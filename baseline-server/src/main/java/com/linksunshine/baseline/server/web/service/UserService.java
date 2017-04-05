package com.linksunshine.baseline.server.web.service;

import com.linksunshine.baseline.server.web.dto.RoleDTO;
import com.linksunshine.baseline.server.web.dto.UserDTO;

import java.util.List;
import java.util.Set;

/**
 * Created by ucmed on 2017/3/27.
 */
public interface UserService {

    int insert(UserDTO userDTO);


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

    int count();

    List<UserDTO> loadList(int pageNo, int pageSize);

    int countSearch(String searchKey);

    List<UserDTO> search(int pageNo, int pageSize, String searchKey);


    List<String> findUserRole(String userId);

    int insertUpdateRole(UserDTO userDTO);

    int updateUser(UserDTO userDTO);
}
