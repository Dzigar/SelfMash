package com.selfmash.interceptors;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.selfmash.model.User;
import com.selfmash.service.NotificationService;
import com.selfmash.service.UserService;

public class UserPageInterceptor extends HandlerInterceptorAdapter {

    /**
     * UserService.
     */
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "NotificationService")
    private NotificationService notificationService;

    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * List following users
     */
    private List<User> following;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("authentication", getAuthenticationUserName());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        try {
            following = userService.getFollowing(userService.getUserByLogin(
                    getAuthenticationUserName()).getId());
            modelAndView.addObject("following", following);
            if (subsrcibed(request.getSession().getAttribute("userLogin")
                    .toString())) {
                modelAndView.addObject("isSubscribed", true);
            }

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }

        try {
            int notificationCount = notificationService
                    .getNotificationsByUserId(
                            userService.getUserByLogin(
                                    getAuthenticationUserName()).getId())
                    .size();
            if (notificationCount != 0) {
                modelAndView.addObject("notificationCount", notificationCount);
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    private String getAuthenticationUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * 
     * @param userLogin
     *            - User login
     * @return true if user login is listed and return false if user login not's
     *         listed
     */
    private boolean subsrcibed(String userLogin) {
        for (User user : following) {
            if (user.getLogin().equals(userLogin)) {
                return true;
            }
        }
        return false;
    }
}