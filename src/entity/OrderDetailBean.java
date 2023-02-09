package entity;

public class OrderDetailBean
{
    String orderID;      //隶属的订单编号
    String productID;    //该商品的编号
    int count;           //商品购买数量
    double totalPrice;   //该商品总价

    public String getOrderID()
    {
        return orderID;
    }

    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }

    public String getProductID()
    {
        return productID;
    }

    public void setProductID(String productID)
    {
        this.productID = productID;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }
}
