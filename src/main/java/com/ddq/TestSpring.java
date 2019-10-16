package com.ddq;

import com.ddq.service.HelloService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring01.xml");
        HelloService helloService = (HelloService) context.getBean("helloService");
        helloService.hello();
    }

}
