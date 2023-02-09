package servlet;

import entity.ProductBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import service.ProductService;
import utils.UidUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ProductBean productBean = new ProductBean();
        Map<String, String> map = new HashMap<String, String>();  //map作为存放商品信息的容器
        map.put("productID", UidUtils.getUid());  //使用UID工具生成一个随机且不重复的ID作为商品编号
        map.put("pin", "false");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(this.getServletContext().getRealPath("/temp")));
        factory.setSizeThreshold(1024*1024*10);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                // 断当前是否是上传组件
                if (item.isFormField()) {  //不是上传组件
                    String fieldName = item.getFieldName();  //获取组件名称
                    String value = item.getString("utf-8");  //解决乱码问题
                    map.put(fieldName, value);
                } else {  //是上传组件
                    String imgurl_parent = "/productImg";  //图片存储父目录
                    String randomName = map.get("productID") + ".png";
                    File parentDir = new File(this.getServletContext().getRealPath(imgurl_parent));
                    //验证目录是否存在，如果不存在，则创建目录
                    if (!parentDir.exists()) {
                        parentDir.mkdirs();
                    }
                    String imgurl = imgurl_parent + "/" + randomName;
                    map.put("imgURL", imgurl);  //放入图片存储路径
                    IOUtils.copy(item.getInputStream(), new FileOutputStream(new File(parentDir, randomName)));
                    item.delete();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        try {
            BeanUtils.populate(productBean, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (new ProductService().addProduct(productBean)) {
            request.setAttribute("addProductMsg", "商品添加成功");
            request.getRequestDispatcher(request.getContextPath() + "/addProduct.jsp").forward(request, response);
        } else {
            request.setAttribute("addProductMsg", "商品添加失败");
            request.getRequestDispatcher(request.getContextPath() + "/addProduct.jsp").forward(request, response);
        }
    }
}
