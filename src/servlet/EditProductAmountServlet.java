package servlet;

import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/editProductAmount")
public class EditProductAmountServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String productID = request.getParameter("productID");  //获取商品ID
        int amount = Integer.parseInt(request.getParameter("amount"));  //获取目标库存数
        PrintWriter out = response.getWriter();
        //调用Service层接口以修改库存数并向writer写入相应操作结果
        if (new ProductService().changeProductAmount(productID, amount))
            out.println("true");
        else
            out.println("false");
        out.close();
    }
}