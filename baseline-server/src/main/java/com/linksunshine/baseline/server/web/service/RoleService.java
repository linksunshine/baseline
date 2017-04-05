package com.linksunshine.baseline.server.web.service;

import com.linksunshine.baseline.server.web.dto.RoleDTO;

import java.util.List;

/**
 * Created by ucmed on 2017/4/1.
 */
public interface RoleService {

    int insert(RoleDTO roleDTO);

    List<RoleDTO> loadList();

    int count();

    List<RoleDTO> loadList(int pageNo, int pageSize);

    int countSearch(String searchKey);

    List<RoleDTO> search(int pageNo, int pageSize, String searchKey);

    List<String> findRolePermission(String roleId);

    int insertUpdatePermission(RoleDTO roleDTO);

    int updateRole(RoleDTO roleDTO);

}
