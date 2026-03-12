package com.kinoxp.security;

import com.kinoxp.model.user.Role;
import com.kinoxp.model.user.User;
import com.kinoxp.service.UserService;

public class AdminChecker {
    public static boolean isAdmin(UserService userService, Long userId){
        User user = userService.findById(userId);
        return user !=null && user.getRole() == Role.ADMIN;
    }
}
