package entity;

import java.util.ArrayList;
import java.util.List;

public class PageBean
{
    private int pageNum;         //当前的页数
    private int totalPageCount;  //总共页数
    private boolean isIndexPage; //是否为网站首页
    private List<ProductBean> products = new ArrayList<>(); //展示的商品列表

    public int getPageNum()
    {
        return pageNum;
    }
    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }

    public int getTotalPageCount()
    {
        return totalPageCount;
    }
    public void setTotalPageCount(int totalPageCount)
    {
        this.totalPageCount = totalPageCount;
    }

    public boolean isIndexPage()
    {
        return isIndexPage;
    }
    public void setIsIndexPage(boolean isIndexPage)
    {
        this.isIndexPage = isIndexPage;
    }

    public List<ProductBean> getProducts()
    {
        return products;
    }
    public void setProducts(List<ProductBean> products)
    {
        this.products = products;
    }
}
