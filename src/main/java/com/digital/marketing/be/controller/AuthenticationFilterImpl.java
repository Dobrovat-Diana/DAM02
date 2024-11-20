package com.digital.marketing.be.controller;

import com.digital.marketing.be.repository.entity.UserEntity;
import com.digital.marketing.be.service.UserService;
import com.digital.marketing.be.service.dto.SecurityUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static com.digital.marketing.be.service.dto.Constants.SESSION_ID_KEY;

public class AuthenticationFilterImpl extends GenericFilterBean {

    private UserService userService;

    public AuthenticationFilterImpl(UserService userService) {
        this.userService = userService;
    }

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        Cookie[] cookies = request.getCookies();
        Cookie sessionIdCookie = null;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(SESSION_ID_KEY)) {
                    sessionIdCookie = cookie;
                }
            }
            if (sessionIdCookie != null) {
                UserEntity userEntity = userService.getUserBySessionId(sessionIdCookie.getValue());
                if (userEntity != null) {
                    SecurityUser securityUser = new SecurityUser(userEntity, true);
                    SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
                    context.setAuthentication(securityUser);
                    this.securityContextHolderStrategy.setContext(context);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
