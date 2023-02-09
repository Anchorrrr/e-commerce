package servlet;

import entity.ProductBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/editCart")
public class EditCartServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String productID = request.getParameter("id");  //获取商品ID
        int count = Integer.parseInt(request.getParameter("count"));  //获取商品数量需要改成多少的参数
        Map<ProductBean, Integer> cart = (Map<ProductBean, Integer>) request.getSession().getAttribute("cart");
        ProductBean productBean = new ProductBean();
        productBean.setProductID(productID);
        if (count == 0)
            cart.remove(productBean);  //若目标数量为0，将商品实体从购物车中移除
        else
            cart.put(productBean, count);  //若目标数量大于0，修改为目标数量
    }
}
