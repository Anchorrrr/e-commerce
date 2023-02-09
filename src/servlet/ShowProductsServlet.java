package servlet;

import entity.PageBean;
import exception.ProductNotFoundException;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/showProducts")
public class ShowProductsServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //通过页码参数page获取请求的页码
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }
        //获取参数group、category、name
        String group = request.getParameter("group");
        String category = request.getParameter("category");
        String name = request.getParameter("name");
        int productAmountPerPage = 20;  //每页显示20件商品
        try {
            //调用Service层的接口来获取pageBean使用实体（包含需要展示的商品）
            PageBean pageBean = new ProductService().findProductByPage(group,category,name,page,productAmountPerPage);
            pageBean.setIsIndexPage(group == null && name == null);
            request.getSession().setAttribute("pageBean", pageBean);  //将pageBean加入会话参数
            if (group == null && name == null)
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            else
                request.getRequestDispatcher(request.getContextPath() + "/products.jsp").forward(request, response);
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("ErrorMessage", e.getMessage());
            if (group == null && name == null)
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            else
                request.getRequestDispatcher(request.getContextPath() + "/products.jsp").forward(request, response);
        }
    }
}
