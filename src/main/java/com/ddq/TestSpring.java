package com.ddq;

import com.ddq.pojo.JuiceMaker;
import com.ddq.pojo.Source;
import com.ddq.service.HelloService;
import com.ddq.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

public class TestSpring {

//        @Value("${usName}")
//        private String usNmae;
    /*
        以上成员变量（usName）的值，不可以通过@Value 的方式从配置文件中获取
        是因为，TestSpring 这个类的对象并没有交给Spring框架去管理，
        因为 Spring 的启动点是创建IOC容器，即语句：ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        而，在程序启动时，底层虚拟机加载TestSpring这个类时，在给这个成员变量进行初始化的时候，
        还没有执行 Spring 的启动语句，而，只有程序执行了Spring 的启动语句时，才会去读取applicationContext.xml文件创建IOC容器，
        并根据applicationConntext.xml文件中配置的内容创建Bean，以及读取配置文件，并赋值，
        而，由于 TestSpring 这个类的对象并没有交给Spring去管理，
        其实，即使是在TestSpring类上添加@Conponent 注解，企图将TestSpring类对象交给Spring去管理，也是无法实现的，
        因为，当spring 启动，并根据appicationContext.xml 文件 去寻找相关的注解，创建bean 时，TestSpring 类的对象已经创建并执行了，
        所以，TestSpring 的成员变量 usName 永远没有机会通过@Value 方式获取到配置文件中的值。

     */

    /*
        关于程序启动的一些理解：
        所有的程序想要运行，就必须有一个入口，通常是一个main（）函数，
        java程序的入口也是main（）函数，java虚拟机运行时，找的这个程序入口就是main（）方法，
        在此spring项目中，我们并没有显式的定义这个入口函数main()方法，
        但是，我们依然能够正常的启动程序，并且运行项目，这是因为，我们使用了Junit测试框架，
        当我们没有显式的定义main方法时，我们是通过在要执行的方法上加上了@Test注解，
        通过这个@Test注解，就声明了这个方法是一个测试方法，Junit中自身定义了一个main()函数作为程序的入口，
        当我们将一个方法或者类通过Junit的注解声明为一个测试方法或者测试类时，
        运行这个测试方法或者测试类时，使用的程序入口就是Junit中自身定义的main方法。
        所以，如果不给方法添加@Test注解，就必须显式的定义一个main()方法，在这个显式的main()方法中调用我们想要执行的测试方法。
    */

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
            ClassPathXmlApplicationContext用于加载CLASSPATH下的Spring配置文件，此步骤中就已经完成了对所有Bean实例的加载，

            Spring的启动过程，就是其IoC容器的启动过程，本质就是创建和初始化bean的工厂（BeanFactory），
            BeanFactory其实就是整个SpringIoc的核心，Spring 使用 BeanFactory 来实例化、配置和管理 Bean。

            对于web程序，IoC容器启动过程即是建立上下文的过程，在web应用中，web容器会提供一个全局的ServletContext上下文环境，
            ServletContext上下文为Spring IoC提供了一个宿主环境。

            此Spring项目中，如果我们不显式的定义一个mian(）函数作为程序入口，
            则在运行测试类和测试方法时，就是利用Junit自身定义的mian()方法作为程序入口，
            当我们执行测试类或者方法时，虚拟机先找到Junit中定义的main()方法作为程序启动的入口，
            然后再执行Spring框架的创建bean容器的语句，进而完成启动bean 容器，根据传入的spring框架配置文件寻找到配置文件，
            进而根据配置文件中配置的bean的相关信息，去创建bean 对象。。。
            spring框架的启动就是从IOC容器的启动开始的，即以下语句：
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

            详细内容可见以下博客：
            spring入门讲解：
            https://www.cnblogs.com/wmyskxz/p/8820371.html

            【Spring源码分析】Bean加载流程概览：
            https://www.cnblogs.com/xrq730/p/6285358.html

            https://www.jianshu.com/p/11c978c6552d

            spring容器启动过程：
            https://blog.51cto.com/12854546/1930134
            https://blog.csdn.net/weixin_43687167/article/details/87629727
            https://blog.csdn.net/CCCCC_SSSSS/article/details/81483889
            https://blog.csdn.net/u011447614/article/details/82994126
            https://www.jianshu.com/p/96ec66535007

            spring Environment类：
            https://www.jianshu.com/p/5f10192eb958

            spring 中Resource接口详解：
            https://blog.csdn.net/shuxing520/article/details/79345980

            当通过ClassPathApplicationContext初始化容器时，它会根据定位加载spring.xml配置，
            然后解析配置文件，生成Bean，注册Bean，最后我们在通过getBean获取对象，这一现象就跟IOC容器的初始化过程一样，
            资源定位、资源加载、资源解析、生成Bean、Bean注册

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
            context 就是一个IOC容器
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
