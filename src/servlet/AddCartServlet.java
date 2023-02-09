package servlet;

import entity.ProductBean;
import exception.ProductNotFoundException;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //用户还未登录，写回消息让前端跳转至登录页面
        if (request.getSession().getAttribute("user") == null) {
            response.getWriter().println("nologin");
            return;
        }
        String productID = request.getParameter("productID");  //获取商品ID
        try {
            ProductBean prdBean = new ProductService().findProductByID(productID); //调用Service层接口获取商品实体
            HttpSession session = request.getSession();
            Map<ProductBean, Integer> cart = (Map<ProductBean, Integer>)session.getAttribute("cart"); //获取参数“购物车”
            if (cart == null)  //购物车为空，即用户还没加入任何商品
                cart = new HashMap<ProductBean, Integer>();  //构造一个HashMap作为购物车
            //将商品实体放入购物车HashMap
            Integer count = cart.put(prdBean, 1);
            if(count != null)
                cart.put(prdBean, count + 1);
            session.setAttribute("cart", cart);  //将购物车写入会话参数
            response.getWriter().println("true");
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("false");
        }
    }
}
