package entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderBean
{
    private String orderID;          //本订单的编号
    private String userID;           //用户编号
    private double totalPrice;       //总价
    private double actualPayment;    //实付
    private boolean paid;            //是否已支付
    private String name;             //收货人姓名
    private String phone;            //收货电话
    private String address;          //收货地址
    private LocalDateTime createTime;//订单创建时间
    private LocalDateTime payTime;   //订单支付时间
    private List<OrderDetailBean> orderDetails = new ArrayList<>(); //订单内的商品

    public String getOrderID()
    {
        return orderID;
    }
    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }

    public String getUserID()
    {
        return userID;
    }
    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public double getActualPayment()
    {
        return actualPayment;
    }
    public void setActualPayment(double actualPayment)
    {
        this.actualPayment = actualPayment;
    }

    public boolean isPaid()
    {
        return paid;
    }
    public void setPaid(boolean paid)
    {
        this.paid = paid;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public List<OrderDetailBean> getOrderDetails()
    {
        return orderDetails;
    }
    public void setOrderDetails(List<OrderDetailBean> orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public LocalDateTime getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(LocalDateTime createTime)
    {
        this.createTime = createTime;
    }

    public LocalDateTime getPayTime()
    {
        return payTime;
    }
    public void setPayTime(LocalDateTime payTime)
    {
        this.payTime = payTime;
    }
}
