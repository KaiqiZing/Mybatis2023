package org.example.mybatis.utils;

import org.example.mybatis.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author
 * @Company
 * 用于创建数据源的工具类
 */
public class DataSourceUtil {

    /**
     *
     * @param configuration
     * @return
     */
    public static Connection getConnection(Configuration configuration){
        try {
            Class.forName(configuration.getDriver());
            return DriverManager.getConnection(configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
