import com.essy01cacheTest.dao.IUserDao;
import com.essy01cacheTest.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;


public class UserTest {

    private InputStream in;
    private  SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
        // sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }




    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstCache(){
        User user1 = userDao.findById(46);
        System.out.println("第一次查询用户"+ user1);



        User user2 = userDao.findById(46);
        System.out.println("第二次查询用户"+user2);


        System.out.println("两次查询结果对比，如果为true则代表没有创建新的对象使用缓存数据");
        System.out.println(user1 == user2);
    }




    /**
     * 测试一级缓存的清空操作
     */
    @Test
    public void testFirstCacheClearOperation(){
        User user1 = userDao.findById(41);
        System.out.println(user1);

//        sqlSession.close();
        //再次获取SqlSession对象
//        sqlSession = factory.openSession();

        sqlSession.clearCache();//此方法也可以清空缓存

   userDao = sqlSession.getMapper(IUserDao.class);

        User user2 = userDao.findById(41);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

    /**
     * 测试缓存的同步
     */
    @Test
    public void testClearlCache(){
        //1.根据id查询用户
        User user1 = userDao.findById(41);
        System.out.println(user1);

        //2.更新用户信息
        user1.setUsername("update user clear cache");
        user1.setAddress("北京市海淀区");
        userDao.updateUser(user1);

        //3.再次查询id为41的用户
        User user2 = userDao.findById(41);
        System.out.println(user2);

        System.out.println(user1 == user2);

    }
}
