package com.naat.demo2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.naat.beans.ClientBean;
import com.naat.services.ClientService;
import com.naat.services.PrintService;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("com/naat/xml/beans.xml");
        
        ClientBean client = new ClientBean();
        
        client.setId(5);
        client.setName("Homer Simpson");
        
        PrintService printService = (PrintService) appContext.getBean("printService");
        
        printService.print(client);
        
        ClientService clientService = (ClientService) appContext.getBean("clientService");
        
        clientService.login(client);
        
        printService.print(client);
        
        ((ConfigurableApplicationContext) appContext).close();
    }
}
