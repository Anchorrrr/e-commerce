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
import java.util.Map;

@WebServlet("/recentOrders")
public class RecentOrdersServlet extends HttpServlet
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
        if (userBean == null || !userBean.getType().equals("admin")) {  //若未登录，或已登录用户不是管理员，不允许查看
            request.setAttribute("orders", null);
        } else {
            Map<OrderBean, String> orders = new OrderService().getOrders();  //调用Service层服务获取最近订单
            if (orders.size() == 0)
                request.setAttribute("orders", null);
            else
                request.setAttribute("orders", orders);
        }
        request.getRequestDispatcher(request.getContextPath() + "/recentOrders.jsp").forward(request, response);
    }
}