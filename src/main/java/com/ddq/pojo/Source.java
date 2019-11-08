package com.ddq.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

// @PropertySource("classpath:config.properties")
/*
    通过@PropertySource注解将properties配置文件中的值存储到Spring的 Environment中，
    Environment接口提供方法去读取配置文件中的值，参数是properties文件中定义的key值
    这个问题产生的原因是：spring在读取∗.properties文件时,默认使用的是asci码
 */
public class Source {
    /*
        定义一个bean，用于对桔汁进行抽象（建模）

     */
    private String fruit; // 类型
    private String sugar; // 糖
    @Value("${size}")
    /*
     通过Spring的 <context:property-placeholder /> 标签配合@value 注解来获取到配置文件中的值（此情况下不需要再添加类上面的@PropertySource 注解）
     注意：在使用@Value注解，不管是组合哪一个标签或注解，来获取配置文件中的值，如果配置文件中有中文，
           则获取到的值，都有很大可能会是乱码，这个很容易导致问题，需要注意。
      */
    private String size; // 大小杯

    public void setFruit(String fruit){
        this.fruit = fruit;
    }

    public String getFruit(){
        return fruit;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getSugar(){
        return sugar;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize(){
        return size;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    /*
       如果要使用@PropertySource + @Value 来获取配置文件的值（此时不需要再在 applicationContext.xml文件中添加<context：property-holder />标签）
       就必须要添加这个bean，
       //要想使用@Value 用${}占位符注入属性，这个bean是必须的，这个就是占位bean,

     */
}
