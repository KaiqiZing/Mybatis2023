package org.example.mybatis.sqlsession.defaults;

import org.example.mybatis.cfg.Configuration;
import org.example.mybatis.sqlsession.SqlSession;
import org.example.mybatis.sqlsession.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    /**
     * 创建一个新的操作数据库对象
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
