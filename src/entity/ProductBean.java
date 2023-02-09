package entity;

import java.io.Serial;
import java.io.Serializable;

public class ProductBean implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;
    private String productID;
    private String name;
    private double price;
    private String group;       //服装群体，men/women/kids
    private String category;    //服装种类，短袖T恤/长袖T恤/牛仔裤等
    private String imgURL;      //服装展示图片的路径
    private int amount;         //库存数量
    private String description; //服装文字简介
    private boolean pin;

    public String getProductID()
    {
        return productID;
    }

    public void setProductID(String productID)
    {
        this.productID = productID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getGroup()
    {
        return group;
    }
    public void setGroup(String group)
    {
        this.group = group;
    }

    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getImgURL()
    {
        return imgURL;
    }
    public void setImgURL(String imgURL)
    {
        this.imgURL = imgURL;
    }

    public int getAmount()
    {
        return amount;
    }
    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isPin()
    {
        return pin;
    }
    public void setPin(boolean pin)
    {
        this.pin = pin;
    }

    static public String groupMap(String group)
    {
        return switch (group)
                {
                    case ("women") -> "女装";
                    case ("men") -> "男装";
                    case ("kids") -> "童装";
                    default -> "";
                };
    }
    static public String categoryMap(String category)
    {
        return switch (category)
                {
                    case("shortT") -> "短袖T恤";
                    case("longT") -> "长袖T恤";
                    case("fleece") -> "卫衣";
                    case("downjacket") -> "羽绒服";
                    case("jeans") -> "牛仔裤";
                    case("casualpants") -> "休闲裤";
                    case("sweatpants") -> "运动裤";
                    case("dress") -> "连衣裙";
                    case("skirt") -> "半身裙";
                    default -> "";
                };
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 17;
        result = prime * result + ((productID == null) ? 0:productID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ProductBean other = (ProductBean) obj;
        if (productID == null || other.productID == null)
            return false;
        return productID.equals(other.productID);
    }
}
