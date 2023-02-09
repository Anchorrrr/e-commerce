package servlet;

import entity.UserBean;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        UserBean requestUserBean = new UserBean();
        HttpSession session = request.getSession();  //获取session
        String preURL = (String) session.getAttribute("preURL"); //获取用户在登录前访问的地址，以便登录后能跳转回原先访问的页面
        try {
            BeanUtils.populate(requestUserBean, request.getParameterMap());  //获取请求登录账号的信息（包括邮箱和密码）
            UserBean selectedUserBean = new UserService().login(requestUserBean);  //调用登录service
            session.setAttribute("user", selectedUserBean);  //登录成功
            if (preURL != null) {
                session.removeAttribute("preURL");
                response.sendRedirect(preURL);  //重定向至用户原先访问的页面
            }
            else
                response.sendRedirect(request.getContextPath() + "/index.jsp");  //重定向至商城首页
            return;
        }catch (IllegalAccessException | InvocationTargetException e) {  //登录失败
            e.printStackTrace();
        }catch (LoginException e) {  //登录失败，密码错误
            request.setAttribute("loginMsg", e.getMessage());
        }
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("password", request.getParameter("password"));
        //请求转发回登录页面
        request.getRequestDispatcher(request.getContextPath() + "/login.jsp").forward(request, response);
    }
}
