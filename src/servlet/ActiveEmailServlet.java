package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeEmail")
public class ActiveEmailServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String activeCode = request.getParameter("activeCode");  //获取激活链接中的激活码参数
        response.setContentType("text/html;charset=utf-8");
        if (new UserService().activeEmail(activeCode))   //调用Service层中的邮箱激活接口
            response.getWriter().println("邮箱激活成功!");
        else
            response.getWriter().println("邮箱激活失败!");
    }
}
