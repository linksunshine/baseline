package com.linksunshine.baseline.server.web.service;

import com.linksunshine.baseline.server.web.dto.UserDTO;

/**
 * Created by ucmed on 2017/3/27.
 */
public interface SecurityService {
    UserDTO login(UserDTO user);
}
