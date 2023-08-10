package com.in28minute.learnspringframework.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person (String name, int age) {}

record Address(String firstLine, String city) {}

//2: Configure the things that we want spring framework to manage - @Configuration
@Configuration
public class HelloWorldConfiguration {
    @Bean
    public String name() {
        return "Olamide Jubril";
    }

    @Bean
    public int age() {
        return 20;
    }

    @Bean
    public Person person() {
        return new Person("Olamide", 25);
    }

    @Bean
    @Primary
    public Person person2MethodCall() {
        return new Person(name(), age());
    }

    @Bean
    public Person person3Parameter(String name, int age, Address address) {
        return new Person(name, age);
    }

    @Bean
    public Address address() {
        return new Address("My address firstLine", "Lagos");
    }
}
