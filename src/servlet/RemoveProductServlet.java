package servlet;

import entity.ProductBean;
import exception.ProductNotFoundException;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/removeProduct")
public class RemoveProductServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String productID = request.getParameter("productID");  //获取需要下架的商品的ID
        PrintWriter out = response.getWriter();
        try {
            ProductBean productBean = new ProductService().findProductByID(productID);
            if (productBean.isPin()) {  //该商品是固定在售商品，不可下架，（防止测试期内商品全被下架）（可以自己添加后再下架）
                out.println("pin");
                return;
            }
            //调用Service层接口进行下架
            if (new ProductService().removeProduct(productID))
                out.println("true");
            else
                out.println("false");
        } catch (ProductNotFoundException e) {
            out.println("false");
        }
    }
}