/*
 * Learning Spring Application Development 
 * Ravi Kant Soni
 * 2015
 */
package org.springframework.chapter1.main;

import org.springframework.chapter1.service.GreetingMessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * xml files should be in src/main/resources, during compile time, maven will add all the resources ...
 * 
 * @since 105-11
 * 
 * 
 */
public class MainClass {
    
    public static void main(String[] args) {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        GreetingMessageService greetingMessageService = context.getBean("greetingMessageServiceImpl", GreetingMessageService.class);
        System.out.println(greetingMessageService.getUser());
    }
    
}