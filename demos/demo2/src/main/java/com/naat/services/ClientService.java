package com.naat.services;

import org.springframework.stereotype.Service;

import com.naat.beans.ClientBean;

@Service
public class ClientService {

	public void login(ClientBean client) {
		// TODO: Do login into repository
		client.setToken("ABC123");
	}
	
}
