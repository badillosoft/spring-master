package com.badillosoft.security;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerMapping;

public class Util {

	public static boolean redirect(HttpServletResponse response, String redirect, boolean error) throws IOException {
    	Cookie cookie = new Cookie("code", error ? "error" : "success");
		cookie.setPath(redirect);
		response.addCookie(cookie);
    	
    	if (redirect != null) {
    		response.sendRedirect(redirect);
    		return true;
    	}
    	
    	return false;
    }
    
    public static void resetCookie(HttpServletResponse response, String redirect) throws IOException {
    	Cookie cookie = new Cookie("code", "ok");
    	if (redirect != null) {
    		cookie.setPath(redirect);
    	}
		response.addCookie(cookie);
    }
    
    public static String getPath(HttpServletRequest request) {
    	return (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
    }
	
}
