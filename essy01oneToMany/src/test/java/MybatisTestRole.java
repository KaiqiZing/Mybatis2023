

import com.essy01oneToMany.dao.IRoleDao;
import com.essy01oneToMany.domain.AccountUser;
import com.essy01oneToMany.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 *
 * 测试mybatis的crud操作
 */
public class MybatisTestRole {

    private InputStream in;
    private SqlSession sqlSession;

    private IRoleDao roleDao;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true); // 实现自动提交的功能
        //4.获取dao的代理对象
        roleDao = sqlSession.getMapper(IRoleDao.class);
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
     * 测试查询所有-roleDao
     */
    @Test
    public void testFindAll(){

      List<Role> roles = roleDao.findAllRole();
        for (Role role : roles) {
            System.out.println(role);
        }
    }



}
