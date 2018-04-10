package com.naat.services;

import org.springframework.stereotype.Service;

import com.naat.beans.*;

@Service
public class PrintService {

	public void print(ClientBean client) {
		System.out.printf("Client [%d]: %s (%s)\n", client.getId(), client.getName(), client.getToken());
	}
	
}
