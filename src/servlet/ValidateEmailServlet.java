package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/validateEmail")
public class ValidateEmailServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String email = request.getParameter("email");   //获取请求的email地址
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();   //获取响应的writer
        if (email.equals(""))
            out.println("邮箱不可为空");
        else if (new UserService().validateEmail(email))  //调用service层接口检测该email是否已被注册
            out.println("邮箱可用");
        else
            out.println("此邮箱已被注册，请更换");
        out.close();
    }
}
