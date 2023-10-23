
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.dao.UserDao;
import org.example.domain.QueryVo;
import org.example.domain.User;
import org.apache.ibatis.io.Resources;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.dc.pr.PRError;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @Company
 * mybatis的入门案例
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    private SqlSessionFactory factory;


    /**
     * 初始化配置文件
     * @throws IOException
     */
    @Before
    public void init() throws IOException {

        //1.读取配置文件
         in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        sqlSession = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
         userDao = sqlSession.getMapper(UserDao.class);

    }

    /**
     * 测试方法执行后的操作，
     * 事务提交，释放资源，资源关闭；
     * @throws IOException
     */
    @After
    public void  destory() throws IOException {
//        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    /**
     * 查询所有用用户信息
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("查询所有的参数"+ user);
        }
    }

    @Test
    public  void testSaveUser(){
        User user = new User();
        user.setUsername("save user info");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("测试地址的操作");
        userDao.saveUser(user);
    }

    @Test
    public  void testupdateUser(){
        User user = new User();
        user.setId(49);
        user.setUsername("测试修改后的内容");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("测试修改后的内容");
        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser(){

        userDao.deleteUser(49);
    }

    @Test
    public void testFindById(){

        User user = userDao.findById(46);
        System.out.println("通过id查询到的数据"+ user);
    }

    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testTotal(){
        int integer = userDao.findTotal();
        System.out.println("返回的总数量是"+ integer);
    }

    @Test
    public void testFindByQueryVo(){
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        queryVo.setUser(user);
        List<User> users = userDao.findByQuertVo(queryVo);
        for (User user1 : users) {
            System.out.println("实体类包装类实现查询功能:" + user1);
        }
    }

}
