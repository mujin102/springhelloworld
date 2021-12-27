package com.ddq;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UtilsTest {
    ObjectMapper objectMapper = new ObjectMapper();



    @Test // Math类的常用方法
    public void mathTest()throws Exception{
        // 取最大最小值
        int maxNum = Math.max(1,2);
        int minNum = Math.min(1,2);
        System.out.println("两数中较大值为： " + maxNum);
        System.out.println("两数中较小值为： " + minNum);

        // 向上取整，向下取整,四舍五入
        double x = 3.4;
        double ceilRes = Math.ceil(x); // 向上取整
        double floorRes = Math.floor(x); // 向下取整
        double roundRes = Math.round(x); // 四舍五入
        System.out.println("向上取整结果为： " + ceilRes);
        System.out.println("向下取整结果为： " + floorRes);
        System.out.println("四舍五入结果为： " + roundRes);

        double i = 3.856;

        // 舍掉小数取整
        System.out.println("舍掉小数取整:Math.floor(3.856)=" + (int) Math.floor(i));

        // 四舍五入取整
        System.out.println("四舍五入取整:(3.856)=" + new BigDecimal(i).setScale(0, BigDecimal.ROUND_HALF_UP));

        // 四舍五入保留两位小数
        System.out.println("四舍五入取整:(3.856)=" + new BigDecimal(i).setScale(2, BigDecimal.ROUND_HALF_UP));

        // 凑整，取上限
        System.out.println("凑整:Math.ceil(3.856)=" + (int) Math.ceil(i));

        // 舍掉小数取整
        System.out.println("舍掉小数取整:Math.floor(-3.856)=" + (int) Math.floor(-i));
        // 四舍五入取整
        System.out.println("四舍五入取整:(-3.856)=" + new BigDecimal(-i).setScale(0, BigDecimal.ROUND_HALF_UP));

        // 四舍五入保留两位小数
        System.out.println("四舍五入取整:(-3.856)=" + new BigDecimal(-i).setScale(2, BigDecimal.ROUND_HALF_UP));

        // 凑整，取上限
        System.out.println("凑整(-3.856)=" + (int) Math.ceil(-i));
    }

    @Test // 生成随机数
    public void randomTest() throws Exception{
        /*
            工具类：
            Math.random(); // 范围是0 <= x < 1
            Math.random()实际上内部调用了Random类，所以它也是伪随机数，只是我们无法指定种子。

            Random用来创建伪随机数。
            所谓伪随机数，是指只要给定一个初始的种子，产生的随机数序列是完全一样的。
            如果不给定种子，就使用系统当前时间戳作为种子
            Random r = new Random();  Random r = new Random(12345);
            r.nextInt(); // 2071575453,每次都不一样
            r.nextInt(10); // 5,生成一个[0,10)之间的int
            r.nextLong(); // 8811649292570369305,每次都不一样
            r.nextFloat(); // 0.54335...生成一个[0,1)之间的float
            r.nextDouble(); // 0.3716...生成一个[0,1)之间的double
         */
        double x = Math.random(); // x的范围是[0,1),左闭右开区间
        double min = 10;
        double max = 50;
        double y = x*(max-min)+min; // y的范围是[10,50)
        long n = (long)y;  // n的范围是[10,50)的整数
        System.out.println("x= " + x + "生成随机数y= " + y);
        System.out.println("x= " + x + "生成随机数n= " + n);
    }

    @Test  // 时间操作
    public void localDatetimeTest(){

        System.out.println(LocalDateTime.now());
        // String --> LocalDate
        LocalDate localDate = LocalDate.parse("2019-12-07");
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        System.out.println("localdate = " + localDate.format(pattern));

        // String --> LocalTime
        LocalTime localTime = LocalTime.parse("00:00:00");

        // String -->LocalDateTime
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"); // 12小时
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 24小时
        String localDateTime = LocalDateTime.of(localDate,localTime).format(formatter);
        System.out.println("localDateTime = " + localDateTime);

        // 日期运算
        LocalDate localDate1 = LocalDate.now();
        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String endDateTime = LocalDateTime.of(localDate1,LocalTime.parse("23:59:59")).plusDays(2).format(dateTimeFormatter);
        String startDataTime = LocalDateTime.of(localDate1.minusDays(10),LocalTime.parse("00:00:00")).format(dateTimeFormatter);
        System.out.println("localDate1 = " + localDate1);
        System.out.println("endDateTime = " + endDateTime);
        System.out.println("startDataTime = " + startDataTime);
    }
}
