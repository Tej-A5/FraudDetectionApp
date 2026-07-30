package com.finance.frauddetection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FrauddetectionApplication {

	public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FrauddetectionApplication.class, args);
//        System.out.println("======Spring Beans in Application Context======");
//        String[] beanNames = context.getBeanDefinitionNames();
//        for(String beanName : beanNames){
//            System.out.println(beanName);
//        }
//        System.out.println("Total Beans : "+beanNames.length);


	}

}
