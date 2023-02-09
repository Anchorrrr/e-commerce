package servlet;

import entity.UserBean;
import service.OrderService;
import utils.MailUtils;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String orderID = request.getParameter("orderID");   //获取需要结账的订单ID
        PrintWriter out = response.getWriter();
        //正常情况下应该调用微信、支付宝等网银接口来让顾客支付，并接收接口返回的支付状态信息，
        //但使用这些接口需要申请商户，操作繁杂，故此处设计为仅仅是简单的不收取费用的“一键支付”。
        //调用Service层接口修改订单支付状态、写入支付时间。
        if (new OrderService().payOrder(orderID)) {  //操作成功
            //向顾客邮箱发送带有订单号的订单支付成功邮件
            UserBean userBean = (UserBean) request.getSession().getAttribute("user");
            String msg = "尊敬的" + userBean.getName() + ": 您的订单(订单号为" + orderID + ")支付成功。————岂曰无衣";
            try {
                MailUtils.sendMail(userBean.getEmail(), msg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            out.println("true");
        }
        else  //操作失败
            out.println("false");
        out.close();
    }
}
