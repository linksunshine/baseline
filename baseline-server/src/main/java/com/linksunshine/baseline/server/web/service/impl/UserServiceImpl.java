package com.linksunshine.baseline.server.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.linksunshine.baseline.server.web.core.orika.OrikaBeanMapper;
import com.linksunshine.baseline.server.web.dao.UserMapper;
import com.linksunshine.baseline.server.web.dto.UserDTO;
import com.linksunshine.baseline.server.web.model.User;
import com.linksunshine.baseline.server.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;

/**
 * Created by ucmed on 2017/3/27.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private OrikaBeanMapper orikaBeanMapper;
    @Autowired
    private UserMapper userMapper;

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

    public List<UserDTO> loadList() {
        User user = new User();
        user.setDeletionState("0");
        Example example = new Example(User.class);
        example.setOrderByClause("createdon desc");//注意用的是类中的属性，不是数据库中的属性
        example.createCriteria().andEqualTo(user);
        return orikaBeanMapper.mapAsList(userMapper.selectByExample(example), UserDTO.class);
    }
}

