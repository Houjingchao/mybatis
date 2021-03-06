package com.jaf.Mapper;

import com.jaf.domain.Article;
import com.jaf.domain.User;

import java.util.List;

/**
 * Created by jaf on 16/8/12.
 */
/*使用合理描述参数 和SQL 语句返回值的接口*/
public interface UserMapper {
    User selectUserByID(int id);

    List selectUsers(String userName);

    /*增加addUser方法*/
    void addUser(User user);

    /*增加deleteUser方法*/
    void deleteUser(int id);

    List<Article> getUserArticles(int id);

}
