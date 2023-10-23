
import com.essy01.dao.IUserDao;
import com.essy01.domain.QueryVo;
import com.essy01.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *
 * 测试mybatis的crud操作
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;


    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true); // 实现自动提交的功能
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
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        //5.执行查询所有方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }

    }


    @Test
    public void testfindUserByCondition(){
        User u = new User();
        u.setUserName("老王");
        u.setUserSex("男");

        //5.执行查询所有方法
        List<User> users = userDao.findUserByCondition(u);
        for(User user : users){
            System.out.println(user);
        }
    }





    @Test
    public void testFindInIds(){
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        vo.setIds(list);

        //5.执行查询所有方法
        List<User> users = userDao.findUserInIds(vo);
        for(User user : users){
            System.out.println(user);
        }
    }




}
