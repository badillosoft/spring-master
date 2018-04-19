package com.badillosoft.security;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

	@Autowired
	CredentialRepository credentialRepository;

    final Long EXPIRATION_TIME = 24l * 60l * 60l;

    public Credential createCredential(Credential credential) {
    	Timestamp date = Timestamp.valueOf(LocalDateTime.now());
    	
    	credential.setLastLogin(date);
    	credential.setLastUpdate(date);
    	credential.setToken(credentialRepository.randomToken());
    	credential.setLogin(true);
    	
        credentialRepository.save(credential);

        return credential;
    }

    public Credential doLogin(Credential credential) {
        Optional<Credential> authenticOptional = credentialRepository.checkCredentialAndRole(credential.getEmail(), credential.getPassword(), credential.getRole());

        if (!authenticOptional.isPresent()) {
            return null;
        }

        Credential authentic = authenticOptional.get();

        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        
        authentic.setLastLogin(date);
        authentic.setLastUpdate(date);
    	authentic.setToken(credentialRepository.randomToken());
    	authentic.setLogin(true);
    	authentic.setDevice(credential.getDevice());

        credentialRepository.save(authentic);

        return authentic;
    }

    public Credential refreshToken(Credential credential) {
        Optional<Credential> authenticOptional = credentialRepository.checkTokenWithDeviceAndExpiration(credential.getEmail(), credential.getToken(), credential.getDevice(), EXPIRATION_TIME);

        if (!authenticOptional.isPresent()) {
            return null;
        }

        Credential authentic = authenticOptional.get();

        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        
        authentic.setLastUpdate(date);
    	authentic.setToken(credentialRepository.randomToken());
    	authentic.setLogin(true);

        credentialRepository.save(authentic);

        return authentic;
    }

    public Credential getCredential(Credential credential) {
        Optional<Credential> authenticOptional = credentialRepository.checkTokenWithDeviceAndExpiration(credential.getEmail(), credential.getToken(), credential.getDevice(), EXPIRATION_TIME);

        if (!authenticOptional.isPresent()) {
            return null;
        }

        Credential authentic = authenticOptional.get();

        return authentic;
    }

    public Credential doLogout(Credential credential) {
        Optional<Credential> authenticOptional = credentialRepository.checkTokenWithDeviceAndExpiration(credential.getEmail(), credential.getToken(), credential.getDevice(), EXPIRATION_TIME);

        if (!authenticOptional.isPresent()) {
            return null;
        }

        Credential authentic = authenticOptional.get();
        
    	authentic.setToken(credentialRepository.randomToken());
    	authentic.setLogin(false);

        credentialRepository.save(authentic);

        return authentic;
    }
    
    public boolean validateCredential(Credential credential) {
    	return getCredential(credential) != null;
    }

}
