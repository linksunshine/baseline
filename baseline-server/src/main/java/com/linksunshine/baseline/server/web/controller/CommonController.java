package com.linksunshine.baseline.server.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommonController {

    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        return "index";
    }
}
