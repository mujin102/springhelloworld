package com.ddq;

import org.junit.Test;

import java.util.Locale;

public class StringTest {

    @Test // 字符串操作 org.apache.commons.lang3
    public void langStringBuilderTest() throws Exception{
        /*
            StringBuilder，它是一个可变对象，可以预分配缓冲区，这样，往StringBuilder中新增字符时，不会创建新的临时对象
         */
        // 新建StringBuilder对象
        StringBuilder s0 = new StringBuilder();
        System.out.println("新建一个空StringBuilder = " + s0.toString());
        System.out.println("空字符串\"\" = " + "");

        // 追加字符
        StringBuilder sb = new StringBuilder(1024);
        for (int i = 0; i < 1000; i++) {
            sb.append(',');   // 追加字符
            sb.append(i);
        }
        String s = sb.toString();

        // 删除子串
        sb = new StringBuilder("从前有座庙，庙里有个老和尚和小和尚");//建立一个字符缓存区
        sb.deleteCharAt(8); //删除下标位置为8的字符
        sb.delete(1, 3); //删除下标位置在1到3的字符，包括1但不包括3
        System.out.println("操作后的字符串 sb = " + sb);

    }

    @Test  // String
    public void langStringTest() throws Exception{

        // 新建字符串对象
        String s1 = "Hello!";
        String s2 = new String(new char[]{'H', 'e', 'l', 'l', 'o', '!'});


        // 字符串比较 必须使用equals()方法而不能用==
        String s3 = "HELLO".toLowerCase();
        System.out.println("s1 == s3 判断结果为： " + (s1==s3)); // s1 == s3 判断结果为： false
        System.out.println("s1.equals(s3) 判断结果为： " + s1.equals(s3)); // s1.equals(s3) 判断结果为： false

        // 搜索子串
        "hello".contains("ll"); // true  是否包含子串"ll"
        "hello".indexOf("l"); // 2
        "hello".lastIndexOf("l"); // 3
        "hello".startsWith("he"); // true
        "hello".endsWith("lo"); // true

        // 提取子串  注意索引号是从0开始的。
        "hello".substring(2); // llo
        "hello".substring(1,3); // ell

        // 去除首尾空白字符
        /*
        使用trim()方法可以移除字符串首尾空白字符。空白字符包括空格，\t，\r，\n
        注意：trim()并没有改变字符串的内容，而是返回了一个新字符串。
         */
        "  \tHello\r\n ".trim(); //  "Hello"
        /*
        strip()方法也可以移除字符串首尾空白字符。它和trim()不同的是，类似中文的空格字符\u3000也会被移除
        "\u3000Hello\u3000".strip(); // "Hello"
        " Hello ".stripLeading(); // "Hello "
        " Hello ".stripTrailing(); // " Hello"

        说明： 由于 .strip() 方法是jdk11中才引入的一个方法，所以，此处不可识别，修改jdk版本即可使用
         */

        /*
            isEmpty()和isBlank()来判断字符串是否为空和空白字符串
            "  \n".isBlank(); // true，因为只包含空白字符
            " Hello ".isBlank(); // false，因为包含非空白字符
            由于 isBlank() 方法是jdk11中才引入的一个方法
         */
        "".isEmpty(); // true，因为字符串长度为0
        "  ".isEmpty(); // false，因为字符串长度不为0


        /*
            JDK 11添加到String的六个方法
            String.repeat(int)
            String.lines()
            String.strip()
            String.stripLeading()
            String.stripTrailing()
            String.isBlank()
         */

        // 替换子串
        /*
            两种方法:
            一种是根据字符或字符串替换：
            一种是通过正则表达式替换：
         */
        String s = "hello";
        s.replace('l', 'w'); // "hewwo"，所有字符'l'被替换为'w'
        s.replace("ll", "~~"); // "he~~o"，所有子串"ll"被替换为"~~"
        s = "A,,B;C ,D";
        s.replaceAll("[\\,\\;\\s]+", ","); // "A,B,C,D"  通过正则表达式，把匹配的子串统一替换为","

        // 分割字符串
        s = "A,B,C,D";
        String[] ss = s.split("\\,"); // {"A", "B", "C", "D"}

        // 拼接字符串
        String[] arr = {"A", "B", "C"};
        s = String.join("***", arr); // "A***B***C"

        // 格式化字符串
        /*
            字符串提供了formatted()方法和format()静态方法，可以传入其他参数，替换占位符，然后生成新的字符串
            s = "Hi %s, your score is %d!";
            System.out.println(s.formatted("Alice", 80));
            System.out.println(String.format("Hi %s, your score is %.2f!", "Bob", 59.5));
            // 运行结果：
            Hi Alice, your score is 80!
            Hi Bob, your score is 59.50!
         */

        //类型转换
        /*
            把任意基本类型或引用类型转换为字符串，可以使用静态方法valueOf()。这是一个重载方法，编译器会根据参数自动选择合适的方法
         */
        String.valueOf(123); // "123"
        String.valueOf(45.67); // "45.67"
        String.valueOf(true); // "true"
        String.valueOf(new Object()); // 类似java.lang.Object@636be97c

        /*
        把字符串转换为其他类型，就需要根据情况。例如，把字符串转换为int类型
         */
        int n1 = Integer.parseInt("123"); // 123
        int n2 = Integer.parseInt("ff", 16); // 按十六进制转换，255

        /*
        把字符串转换为boolean类型
         */
        boolean b1 = Boolean.parseBoolean("true"); // true
        boolean b2 = Boolean.parseBoolean("FALSE"); // false

        /*
        要特别注意，Integer有个getInteger(String)方法，它不是将字符串转换为int，而是把该字符串对应的系统变量转换为Integer
         */
        Integer.getInteger("java.version"); // 版本号，8


        // 转换为char[]
        /*
            String和char[]类型可以互相转换
         */
        char[] cs = "Hello".toCharArray(); // String -> char[]
        s = new String(cs); // char[] -> String


    }

}
