package com.jaf.Mapper;

import com.jaf.domain.User;

/**
 * Created by jaf on 16/8/12.
 */
/*使用合理描述参数 和SQL 语句返回值的接口*/
public interface UserMapper {
    User selectUserByID(int id);
}
