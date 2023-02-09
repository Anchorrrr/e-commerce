package servlet;

import entity.SaleBean;
import entity.UserBean;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/sales")
public class SalesServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("user");  //从会话中获取用户实体
        if (userBean == null || !userBean.getType().equals("admin")) {  //若未登录或已登录用户不是管理员，不予查看
            request.getRequestDispatcher(request.getContextPath() + "sales.jsp").forward(request, response);
            return;
        }
        //调用Service层接口获取各个销售额并置为请求内的参数
        ArrayList<SaleBean> sale_all = new OrderService().getSales();
        ArrayList<SaleBean> sale_men = new OrderService().getSales("men");
        ArrayList<SaleBean> sale_women = new OrderService().getSales("women");
        ArrayList<SaleBean> sale_kids = new OrderService().getSales("kids");
        request.setAttribute("sale_all", sale_all);
        request.setAttribute("sale_men", sale_men);
        request.setAttribute("sale_women", sale_women);
        request.setAttribute("sale_kids", sale_kids);
        request.getRequestDispatcher(request.getContextPath() + "sales.jsp").forward(request, response);
    }
}
