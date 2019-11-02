package com.mage.crm.util;

import javax.servlet.http.HttpServletRequest;

public class UserLoginUtil {
    public static String realseUserId(HttpServletRequest request) {
        return CookieUtil.getCookieValue(request, "userId");
    }
}
