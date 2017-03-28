package com.linksunshine.baseline.server.web.dao;

import com.linksunshine.baseline.server.web.model.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

public interface UserMapper extends Mapper<User> {

    @Select({
            "select r.p_role_id from p_user u, p_role r,p_user_role ur ",
            "where u.p_user_id=#{userId,jdbcType=VARCHAR} and u.p_user_id=ur.p_user_id ",
            "and r.p_role_id=ur.p_role_id and u.deletion_state='0' and r.deletion_state='0'",
            "and ur.deletion_state='0'"
    })
    Set<String> findRoles(String userId);

    @Select({
            "select permission from p_user u, p_role r, p_permission p, ",
            "p_user_role ur, p_role_permission rp ",
            "where u.p_user_id=#{userId,jdbcType=VARCHAR} ",
            "and u.p_user_id=ur.p_user_id and r.p_role_id=ur.p_role_id ",
            "and r.p_role_id=rp.p_role_id ",
            "and p.p_permission_id=rp.p_permission_id ",
            "and u.deletion_state='0' and r.deletion_state='0' and p.deletion_state='0'",
            "and ur.deletion_state='0' and rp.deletion_state='0'"
    })
    Set<String> findPermissions(String userId);


}