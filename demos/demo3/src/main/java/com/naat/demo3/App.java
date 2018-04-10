package com.naat.demo3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.naat.beans.*;
import com.naat.services.*;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("com/naat/xml/beans.xml");
        
        ClienteService clienteService = (ClienteService) appContext.getBean("clienteService");
        
        ClienteBean cliente = clienteService.crearCliente("Pepe");
        
        System.out.printf("Cliente [%d]: %s\n", cliente.getId(), cliente.getNombre());
        
        ProductoService productoService = (ProductoService) appContext.getBean("productoService");
        
        ProductoBean producto = productoService.crearProducto("Coca-Cola", "ABC-123");
        
        System.out.printf("Producto [%d]: %s (%s)\n", producto.getId(), producto.getNombre(), producto.getSku());
        
        ((ConfigurableApplicationContext) appContext).close();
    }
}
