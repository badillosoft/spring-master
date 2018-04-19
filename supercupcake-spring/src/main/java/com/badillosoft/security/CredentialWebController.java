package com.badillosoft.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class CredentialWebController {

	@Autowired
	CredentialRepository credentialRepository;
	
	@Autowired
	CredentialService credentialService;
	
	@GetMapping("")
	public String formLogin(@CookieValue(value="code", defaultValue="ok") String code, Model model,
			@RequestParam(required=false) String redirect, 
    		@RequestParam(required=false) String redirectError,
    		HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		Util.resetCookie(response, Util.getPath(request));
		
		model.addAttribute("code", code);
		
		return "auth/formLogin";
	}
	
}
