package com.ddq;

import com.ddq.pojo.JuiceMaker;
import com.ddq.pojo.Source;
import com.ddq.service.HelloService;
import com.ddq.service.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        /*
            控制反转：创建对象交由spring框架来完成  Inverse of Control（控制反转）
            读作“反转控制”，更好理解，不是什么技术，而是一种设计思想，就是将原本在程序中手动创建对象的控制权，交由Spring框架来管理。
            正控：若要使用某个对象，需要自己去负责对象的创建
            反控：若要使用某个对象，只需要从 Spring 容器中获取需要使用的对象，不关心对象的创建过程，也就是把创建对象的控制权反转给了Spring框架
            好莱坞法则：Don’t call me ,I’ll call you
            以上创建ClassPathXmlApplicationContext对象的语句就是创建IOC容器的，IOC容器是创建，管理bean的。
         */
        HelloService helloService = (HelloService) context.getBean("helloService");
        /*
            此处获取对象后，还进行了一次向下转型，是由于：spring框架创建对象的底层实现中，
            创建对象后，返回的是一个object类型的对象，此处用到了向上转型，所以，在返回对象后，还需要进行下向下转型。
         */
        helloService.hello();
    }

    @Test
    public void test02(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        /*
            ApplicationContext 是一个接口
            ClassPathXmlApplicationContext 是一个接口实现类（但是并不是直接实现Application接口）
            以上语句是利用了java的多态特性
         */
        ProductService pS = (ProductService) context.getBean("productService");
        pS.doSomeService();

    }

    @Test
    public void test03(){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        Source source = (Source) context.getBean("source");
        System.out.println(source.getFruit());
        System.out.println(source.getSugar());
        System.out.println(source.getSize());

        JuiceMaker juiceMaker = (JuiceMaker) context.getBean("juiceMaker");
        System.out.println(juiceMaker.juiceMake());

    }

}
