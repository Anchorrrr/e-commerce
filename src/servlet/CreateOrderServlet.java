package servlet;

import entity.OrderBean;
import entity.OrderDetailBean;
import entity.ProductBean;
import entity.UserBean;
import org.apache.commons.beanutils.BeanUtils;
import service.OrderService;
import utils.UidUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        //从会话中获取用户实体和购物车实体
        UserBean userBean = (UserBean)session.getAttribute("user");
        Map<ProductBean, Integer> cart = (Map<ProductBean, Integer>)session.getAttribute("cart");
        OrderBean orderBean = new OrderBean();
        try {
            BeanUtils.populate(orderBean, request.getParameterMap());  //将请求中的收货人信息置入订单实体中
        } catch(IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        orderBean.setOrderID(UidUtils.getUid());  //获取一个UID作为订单编号
        orderBean.setUserID(userBean.getUserID());  //绑定用户
        orderBean.setPaid(false);  //订单刚创建，尚未支付，故支付状态设为否
        orderBean.setCreateTime(LocalDateTime.now());  //获取本地时间作为订单创建时间
        //遍历购物车内的商品，加入订单实体
        for(Map.Entry<ProductBean, Integer> prdItem: cart.entrySet())
        {
            OrderDetailBean orderDetail = new OrderDetailBean();  //创建订单内详细商品实体
            //置入订单ID、商品ID、商品购买数、该商品总价
            orderDetail.setOrderID(orderBean.getOrderID());
            orderDetail.setProductID(prdItem.getKey().getProductID());
            orderDetail.setCount(prdItem.getValue());
            orderDetail.setTotalPrice(prdItem.getKey().getPrice() * prdItem.getValue());
            orderBean.getOrderDetails().add(orderDetail);  //将该详细商品实体加入订单实体
        }
        PrintWriter out = response.getWriter();
        //调用Service层接口进行订单的写入
        if(new OrderService().createOrder(orderBean)){  //写入成功
            session.removeAttribute("cart");
            request.setAttribute("order", orderBean);
            //以JSON格式写入订单信息
            out.write("{\"flag\":\"true\", \"orderID\": \"" + orderBean.getOrderID() +
                      "\", \"createTime\": \"" + orderBean.getCreateTime() + "\"}");
        }
        else  //写入失败
            out.write("{flag: 'false'}");
        out.close();
   }
}
