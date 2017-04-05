package com.linksunshine.baseline.server.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.linksunshine.baseline.server.web.core.orika.OrikaBeanMapper;
import com.linksunshine.baseline.server.web.dao.UserMapper;
import com.linksunshine.baseline.server.web.dao.UserRoleMapper;
import com.linksunshine.baseline.server.web.dto.UserDTO;
import com.linksunshine.baseline.server.web.model.Role;
import com.linksunshine.baseline.server.web.model.RolePermission;
import com.linksunshine.baseline.server.web.model.User;
import com.linksunshine.baseline.server.web.model.UserRole;
import com.linksunshine.baseline.server.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by ucmed on 2017/3/27.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private OrikaBeanMapper orikaBeanMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    public int insert(UserDTO userDTO) {

        userDTO.setPassword("123456");
        userDTO.setSalt("11111");

        User user = orikaBeanMapper.map(userDTO, User.class);
        user.setUserId(UUID.randomUUID().toString());
        user.setCreatedby("admin");
        user.setCreatedon(new Date());
        user.setModifiedby("admin");
        user.setModifiedon(new Date());
        user.setDeletionState("0");
        return userMapper.insertSelective(user);
    }

    public UserDTO findByUsername(String username) {
        User users = new User();
        users.setUsername(username);
        users.setDeletionState("0");
        return orikaBeanMapper.map(userMapper.selectOne(users), UserDTO.class);
    }

    /**
     * 根据用户Id查找其角色
     *
     * @param userId
     * @return
     */
    public Set<String> findRoles(String userId) {
        return userMapper.findRoles(userId);
    }


    /**
     * 根据用户id查找其权限
     *
     * @param userId
     * @return
     */
    public Set<String> findPermissions(String userId) {
        return userMapper.findPermissions(userId);
    }


    public UserDTO findByUserId(String userId) {
        User model = new User();
        model.setUserId(userId);
        model.setDeletionState("0");
        return orikaBeanMapper.map(userMapper.selectOne(model), UserDTO.class);
    }


    public int count() {
        User user = new User();
        user.setDeletionState("0");
        return userMapper.selectCount(user);
    }

    public List<UserDTO> loadList(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        User user = new User();
        user.setDeletionState("0");
        Example example = new Example(User.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria().andEqualTo(user);
        return orikaBeanMapper.mapAsList(userMapper.selectByExample(example), UserDTO.class);
    }

    public int countSearch(String searchKey) {
        Example example = new Example(User.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria()
                .andCondition("username like '%" + searchKey + "%'")
                .andCondition("surname like '%" + searchKey + "%'")
                .andCondition("email like '%" + searchKey + "%'")
                .andCondition("mobile like '%" + searchKey + "%'")
                .andEqualTo("deletionState", "0");
        return userMapper.selectCountByExample(example);
    }

    public List<UserDTO> search(int pageNo, int pageSize, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(User.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria()
                .andCondition("username like '%" + searchKey + "%'")
                .andCondition("surname like '%" + searchKey + "%'")
                .andCondition("email like '%" + searchKey + "%'")
                .andCondition("mobile like '%" + searchKey + "%'")
                .andEqualTo("deletionState", "0");
        return orikaBeanMapper.mapAsList(userMapper.selectByExample(example), UserDTO.class);
    }

    public List<String> findUserRole(String userId) {
        return userRoleMapper.findUserRole(userId);
    }

    public int insertUpdateRole(UserDTO userDTO) {

        UserRole userRole = new UserRole();
        userRole.setpUserId(userDTO.getUserId());
        userRole.setDeletionState("0");
        List<UserRole> userRoleList = userRoleMapper.select(userRole);
        List<UserRole> list = new ArrayList<UserRole>();
        for (String roleId : userDTO.getUserRoles()) {
            boolean isAdd = true;
            for (UserRole ur : userRoleList) {
                if (roleId.equals(ur.getpRoleId())) {
                    isAdd = false;
                    break;
                }
            }
            if (isAdd) {
                UserRole u = new UserRole();
                u.setpUserRoleId(UUID.randomUUID().toString());
                u.setpUserId(userDTO.getUserId());
                u.setpRoleId(roleId);
                u.setCreatedby("admin");
                u.setCreatedon(new Date());
                u.setModifiedby("admin");
                u.setModifiedon(new Date());
                u.setDeletionState("0");
                list.add(u);
            }
        }


        return userRoleMapper.insertUpdateRole(list);
    }

    public int updateUser(UserDTO userDTO) {
        userDTO.setModifiedon(new Date());
        return userMapper.updateByPrimaryKeySelective(orikaBeanMapper.map(userDTO, User.class));
    }
}

