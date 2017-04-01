package com.linksunshine.baseline.server.web.controller;

import com.linksunshine.baseline.server.web.dto.PermissionDTO;
import com.linksunshine.baseline.server.web.dto.UserDTO;
import com.linksunshine.baseline.server.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ucmed on 2017/3/20.
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public ResponseEntity<PermissionDTO> add(@RequestBody PermissionDTO permissionDTO) {
        return new ResponseEntity<PermissionDTO>(permissionDTO, HttpStatus.OK);
    }
}
