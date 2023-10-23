import com.essy01Annotation.dao.IUserDao;
import com.essy01Annotation.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
public class AnnotationCRUDTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public  void destroy()throws  Exception{
        session.commit();
        session.close();
        in.close();
    }

    /**
     * 查询所有用户的数据
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("注解查询所有的操作方法："+ user);
        }
    }

    @Test
    public void testFindById(){

        User user = userDao.findById(41);
        System.out.println(user);

    }

    /**
     * 使用聚合函数进行查询的操作
     */
    @Test
    public void testfindTotal(){
        int d = userDao.findTotal();
        System.out.println(d);

    }

}
