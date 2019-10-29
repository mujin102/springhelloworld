package com.ddq.pojo;

public class JuiceMaker {
    private Source source = null; // 关联了一个Source对象
    public void setSource(Source source){
        this.source = source;
    }
    public Source getSource(){
        return source;
    }

    /*
        该JavaBean的属性source 的值是通过配置文件进行赋值的，此例子演示了spring的依赖注入
        IoC 和 DI 其实是同一个概念的不同角度描述，DI 相对 IoC 而言，明确描述了“被注入对象依赖 IoC 容器配置依赖对象”

        IoC 如何实现的
            最后我们简单说说IoC是如何实现的。想象一下如果我们自己来实现这个依赖注入的功能，我们怎么来做？ 无外乎：
                读取标注或者配置文件，看看JuiceMaker依赖的是哪个Source，拿到类名
                使用反射的API，基于类名实例化对应的对象实例
                将对象实例，通过构造函数或者 setter，传递给 JuiceMaker
        注：以上说明中提到的通过构造函数对对象进行实例化，就是最基础的控制反转中创建实例时使用的 构造器方式实例化bean对象。
            即：
                <bean id="userServiceImpl" class="com.shsxt.service.impl.UserServiceImpl"></bean>
            该种方式配置bean，则创建bean实例时就是使用构造器创建bean实例的，
            该种方式创建bean实例，则要求空构造方法必须存在 否则创建失败
            比如 HelloService 和 ProductService 这两个bean， 这两个类中没有显式的定义构造方法，
            根据java语法，当创建类的时候，没有显示声明类的构造方法，程序会默认自动创建一个无参数的构造器
            （但注意，一旦在类中有定义好的构造器，则默认构造器就不再提供，所以，如果，一个类自定义了构造器，
            但还需要使用默认构造器（无参构造器），则必须再显式的声明一个无参的构造器）

     */

    public String juiceMake(){
        String juice = "顾客点了一杯桔汁：" + source.getFruit() + source.getSugar() + source.getSize();
        return juice;
    }
}
