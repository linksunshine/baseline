package com.linksunshine.baseline.server.web.controller;

import com.linksunshine.baseline.server.web.model.Organization;
import com.linksunshine.baseline.server.web.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ucmed on 2017/3/20.
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

    @Autowired
    @Resource
    private OrganizationService organizationService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<Organization>> list() {
        return new ResponseEntity<List<Organization>>(organizationService.loadList(), HttpStatus.OK);
    }
}
