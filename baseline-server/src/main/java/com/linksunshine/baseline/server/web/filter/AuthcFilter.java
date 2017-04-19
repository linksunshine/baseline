package com.linksunshine.baseline.server.web.filter;

import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ucmed on 2017/4/7.
 */
public class AuthcFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        if (!getSubject(servletRequest, servletResponse).isAuthenticated()) {
            afterAccessDenied(servletRequest, servletResponse);
            return false;
        }
        return true;
    }

    private void afterAccessDenied(ServletRequest request, ServletResponse response) throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("shiro unauth");
    }
}
