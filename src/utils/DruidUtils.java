package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DruidUtils {
    private static DataSource dataSource = null;
    static {
        Properties properties = new Properties();
        try {
            InputStream is = DruidUtils.class.getClassLoader().getResourceAsStream("/utils/druid.properties");
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }
}
