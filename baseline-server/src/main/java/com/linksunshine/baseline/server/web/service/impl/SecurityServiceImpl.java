package com.linksunshine.baseline.server.web.service.impl;

import com.linksunshine.baseline.server.web.dto.UserDTO;
import com.linksunshine.baseline.server.web.service.SecurityService;
import com.linksunshine.baseline.server.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ucmed on 2017/3/27.
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserService userService;

    public UserDTO login(UserDTO user) {
        UserDTO usersDTO = userService.findByUsername(user.getUsername());
        if (null == usersDTO) {
            throw new UnknownAccountException();
        }
        UsernamePasswordToken token = new UsernamePasswordToken(
                usersDTO.getUserId(), usersDTO.getPassword());
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        subject.getSession().setAttribute("userid", usersDTO.getUserId());

        usersDTO.setRoles(userService.findRoles(String.valueOf(subject.getPrincipal())));
        usersDTO.setPermissions(userService.findPermissions(String.valueOf(subject.getPrincipal())));
        return usersDTO;
    }
}
