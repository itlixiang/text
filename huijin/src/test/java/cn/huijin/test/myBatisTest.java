package cn.huijin.test;


import cn.huijin.domain.User;
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: 666
 * @Date: 2019/6/10
 * @Time: 15:08
 */

public class myBatisTest {

    /*
        测试查询的方法
     */
    @Test
    public void test1() throws IOException {
        //加载核心配置文件
        InputStream resourceAsAtream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsAtream);
        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql语句
        List<User> userlist = sqlSession.selectList("userMapper.findAll");
        //打印结果
        System.out.println(userlist);
        //释放资源
        sqlSession.close();
    }

    /*
        测试插入的方法
     */
    @Test
    public void test2() throws IOException {

        User user = new User();
        user.setUsername("wangmingda");
        user.setPassword("123456");

        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql语句
        int i = sqlSession.insert("userMapper.add", user);
        //提交事务 myBatis不会自动提交事务 需要手动提交 原始的数据库连接也是不会自动提交  jabcTemplate默认自动提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
    @Test
    public void test3() throws IOException {

        User user = new User();
        user.setId(4);
        user.setUsername("dawang");
        user.setPassword("123456");
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql语句
        int update = sqlSession.update("userMapper.update", user);
        System.out.println(update);
        //提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }
    @Test
    public void test4() throws IOException {

        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql语句
        int i = sqlSession.delete("userMapper.delete",4);
        System.out.println(i);
        //提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();

    }


}
