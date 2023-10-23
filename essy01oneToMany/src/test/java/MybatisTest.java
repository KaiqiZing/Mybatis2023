

import com.essy01oneToMany.dao.IAccountDao;
import com.essy01oneToMany.dao.IUserDao;
import com.essy01oneToMany.domain.Account;
import com.essy01oneToMany.domain.AccountUser;
import com.essy01oneToMany.domain.User;
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
import java.util.ArrayList;

/**
 *
 * 测试mybatis的crud操作
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;

    private IAccountDao accountDao;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true); // 实现自动提交的功能
        //4.获取dao的代理对象
        accountDao = sqlSession.getMapper(IAccountDao.class);
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
     * 测试查询所有-accountDao
     */
    @Test
    public void testFindAll(){

        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }

    }

    /**
     * 查询所有账户信息，同时包含用户名称和地址值；
     */
    @Test
    public void testFindAllAccountUser(){

        List<AccountUser> accountUsers = accountDao.findAllAccount();
        for (AccountUser accountUser : accountUsers) {
            System.out.println(accountUser);
        }
    }


}
