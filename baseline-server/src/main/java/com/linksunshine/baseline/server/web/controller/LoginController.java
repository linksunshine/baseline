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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
    @ResponseBody
    public UserDTO login(@RequestBody UserDTO user,HttpServletRequest request) {

        UserDTO userForAuth = null;

        try {
            /*userForAuth = securityService.login(user);*/
            userForAuth=new UserDTO();
            userForAuth.setMessageCode("200");
            userForAuth.setMessageInfo("登录成功");


            userForAuth.setUserId(UUID.randomUUID().toString());
            Set<String> permissions=new HashSet<String>();
            permissions.add("S:1");
            permissions.add("U:1");
            userForAuth.setPermissions(permissions);

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
