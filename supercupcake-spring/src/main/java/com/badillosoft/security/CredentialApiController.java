package com.badillosoft.security;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class CredentialApiController {

    @Autowired
    CredentialService credentialService;

    @PostMapping("/login")
    @ResponseBody
    public Credential doLogin(@RequestParam String email, @RequestParam String password, 
    		@RequestParam(defaultValue="EMPTY-DEVICE") String device, 
    		@RequestParam(defaultValue="EMPTY-ROLE") String role, 
    		@RequestParam(required=false) String redirect, 
    		@RequestParam(required=false) String redirectError,
    		HttpServletResponse response) throws IOException {
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setPassword(password);
        credential.setDevice(device);
        credential.setRole(role);
        
        System.out.println(role);

        Credential authentic = credentialService.doLogin(credential);
        
        Util.resetCookie(response, redirect);
        Util.resetCookie(response, redirectError);

        if (authentic == null) {
        	if (Util.redirect(response, redirectError, true)) {
        		return null;
        	}
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Unathorized");
            return null;
        }
        
        if (Util.redirect(response, redirect, false)) {
    		return null;
    	}

        return authentic;
    }

    @GetMapping("/logout")
    @ResponseBody
    public Credential doLogout(@RequestParam String email, @RequestParam String token, 
    		@RequestParam(defaultValue="EMPTY-DEVICE") String device,
    		@RequestParam(required=false) String redirect, @RequestParam(required=false) String redirectError,
    		HttpServletResponse response) throws IOException {
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setToken(token);
        credential.setDevice(device);

        Credential authentic = credentialService.doLogout(credential);
        
        Util.resetCookie(response, redirect);
        Util.resetCookie(response, redirectError);

        if (authentic == null) {
        	if (Util.redirect(response, redirectError, true)) {
        		return null;
        	}
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Unathorized");
            return null;
        }
        
        if (Util.redirect(response, redirect, false)) {
    		return null;
    	}

        return authentic;
    }

    @GetMapping("/token")
    @ResponseBody
    public Credential refreshToken(@RequestParam String email, @RequestParam String token,
    		@RequestParam(defaultValue="EMPTY-DEVICE") String device,
    		@RequestParam(required=false) String redirect, @RequestParam(required=false) String redirectError,
    		HttpServletResponse response) throws IOException {
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setToken(token);
        credential.setDevice(device);

        Credential authentic = credentialService.refreshToken(credential);
        
        Util.resetCookie(response, redirect);
        Util.resetCookie(response, redirectError);

        if (authentic == null) {
        	if (Util.redirect(response, redirectError, true)) {
        		return null;
        	}
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Token has been expired or revoked");
            return null;
        }
        
        if (Util.redirect(response, redirect, false)) {
    		return null;
    	}

        return authentic;
    }

    @PutMapping("")
    @ResponseBody
    public Credential createCredential(@RequestParam String email, @RequestParam String password,
    		@RequestParam(defaultValue="EMPTY-DEVICE") String device, @RequestParam(defaultValue="EMPTY-ROLE") String role,
    		@RequestParam(required=false) String redirect, @RequestParam(required=false) String redirectError,
    		HttpServletResponse response) throws IOException {
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setPassword(password);
        credential.setDevice(device);
        credential.setRole(role);

        Credential authentic = credentialService.createCredential(credential);
        
        Util.resetCookie(response, redirect);
        Util.resetCookie(response, redirectError);

        if (authentic == null) {
        	if (Util.redirect(response, redirectError, true)) {
        		return null;
        	}
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error creating credential");
            return null;
        }
        
        if (Util.redirect(response, redirect, false)) {
    		return null;
    	}

        return authentic;
    }

    @GetMapping("")
    @ResponseBody
    public Credential getCredential(@RequestParam String email, @RequestParam String token,
    		@RequestParam(defaultValue="EMPTY-DEVICE") String device,
    		@RequestParam(required=false) String redirect, @RequestParam(required=false) String redirectError,
    		HttpServletResponse response) throws IOException {
        Credential credential = new Credential();
        credential.setEmail(email);
        credential.setToken(token);
        credential.setDevice(device);

        Credential authentic = credentialService.getCredential(credential);
        
        Util.resetCookie(response, redirect);
        Util.resetCookie(response, redirectError);

        if (authentic == null) {
        	if (Util.redirect(response, redirectError, true)) {
        		return null;
        	}
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Token has been expired or revoked");
            return null;
        }
        
        if (Util.redirect(response, redirect, false)) {
    		return null;
    	}

        return authentic;
    }

}
