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

@WebServlet("/productDetail")
public class ProductDetailServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String productID = request.getParameter("id");  //获取请求中的商品ID参数
        try{
            //调用Service层接口获取ID对应的Product实体
            ProductBean productBean = new ProductService().findProductByID(productID);
            //作为会话参数
            request.getSession().setAttribute("productBean", productBean);
            //请求转发至productDetail.jsp，让其展示商品详细信息
            request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
        }catch (ProductNotFoundException e){
            e.printStackTrace();
        }
    }
}
