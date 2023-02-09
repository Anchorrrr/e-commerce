package servlet;

import org.apache.commons.beanutils.*;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import entity.UserBean;
import service.UserService;
import utils.DynamicCodeUtils;
import utils.MailUtils;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        UserBean userBean = new UserBean() ;
        request.setCharacterEncoding("utf-8");
        try {
            BeanUtils.populate(userBean, request.getParameterMap());   //以JavaBean形式获取需要注册的账号的信息
        }catch(IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        String activeCode = DynamicCodeUtils.getDynamicCode();  //获取随机的账号激活码
        userBean.setActiveCode(activeCode);  //将激活码置入userBean
        if(new UserService().register(userBean))  //注册成功
        {
            String msg = "请点击以下链接激活邮箱：\n\n" + request.getScheme() + "://" +
                         request.getServerName() + request.getContextPath() + "/activeEmail?activeCode=" + activeCode +
                         "\n\n————岂曰无衣";  //要发送的邮箱文本
            try {
                MailUtils.sendMail(userBean.getEmail(), msg);  //发送至用户注册的邮箱
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            request.setAttribute("registerMsg", "注册成功，请查收邮件并激活账号");
        }
        else //注册失败
            request.setAttribute("registerMsg", "注册失败，请稍后重试");
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("name", request.getParameter("name"));
        request.getRequestDispatcher(request.getContextPath() + "/register.jsp").forward(request, response);
    }
}
