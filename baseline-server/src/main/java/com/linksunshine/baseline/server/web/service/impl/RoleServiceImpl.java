package com.linksunshine.baseline.server.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.linksunshine.baseline.server.web.core.orika.OrikaBeanMapper;
import com.linksunshine.baseline.server.web.dao.RoleMapper;
import com.linksunshine.baseline.server.web.dao.RolePermissionMapper;
import com.linksunshine.baseline.server.web.dto.RoleDTO;
import com.linksunshine.baseline.server.web.model.Permission;
import com.linksunshine.baseline.server.web.model.Role;
import com.linksunshine.baseline.server.web.model.RolePermission;
import com.linksunshine.baseline.server.web.model.User;
import com.linksunshine.baseline.server.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ucmed on 2017/4/1.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private OrikaBeanMapper orikaBeanMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public int insert(RoleDTO roleDTO) {
        Role role = orikaBeanMapper.map(roleDTO, Role.class);
        role.setpRoleId(UUID.randomUUID().toString());
        role.setCreatedby("admin");
        role.setCreatedon(new Date());
        role.setModifiedby("admin");
        role.setModifiedon(new Date());
        role.setDeletionState("0");
        return roleMapper.insertSelective(role);
    }

    public List<RoleDTO> loadList() {
        Role role = new Role();
        role.setDeletionState("0");
        Example example = new Example(Role.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria().andEqualTo(role);
        return orikaBeanMapper.mapAsList(roleMapper.selectByExample(example), RoleDTO.class);
    }

    public int count() {
        Role role = new Role();
        role.setDeletionState("0");
        return roleMapper.selectCount(role);
    }

    public List<RoleDTO> loadList(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Role role = new Role();
        role.setDeletionState("0");
        Example example = new Example(Role.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria().andEqualTo(role);
        return orikaBeanMapper.mapAsList(roleMapper.selectByExample(example), RoleDTO.class);
    }

    public int countSearch(String searchKey) {
        Example example = new Example(Role.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria()
                .andCondition("role_name like '%" + searchKey + "%'")
                .andEqualTo("deletionState", "0");
        return roleMapper.selectCountByExample(example);
    }

    public List<RoleDTO> search(int pageNo, int pageSize, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(Role.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria()
                .andCondition("role_name like '%" + searchKey + "%'")
                .andEqualTo("deletionState", "0");
        return orikaBeanMapper.mapAsList(roleMapper.selectByExample(example), RoleDTO.class);
    }

    public List<String> findRolePermission(String roleId) {
        return rolePermissionMapper.findRolePermission(roleId);
    }

    public int insertUpdatePermission(RoleDTO roleDTO) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setpRoleId(roleDTO.getpRoleId());
        rolePermission.setDeletionState("0");

        List<RolePermission> rolePermissionList = rolePermissionMapper.select(rolePermission);
        List<RolePermission> list = new ArrayList<RolePermission>();
        for (String permissionId : roleDTO.getRolePermissions()) {
            boolean isAdd = true;
            for (RolePermission rp : rolePermissionList) {
                if (permissionId.equals(rp.getpPermissionId())) {
                    isAdd = false;
                    break;
                }
            }
            if (isAdd) {
                RolePermission r = new RolePermission();
                r.setpRolePermissionId(UUID.randomUUID().toString());
                r.setpRoleId(roleDTO.getpRoleId());
                r.setpPermissionId(permissionId);
                r.setCreatedby("admin");
                r.setCreatedon(new Date());
                r.setModifiedby("admin");
                r.setModifiedon(new Date());
                r.setDeletionState("0");
                list.add(r);
            }
        }


        return rolePermissionMapper.insertUpdatePermission(list);
    }

    public int updateRole(RoleDTO roleDTO) {
        roleDTO.setModifiedon(new Date());
        return roleMapper.updateByPrimaryKeySelective(orikaBeanMapper.map(roleDTO, Role.class));
    }
}
