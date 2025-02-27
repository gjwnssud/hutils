package com.hzn.hutils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 10. 31.
 */
public class IpChecker {

    public static String resolveClientIp() {
        return resolveClientIp(Rch.getRequest());
    }

    public static String resolveClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
