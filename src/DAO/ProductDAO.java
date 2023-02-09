package DAO;

import entity.ProductBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DruidUtils;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO
{
    public ArrayList<ProductBean> selectProduct() throws SQLException  //返回所有商品（无过滤条件）
    {
        String cmd = "select * from products";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return (ArrayList<ProductBean>)runner.query(cmd, new BeanListHandler<ProductBean>(ProductBean.class));
    }
    public ProductBean selectProductByID(String productID) throws SQLException  //根据商品编号返回指定单一商品
    {
        String cmd = "select * from products where productID=?";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return runner.query(cmd, new BeanHandler<ProductBean>(ProductBean.class), productID);
    }
    public ArrayList<ProductBean> selectProductByName(String group, String category, String name) //跟据商品名进行模糊查询
            throws SQLException
    {
        if (group == null)
        {
            String cmd = "select * from products where name like '%" + name + "%'";
            QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
            return (ArrayList<ProductBean>) runner.query(cmd, new BeanListHandler<ProductBean>(ProductBean.class));
        }
        else if(category == null)
        {
            String cmd = "select * from products where `group`=? and name like '%" + name + "%'";
            QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
            return (ArrayList<ProductBean>) runner.query(cmd, new BeanListHandler<ProductBean>(ProductBean.class), group);
        }
        else
        {
            String cmd = "select * from products where `group`=? and category=? and name like '%" + name + "%'";
            QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
            return (ArrayList<ProductBean>) runner.query(cmd, new BeanListHandler<ProductBean>(ProductBean.class), group, category);
        }
    }
    public ArrayList<ProductBean> selectProductByGroup(String group) throws SQLException  //根据指定群体查询商品
    {
        String cmd = "select * from products where `group`=?";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return (ArrayList<ProductBean>)runner.query(cmd, new BeanListHandler<ProductBean>(ProductBean.class), group);
    }
    public ArrayList<ProductBean> selectProductByCategory(String group, String category)  //根据指定类别查询商品
            throws SQLException
    {
        String cmd = "select * from products where `group`=? and category=?";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return (ArrayList<ProductBean>)runner.query(cmd, new BeanListHandler<ProductBean>(ProductBean.class), group, category);
    }
    public void insertProduct(ProductBean productBean) throws SQLException  //插入商品
    {
        String cmd = "insert into products values(?,?,?,?,?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        runner.update(cmd, productBean.getProductID(), productBean.getName(), productBean.getGroup(),
                productBean.getCategory(), productBean.getPrice(), productBean.getImgURL(),
                productBean.getAmount(),productBean.getDescription(), productBean.isPin());
    }
    public int deleteProduct(String productID) throws  SQLException  //删除（下架）商品
    {
        String cmd = "delete from products where `productID`=?";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return runner.update(cmd, productID);
    }
    public int updateProductAmount(String productID, int amount) throws SQLException  //修改商品现有库存数
    {
        String cmd = "update products set `amount`=? where `productID`=?";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return runner.update(cmd, amount, productID);
    }
}
