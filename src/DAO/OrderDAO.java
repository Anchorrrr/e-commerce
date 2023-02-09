package DAO;

import entity.OrderBean;
import entity.OrderDetailBean;
import entity.SaleBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DruidUtils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderDAO
{
    public void insertOrder(OrderBean orderBean) throws SQLException  //新增订单，用于用户创建订单
    {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String cmd="insert into orders(`orderID`, `userID`, `totalPrice`, `actualPayment`, `paid`, `name`, `phone`, `address`, `createTime`) values(?,?,?,?,?,?,?,?,?)";
        runner.update(cmd, orderBean.getOrderID(), orderBean.getUserID(), orderBean.getTotalPrice(), orderBean.getActualPayment(),
                orderBean.isPaid(), orderBean.getName(), orderBean.getPhone(), orderBean.getAddress(), orderBean.getCreateTime());

        String cmd1 = "insert into orderdetails(`orderID`, `productID`, `count`, `totalPrice`) values(?,?,?,?)";
        String cmd2 = "update products set `amount`=`amount`-? where `productID`=?";
        for(OrderDetailBean detail: orderBean.getOrderDetails())
        {
            runner.update(cmd1, detail.getOrderID(), detail.getProductID(), detail.getCount(), detail.getTotalPrice());
            runner.update(cmd2, detail.getCount(), detail.getProductID());
        }
    }
    public OrderBean selectOrder(String orderID) throws SQLException  //根据订单编号查询并返回指定订单
    {
        String cmd = "select * from orders where `orderID`=?";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        OrderBean orderBean = runner.query(cmd, new BeanHandler<OrderBean>(OrderBean.class), orderID);
        cmd = "select * from orderdetails where `orderID`=?";
        orderBean.setOrderDetails((ArrayList<OrderDetailBean>)runner.query(cmd, new BeanListHandler<OrderDetailBean>(OrderDetailBean.class), orderID));
        return orderBean;
    }
    public Map<OrderBean, String> selectOrders() throws SQLException  //查询所有订单并按订单创建时间降序返回
    {
        String cmd = "select * from orders order by createTime desc";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        ArrayList<OrderBean> orders = (ArrayList<OrderBean>)runner.query(cmd, new BeanListHandler<OrderBean>(OrderBean.class));
        Map<OrderBean, String> map = new LinkedHashMap<>();
        String cmd1 = "select `name` from users where `userID`=?";
        for (OrderBean order: orders)
        {
            String name = runner.query(cmd1, new ScalarHandler<>(), order.getUserID());
            map.put(order, name);
        }
        return map;
    }
    public ArrayList<OrderBean> selectOrders(String userID) throws SQLException  //查询并返回指定用户的所有订单
    {
        String cmd = "select * from orders where `userID`=?";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return (ArrayList<OrderBean>)runner.query(cmd, new BeanListHandler<OrderBean>(OrderBean.class), userID);
    }
    public ArrayList<SaleBean> countSales() throws SQLException  //查询并返回群体(男装、女装、童装)分别的销售额
    {
        String cmd = "select products.`group`, sum(`totalPrice`) as 'sales' from orderdetails, products where orderdetails.`productID` = products.`productID` group by products.`group`;";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return (ArrayList<SaleBean>)runner.query(cmd, new BeanListHandler<SaleBean>(SaleBean.class));
    }
    public ArrayList<SaleBean> countSales(String group) throws SQLException //查询指定群体下的各类服装(如短T、牛仔裤等)的销售额
    {
        String cmd = "select products.`category`, sum(`totalPrice`) as 'sales' from orderdetails, products where orderdetails.`productID` = products.`productID` and products.`group`=? group by products.`category`;";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return (ArrayList<SaleBean>)runner.query(cmd, new BeanListHandler<SaleBean>(SaleBean.class), group);
    }
    public int payOrder(String orderID) throws SQLException  //修改订单支付状态为是并写入支付时间
    {
        String cmd = "update orders set `paid`=1, `payTime`=? where `orderID`=?";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return runner.update(cmd, LocalDateTime.now(), orderID);
    }
}
