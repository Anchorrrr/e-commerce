package servlet;

import DAO.UserDAO;
import entity.UserBean;
import service.UserService;
import utils.MailUtils;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/sendEmail")
public class SendEmailServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        UserBean userBean = null;
        try {
            userBean = new UserDAO().selectUserByEmail(request.getParameter("email"));  //获取用户的UserBean
            if (userBean == null)
                request.setAttribute("sendEmailMsg", "该邮箱尚未注册");
            else if (userBean.isActivated())
                request.setAttribute("sendEmailMsg", "该账号已激活");
            else {
                String activeCode = userBean.getActiveCode();  //获取激活码
                String msg = "请点击以下链接激活邮箱：\n\n" + request.getScheme()+"://" + request.getServerName() +
                             request.getContextPath() + "/activeEmail?activeCode=" + activeCode + "\n\n————岂曰无衣";
                MailUtils.sendMail(userBean.getEmail(), msg);  //使用MailUtils发送验证邮件至用户的邮箱
                request.setAttribute("sendEmailMsg", "已发送激活邮件");
            }
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("sendEmailMsg", "已发送激活链接");
        }
        request.setAttribute("email", request.getParameter("email"));
        request.getRequestDispatcher(request.getContextPath() + "/sendEmail.jsp").forward(request, response);
    }
}