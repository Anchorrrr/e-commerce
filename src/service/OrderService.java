package service;

import DAO.OrderDAO;
import entity.OrderBean;
import entity.SaleBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class OrderService
{
    OrderDAO DAO = new OrderDAO();

    public boolean createOrder(OrderBean orderBean)     //用户创建订单
    {
        try{
            DAO.insertOrder(orderBean);
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public OrderBean getOrder(String orderID)           //根据订单号获取订单
    {
        try {
            return DAO.selectOrder(orderID);
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Map<OrderBean, String> getOrders()           //获取最近订单（管理员功能）
    {
        try{
            return DAO.selectOrders();
        }catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<OrderBean> getOrders(String userID)//获取某一用户的所有订单
    {
        try{
            return DAO.selectOrders(userID);
        }catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<SaleBean> getSales()               //获取各群体销售额（管理员功能）
    {
        try {
            return DAO.countSales();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<SaleBean> getSales(String group)   //获取某一群体下各类别的销售额（管理员功能）
    {
        try {
            return DAO.countSales(group);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean payOrder(String orderID)             //用户支付订单功能
    {
        try {
            return DAO.payOrder(orderID) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
