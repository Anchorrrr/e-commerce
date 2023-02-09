package servlet;

import entity.OrderBean;
import entity.OrderDetailBean;
import entity.ProductBean;
import exception.ProductNotFoundException;
import service.OrderService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String orderID = request.getParameter("id");  //从请求中获取需要查看的订单的ID
        if (orderID == null) {
            request.setAttribute("order", null);
        }
        else {
            OrderBean orderBean = new OrderService().getOrder(orderID);  //调用Service层接口获取orderID对应的订单实体
            if (orderBean == null)
                request.setAttribute("order", null);
            else {
                request.setAttribute("order", orderBean);
                Map<ProductBean, Integer> products = new HashMap<ProductBean, Integer>();  //存放订单内所有商品的容器
                //遍历订单内的商品
                for (OrderDetailBean detail: orderBean.getOrderDetails()) {
                    try {
                        //调用Service层接口获取商品实体，并同购买数量一起放入products容器
                        products.put(new ProductService().findProductByID(detail.getProductID()), detail.getCount());
                    } catch (ProductNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                request.setAttribute("orderDetail", products);  //容器置为请求的参数
            }
        }
        //请求转发至jsp
        request.getRequestDispatcher(request.getContextPath() + "/orderDetail.jsp").forward(request,response);
    }
}