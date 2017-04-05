package com.linksunshine.baseline.server.web.service;


import com.linksunshine.baseline.server.web.dto.PermissionDTO;

import java.util.List;

/**
 * Created by ucmed on 2017/3/27.
 */
public interface PermissionService {
    int insert(PermissionDTO permissionDTO);

    int count();

    List<PermissionDTO> loadList(int pageNo, int pageSize);

    int countSearch(String searchKey);

    List<PermissionDTO> search(int pageNo, int pageSize, String searchKey);

    List<PermissionDTO> loadList();

    int updatePermission(PermissionDTO permissionDTO);

}
