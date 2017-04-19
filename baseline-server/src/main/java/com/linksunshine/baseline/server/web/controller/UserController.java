package com.linksunshine.baseline.server.web.controller;

import com.linksunshine.baseline.server.web.commons.constants.PermissionConstants;
import com.linksunshine.baseline.server.web.dto.*;
import com.linksunshine.baseline.server.web.service.RoleService;
import com.linksunshine.baseline.server.web.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ucmed on 2017/3/20.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequiresPermissions(value = {
            PermissionConstants.USER_CREATE},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public ResponseEntity<UserDTO> add(@RequestBody UserDTO userDTO) {
        userService.insert(userDTO);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @RequiresPermissions(value = {
            PermissionConstants.USER_VIEW},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<ReturnBodyDTO> list(@RequestParam int pageNo, @RequestParam int pageSize) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotalRecord(userService.count());
        ReturnBodyDTO returnBodyDTO = new ReturnBodyDTO();
        returnBodyDTO.setPage(pageDTO);
        returnBodyDTO.setData(userService.loadList(pageNo, pageSize));
        returnBodyDTO.setStatus(200);

        return new ResponseEntity<ReturnBodyDTO>(returnBodyDTO, HttpStatus.OK);
    }


    @RequiresPermissions(value = {
            PermissionConstants.USER_SEARCH},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<ReturnBodyDTO> search(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchKey) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotalRecord(userService.countSearch(searchKey));
        ReturnBodyDTO returnBodyDTO = new ReturnBodyDTO();
        returnBodyDTO.setPage(pageDTO);
        returnBodyDTO.setData(userService.search(pageNo, pageSize, searchKey));
        returnBodyDTO.setStatus(200);

        return new ResponseEntity<ReturnBodyDTO>(returnBodyDTO, HttpStatus.OK);
    }

    @RequiresPermissions(value = {
            PermissionConstants.USER_ALLOCATE},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = "/role")
    @ResponseBody
    public ResponseEntity<UserDTO> roleAllocate(@RequestBody UserDTO userDTO) {
        //获取角色列表
        List<RoleDTO> roleDTOs = roleService.loadList();
        userDTO.setRoleDTOs(roleDTOs);
        //获取用户对应的角色列表
        userDTO.setUserRoles(userService.findUserRole(userDTO.getUserId()));
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @RequiresPermissions(value = {
            PermissionConstants.USER_ALLOCATE},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = "/allocate/role")
    @ResponseBody
    public ResponseEntity<Integer> insertUpdateRole(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<Integer>(userService.insertUpdateRole(userDTO), HttpStatus.OK);
    }


    @RequiresPermissions(value = {
            PermissionConstants.USER_UPDATE},
            logical = Logical.OR)
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public ResponseEntity<Integer> updateUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<Integer>(userService.updateUser(userDTO), HttpStatus.OK);
    }

}
