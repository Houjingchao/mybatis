package com.jaf.Test;

import com.jaf.domain.User;
import com.jaf.Mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

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

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
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
            UserMapper UserMapper=session.getMapper(UserMapper.class);
            List<User> users = UserMapper.selectUsers("summer");
            for(User user:users) {
                System.out.println(user.getId() + ":" + user.getUserName() + ":" + user.getUserAddress());
            }
        } finally {
            session.close();
        }
    }
}