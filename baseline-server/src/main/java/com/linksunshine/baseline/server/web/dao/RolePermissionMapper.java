package com.linksunshine.baseline.server.web.dao;

import com.linksunshine.baseline.server.web.model.RolePermission;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface RolePermissionMapper extends Mapper<RolePermission> {
    @Select({
            "SELECT",
            "p_permission_id",
            "FROM",
            "p_role_permission",
            "WHERE",
            "p_role_id = #{roleId,jdbcType=VARCHAR}",
            "AND deletion_state = '0'"
    })
    @Results({
            @Result(column = "p_permission_id", property = "permissionId", jdbcType = JdbcType.VARCHAR)})
    List<String> findRolePermission(String roleId);


    Integer insertUpdatePermission(List<RolePermission> list);

}