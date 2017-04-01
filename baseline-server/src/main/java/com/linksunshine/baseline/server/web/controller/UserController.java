package com.linksunshine.baseline.server.web.controller;

import com.linksunshine.baseline.server.web.dto.UserDTO;
import com.linksunshine.baseline.server.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ucmed on 2017/3/20.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<UserDTO>> list() {
        return new ResponseEntity<List<UserDTO>>(userService.loadList(), HttpStatus.OK);
    }
}
