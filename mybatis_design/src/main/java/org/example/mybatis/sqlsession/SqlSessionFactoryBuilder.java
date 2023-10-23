package org.example.mybatis.sqlsession;

import org.example.mybatis.cfg.Configuration;
import org.example.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import org.example.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    /**
     * 根据参数的字节输入流来构建一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration configuration = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(configuration);
    }
}
