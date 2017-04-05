package com.linksunshine.baseline.server.web.dao;

import com.linksunshine.baseline.server.web.model.UserRole;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserRoleMapper extends Mapper<UserRole> {
    @Select({
            "SELECT",
            "p_role_id",
            "FROM",
            "p_user_role",
            "WHERE",
            "p_user_id = #{userId,jdbcType=VARCHAR}",
            "AND deletion_state = '0'"
    })
    @Results({
            @Result(column = "p_role_id", property = "pRoleId", jdbcType = JdbcType.VARCHAR)})
    List<String> findUserRole(String userId);


    Integer insertUpdateRole(List<UserRole> list);
}