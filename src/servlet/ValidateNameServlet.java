package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/validateName")
public class ValidateNameServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String name = request.getParameter("name");     //获取请求中的用户名
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();    //获取响应的writer
        if (name.equals(""))
            out.println("用户名不可为空");
        else if (new UserService().validateName(name))   //调用service层接口检测该用户名是否已被注册
            out.println("用户名可用");
        else
            out.println("此用户名已被占用，请更换");
        out.close();
    }
}
