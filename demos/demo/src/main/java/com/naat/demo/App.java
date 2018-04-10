package com.naat.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.naat.beans.ClienteBean;
import com.naat.beans.DireccionBean;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("com/naat/xml/beans.xml");
        
        ClienteBean cliente = (ClienteBean) appContext.getBean("cliente");
        
        System.out.printf("[%d] %s\n", cliente.getId(), cliente.getNombre());
        
        DireccionBean direccion = cliente.getDireccion();
        
        System.out.printf("Calle: %s Colonia: %s\n", direccion.getCalle(), direccion.getColonia());
        
        ((ConfigurableApplicationContext) appContext).close();
    }
}
