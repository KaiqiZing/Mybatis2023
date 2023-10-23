package org.example.mybatis.sqlsession.proxy;

import org.example.mybatis.cfg.Mapper;
import org.example.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {
    private Map<String, Mapper> mappers;
    private Connection connection;

    public MapperProxy(Map<String, Mapper> mappers, Connection connection) {
        this.mappers = mappers;
        this.connection = connection;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /*1 获取到方法名
        * 2.获取到方法所在类的名称
        * 3. 组合key
        * 4. 获取mapper中的mapper对象
        * 5.调用工具类执行查询所有*/

        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        String key = className + "." + methodName;
        Mapper mapper = mappers.get(key);

        if (mapper == null){
            throw new IllegalAccessException("传入的参数有误");
        }

        return new Executor().selectList(mapper, connection);
    }
}
