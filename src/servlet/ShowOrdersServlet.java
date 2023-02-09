package servlet;

import entity.OrderBean;
import entity.UserBean;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/showOrders")
public class ShowOrdersServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        UserBean userBean = (UserBean) request.getSession().getAttribute("user");  //从会话中获取用户实体
        if (userBean == null) {  //用户未登录
            request.setAttribute("orders", null);
        }
        else {  //用户已登录
            String userID;
            if (userBean.getType().equals("admin"))  //登录用户的身份为管理员，管理员可以查看所有用户的订单记录
                userID = request.getParameter("userID");  //从请求参数中获取需要查看记录的用户的ID
            else  //登录用户的身份为普通用户，普通用户只能查看自己的订单记录
                userID = userBean.getUserID();  //获取该用户的ID
            ArrayList<OrderBean> orders = new OrderService().getOrders(userID);  //调用Service层接口获取订单记录
            if (orders.size() == 0)  //订单记录数为0，即未下过单
                request.setAttribute("orders", null);
            else
                request.setAttribute("orders", orders);
        }
        request.getRequestDispatcher(request.getContextPath() + "/orders.jsp").forward(request, response);
    }
}
