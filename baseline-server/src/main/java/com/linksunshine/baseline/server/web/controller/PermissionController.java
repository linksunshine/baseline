package com.linksunshine.baseline.server.web.controller;

import com.linksunshine.baseline.server.web.dto.PageDTO;
import com.linksunshine.baseline.server.web.dto.PermissionDTO;
import com.linksunshine.baseline.server.web.dto.ReturnBodyDTO;
import com.linksunshine.baseline.server.web.dto.RoleDTO;
import com.linksunshine.baseline.server.web.service.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public ResponseEntity<PermissionDTO> add(@RequestBody PermissionDTO permissionDTO) {
        permissionService.insert(permissionDTO);
        return new ResponseEntity<PermissionDTO>(permissionDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<ReturnBodyDTO> list(@RequestParam int pageNo, @RequestParam int pageSize) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotalRecord(permissionService.count());
        ReturnBodyDTO returnBodyDTO = new ReturnBodyDTO();
        returnBodyDTO.setPage(pageDTO);
        returnBodyDTO.setData(permissionService.loadList(pageNo, pageSize));
        returnBodyDTO.setStatus(200);

        return new ResponseEntity<ReturnBodyDTO>(returnBodyDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<ReturnBodyDTO> search(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchKey) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotalRecord(permissionService.countSearch(searchKey));
        ReturnBodyDTO returnBodyDTO = new ReturnBodyDTO();
        returnBodyDTO.setPage(pageDTO);
        returnBodyDTO.setData(permissionService.search(pageNo, pageSize, searchKey));
        returnBodyDTO.setStatus(200);

        return new ResponseEntity<ReturnBodyDTO>(returnBodyDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public ResponseEntity<Integer> updatePermission(@RequestBody PermissionDTO permissionDTO) {
        return new ResponseEntity<Integer>(permissionService.updatePermission(permissionDTO), HttpStatus.OK);
    }

}
