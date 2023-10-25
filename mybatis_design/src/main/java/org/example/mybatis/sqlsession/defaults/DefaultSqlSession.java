package org.example.mybatis.sqlsession.defaults;

import org.example.mybatis.cfg.Configuration;
import org.example.mybatis.sqlsession.SqlSession;
import org.example.mybatis.sqlsession.proxy.MapperProxy;
import org.example.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Connection connection;

    public DefaultSqlSession(Configuration configuration){
        this.configuration = configuration;
        connection = DataSourceUtil.getConnection(configuration);
    }


    /**
     * 创建代理对象
     *  UserDao userDao = session.getMapper(UserDao.class);
     * @param daoInterfaceClass dao的接口字节码
     * @return
     * @param <T>
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        // proxy.newProxyInstance() 方法创建代理类实例， 通过代理类实例运行时动态生成；
        // 代理类实例将方法调用转发给InvocationHandler接口；
        // 实现invoke()方法， 该方法会收到代理类中所有方法的调用请求；
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(configuration.getMappers(),connection));
    }

    /**
     * 资源释放
     */
    @Override
    public void close() {
        if (connection != null){
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

