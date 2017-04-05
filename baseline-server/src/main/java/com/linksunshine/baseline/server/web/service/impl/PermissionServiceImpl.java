package com.linksunshine.baseline.server.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.linksunshine.baseline.server.web.core.orika.OrikaBeanMapper;
import com.linksunshine.baseline.server.web.dao.PermissionMapper;
import com.linksunshine.baseline.server.web.dto.PermissionDTO;
import com.linksunshine.baseline.server.web.model.Permission;
import com.linksunshine.baseline.server.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ucmed on 2017/4/1.
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    private OrikaBeanMapper orikaBeanMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    public int insert(PermissionDTO permissionDTO) {
        Permission permission = orikaBeanMapper.map(permissionDTO, Permission.class);
        permission.setPermissionId(UUID.randomUUID().toString());
        permission.setCreatedby("admin");
        permission.setCreatedon(new Date());
        permission.setModifiedby("admin");
        permission.setModifiedon(new Date());
        permission.setDeletionState("0");
        return permissionMapper.insertSelective(permission);
    }

    public int count() {
        Permission permission = new Permission();
        permission.setDeletionState("0");
        return permissionMapper.selectCount(permission);
    }

    public List<PermissionDTO> loadList(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Permission permission = new Permission();
        permission.setDeletionState("0");
        Example example = new Example(Permission.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria().andEqualTo(permission);
        return orikaBeanMapper.mapAsList(permissionMapper.selectByExample(example), PermissionDTO.class);
    }

    public int countSearch(String searchKey) {
        Example example = new Example(Permission.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria()
                .andCondition("permission_name like '%" + searchKey + "%'")
                .andEqualTo("deletionState", "0");
        return permissionMapper.selectCountByExample(example);
    }

    public List<PermissionDTO> search(int pageNo, int pageSize, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(Permission.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria()
                .andCondition("permission_name like '%" + searchKey + "%'")
                .andEqualTo("deletionState", "0");
        return orikaBeanMapper.mapAsList(permissionMapper.selectByExample(example), PermissionDTO.class);
    }

    public List<PermissionDTO> loadList() {
        Permission permission = new Permission();
        permission.setDeletionState("0");
        Example example = new Example(Permission.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria().andEqualTo(permission);
        return orikaBeanMapper.mapAsList(permissionMapper.selectByExample(example), PermissionDTO.class);
    }

    public int updatePermission(PermissionDTO permissionDTO) {
        permissionDTO.setModifiedon(new Date());
        return permissionMapper.updateByPrimaryKeySelective(orikaBeanMapper.map(permissionDTO, Permission.class));
    }
}
