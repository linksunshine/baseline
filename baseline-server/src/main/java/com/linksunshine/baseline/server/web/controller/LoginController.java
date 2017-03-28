package com.linksunshine.baseline.server.web.controller;

import com.linksunshine.baseline.server.web.dto.UserDTO;
import com.linksunshine.baseline.server.web.service.SecurityService;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ucmed on 2017/3/20.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private static final Logger LOGGER = Logger.getLogger("LoginController");

    private static final String ACCOUNT_OR_PASSWORD_ERROR = "账号或密码不正确。";

    @Autowired
    private SecurityService securityService;

    @RequestMapping(method = RequestMethod.POST)
    public UserDTO login(@RequestBody UserDTO user) {

        UserDTO userForAuth = null;

        try {
            userForAuth = securityService.login(user);
            userForAuth.setMessageCode("200");
            userForAuth.setMessageInfo("登录成功");
            return userForAuth;
        } catch (ExcessiveAttemptsException eaEx) {
            LOGGER.error("", eaEx);
            userForAuth.setMessageCode("201");
            userForAuth.setMessageInfo(ACCOUNT_OR_PASSWORD_ERROR);
            return userForAuth;
        } catch (UnknownAccountException uaEX) {
            LOGGER.error("", uaEX);
            userForAuth.setMessageCode("202");
            userForAuth.setMessageInfo(ACCOUNT_OR_PASSWORD_ERROR);
            return userForAuth;
        } catch (AuthenticationException aEx) {
            LOGGER.error("", aEx);
            userForAuth.setMessageCode("203");
            userForAuth.setMessageInfo(ACCOUNT_OR_PASSWORD_ERROR);
            return userForAuth;
        } catch (Exception ex) {
            LOGGER.error("", ex);
            userForAuth.setMessageCode("204");
            userForAuth.setMessageInfo(ACCOUNT_OR_PASSWORD_ERROR);
            return userForAuth;
        }

    }
}
