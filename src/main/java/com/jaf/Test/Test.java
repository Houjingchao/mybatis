package com.jaf.Test;

import com.jaf.domain.User;
import com.jaf.Mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * Created by jaf on 16/8/12.
 */
public class Test {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("conf.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

//    public static void main(String[] args) {
//        SqlSession session = sqlSessionFactory.openSession();
//        try {
            /*----------------方法1-----------------*/
//            User user = (User) session.selectOne("com.jaf.Mapper.UserMapper.selectUserByID", 1);
//            System.out.println(user.getUserAddress());
//            System.out.println(user.getUserName());

            /*----------------方法2-----------------*/
//            UserMapper userMapper=session.getMapper(UserMapper.class);
//            User user = userMapper.selectUserByID(1);
//            System.out.println(user.getUserAddress());
//            System.out.println(user.getUserName());
            /*--------------自定义returnMap------------------*/
//            UserMapper UserMapper=session.getMapper(UserMapper.class);
//            List<User> users = UserMapper.selectUsers("summer");
//            for(User user:users) {
//                System.out.println(user.getId() + ":" + user.getUserName() + ":" + user.getUserAddress());
//            }


//        } finally {
//            session.close();
//        }
//    }

    /**
     * 增加user后,必须提交事务,否则不会写入到数据库
     */
    public void addUser() {
        User user = new User();
        user.setUserAddress("北京");
        user.setUserName("JAFER");
        user.setUserAge("20");
        SqlSession session = sqlSessionFactory.openSession();
        try {

            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.addUser(user);
            session.commit();
            System.out.println("当前增加的用户 id 为:" + user.getId());

        } finally {
            session.close();
        }

    }

    /*同样删除后记得,提交事务,否则修改不了数据库*/
    public void deleteUser(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            userMapper.deleteUser(id);
            session.commit();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
//        test.addUser();
        test.deleteUser(1);
    }
}