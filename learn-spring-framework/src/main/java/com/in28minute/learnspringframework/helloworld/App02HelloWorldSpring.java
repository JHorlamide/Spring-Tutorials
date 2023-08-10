package com.in28minute.learnspringframework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {
    public static void main(String[] args) {
        try(var context = //1: Launch a spring context:
                    new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        ) {
            System.out.println(context.getBean("name"));
            System.out.println(context.getBean("age"));
            System.out.println(context.getBean("person"));
            System.out.println(context.getBean("person2MethodCall"));
            System.out.println(context.getBean("person3Parameter"));
            System.out.println(context.getBean(Address.class));

            String[] allBeans = context.getBeanDefinitionNames();
            int allBeanCount = context.getBeanDefinitionCount();

            for(int i = 0; i < allBeanCount; i++) {
                System.out.println("Index: " + allBeans[i]);
            }

//        Arrays.stream(allBeans).forEach(System.out::println);
        }
    }
}
