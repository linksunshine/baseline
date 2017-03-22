package com.linksunshine.baseline.server.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * ������ͼ������
 *
 * @author starzou
 * @since 2014��4��15�� ����4:16:34
 **/
@Controller
public class CommonController {
    /**
     * ��ҳ
     *
     * @param request
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        return "index";
    }
}
