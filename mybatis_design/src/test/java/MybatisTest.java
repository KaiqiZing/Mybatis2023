

import org.example.dao.UserDao;
import org.example.domain.User;
import org.example.mybatis.io.Resources;
import org.example.mybatis.sqlsession.SqlSession;
import org.example.mybatis.sqlsession.SqlSessionFactory;
import org.example.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

// 注意xml配置文件的配置地址要和项目保持一致不然会导致获取不到数据
public class MybatisTest {

    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args)throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        UserDao userDao = session.getMapper(UserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }
}
