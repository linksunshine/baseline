package com.linksunshine.baseline.server.web.controller;

import com.linksunshine.baseline.server.web.dto.*;
import com.linksunshine.baseline.server.web.service.PermissionService;
import com.linksunshine.baseline.server.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ucmed on 2017/3/20.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public ResponseEntity<RoleDTO> add(@RequestBody RoleDTO roleDTO) {
        roleService.insert(roleDTO);
        return new ResponseEntity<RoleDTO>(roleDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<ReturnBodyDTO> list(@RequestParam int pageNo, @RequestParam int pageSize) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotalRecord(roleService.count());
        ReturnBodyDTO returnBodyDTO = new ReturnBodyDTO();
        returnBodyDTO.setPage(pageDTO);
        returnBodyDTO.setData(roleService.loadList(pageNo, pageSize));
        returnBodyDTO.setStatus(200);

        return new ResponseEntity<ReturnBodyDTO>(returnBodyDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<ReturnBodyDTO> search(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchKey) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotalRecord(roleService.countSearch(searchKey));
        ReturnBodyDTO returnBodyDTO = new ReturnBodyDTO();
        returnBodyDTO.setPage(pageDTO);
        returnBodyDTO.setData(roleService.search(pageNo, pageSize, searchKey));
        returnBodyDTO.setStatus(200);

        return new ResponseEntity<ReturnBodyDTO>(returnBodyDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/permission")
    @ResponseBody
    public ResponseEntity<RoleDTO> permissionAllocate(@RequestBody RoleDTO roleDTO) {
        //获取资源列表
        List<PermissionDTO> permissionDTOs = permissionService.loadList();
        roleDTO.setPermissionDTOs(permissionDTOs);
        //获取角色对应的资源列表
        roleDTO.setRolePermissions(roleService.findRolePermission(roleDTO.getpRoleId()));
        return new ResponseEntity<RoleDTO>(roleDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/allocate/permission")
    @ResponseBody
    public ResponseEntity<Integer> insertUpdatePermission(@RequestBody RoleDTO roleDTO) {
        return new ResponseEntity<Integer>(roleService.insertUpdatePermission(roleDTO), HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public ResponseEntity<Integer> updateRole(@RequestBody RoleDTO roleDTO) {
        return new ResponseEntity<Integer>(roleService.updateRole(roleDTO), HttpStatus.OK);
    }


}
