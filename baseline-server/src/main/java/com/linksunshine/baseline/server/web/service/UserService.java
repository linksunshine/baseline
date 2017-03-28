package com.linksunshine.baseline.server.web.service;

import com.linksunshine.baseline.server.web.dto.UserDTO;

import java.util.Set;

/**
 * Created by ucmed on 2017/3/27.
 */
public interface UserService {
    UserDTO findByUsername(String username);

    /**
     * 根据用户Id查找其角色
     *
     * @param userId
     * @return
     */
    Set<String> findRoles(String userId);

    /**
     * 根据用户id查找其权限
     *
     * @param userId
     * @return
     */
    Set<String> findPermissions(String userId);


    UserDTO findByUserId(String userId);
}
