package com.ddq.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/*
    ProceedingJoinPoint的概述：
    （1）它是一个对象；
    （2）作为参数：定义增强处理方法时，将第一个参数定义为该类型，当该增强处理方法被调用时，该类型的对象就代表织入增强处理的连接点。
    （3）该对象常用方法：
    1，Object[ ]  getArgs ：返回目标方法的参数；
    2，Signature getSignature：返回目标方法的签名；
    3，Object getTarget ：返回织入增强处理的目标对象；
    4，Object getThis ： 返回AOP框架为目标对象生成的代理对象；
    注意：当使用@Around处理时，我们需要将第一个参数定义为ProceedingJoinPoint类型，该类是JoinPoint的子类。
        ————————————————
        版权声明：本文为CSDN博主「qq_38793958邓鑫涛」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/qq_38793958/article/details/82108574
 */

/*
    Spring AOP 简介
    如果说 IoC 是 Spring 的核心，那么面向切面编程就是 Spring 最为重要的功能之一了，在数据库事务中切面编程被广泛使用。

    AOP 即 Aspect Oriented Program 面向切面编程
    首先，在面向切面编程的思想里面，把功能分为核心业务功能，和周边功能。

    所谓的核心业务，比如登陆，增加数据，删除数据都叫核心业务
    所谓的周边功能，比如性能统计，日志，事务管理等等
    周边功能在 Spring 的面向切面编程AOP思想里，即被定义为切面

    在面向切面编程AOP的思想里面，核心业务功能和切面功能分别独立进行开发，然后把切面功能和核心业务功能 "编织" 在一起，这就叫AOP

    AOP 的目的
    AOP能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来，
    便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。

    AOP 当中的概念：
    切入点（Pointcut）
        在哪些类，哪些方法上切入（where）
    通知（Advice）
        在方法执行的什么实际（when:方法前/方法后/方法前后）做什么（what:增强的功能）
    切面（Aspect）
        切面 = 切入点 + 通知，通俗点就是：在什么时机，什么地方，做什么增强！
    织入（Weaving）
        把切面加入到对象，并创建出代理对象的过程。（由 Spring 来完成）

    Spring中实现aop的方式有三种;
    （1） 基于xml配置方式
    （2）spring纯注解方式配置aop
    （3）动态代理方式实现aop

    此处，即使通过xml方式配置aop:
    （1）创建业务逻辑接口以及其具体实现类
    （2）创建切面类
    （3）在spring的xml配置文件中配置aop：在 xml 文件中声明业务对象和日志切面
    （4）进行测试

    此文件是日志切面类
    切面类相当于就是定义了一个切面，也就是定义了一个功能，该功能是一个周边业务。

 */

public class LoggerAspect {
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("start log:" + joinPoint.getSignature().getName());
        Object object = joinPoint.proceed();
        System.out.println("end log" + joinPoint.getSignature().getName());
        return object;
    }


}
