package service;

import DAO.ProductDAO;
import entity.PageBean;
import entity.ProductBean;
import exception.ProductNotFoundException;

import java.sql.SQLException;
import java.util.List;

public class ProductService
{
    ProductDAO DAO = new ProductDAO();

    //根据商品编号查找商品
    public ProductBean findProductByID(String id) throws ProductNotFoundException
    {
        try
        {
            ProductBean prdBean = DAO.selectProductByID(id);
            if (prdBean == null)
                throw new ProductNotFoundException(id);
            return prdBean;
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new ProductNotFoundException(id);
        }
    }
    //根据搜索条件返回一页商品展示页
    public PageBean findProductByPage(String group, String category, String name, int page, int productAmountPerPage)
            throws ProductNotFoundException
    {
        PageBean pageBean = new PageBean();
        List<ProductBean> products;
        try
        {
            if (name != null)
                products = DAO.selectProductByName(group, category, name);
            else if (group != null && category != null)
                products = DAO.selectProductByCategory(group, category);
            else if(group != null)
                products = DAO.selectProductByGroup(group);
            else
                products = DAO.selectProduct();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new ProductNotFoundException(group + "-" + category + "-" + name);
        }
        if (products.size() == 0)
        {
            pageBean.setPageNum(1);
            pageBean.setTotalPageCount(1);
            return pageBean;
        }
        int totalPageCount = (int) Math.ceil((double) products.size() / productAmountPerPage);
        pageBean.setTotalPageCount(totalPageCount);
        page = Math.min(page, totalPageCount);
        pageBean.setPageNum(page);
        for (int i = (page - 1) * productAmountPerPage; (i < page * productAmountPerPage) && (i < products.size()); i++)
            pageBean.getProducts().add(products.get(i));
        return pageBean;
    }
    //管理员添加商品
    public boolean addProduct(ProductBean productBean)
    {
        try{
            DAO.insertProduct(productBean);
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    //管理员移除商品
    public boolean removeProduct(String productID)
    {
        try{
            if (DAO.deleteProduct(productID) == 0)
                return false;
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    //管理员修改商品现有库存数
    public boolean changeProductAmount(String productID, int amount)
    {
        try {
            return (DAO.updateProductAmount(productID, amount) == 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
