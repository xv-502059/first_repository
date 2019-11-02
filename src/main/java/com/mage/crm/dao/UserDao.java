package com.mage.crm.dao;

import com.mage.crm.vo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    User queryUserByName(String userName);

    User queryUserById(String id);

    int updatePwd(@Param("id") String id, @Param("userPwd")String userPwd);
    @Select("SELECT\n" +
            "\tu.true_name as 'trueName'\n" +
            "FROM\n" +
            "\tt_user u\n" +
            "LEFT JOIN t_user_role ur ON u.id = ur.user_id\n" +
            "LEFT JOIN t_role r ON r.id = ur.role_id\n" +
            "WHERE\n" +
            "\tr.role_name = '客户经理'\n" +
            "AND u.is_valid = 1\n" +
            "AND ur.is_valid = 1\n" +
            "AND r.is_valid = 1")
    List<User> queryAllCustomerManager();
}
