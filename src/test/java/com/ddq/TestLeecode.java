package com.ddq;

import org.junit.Test;

import java.util.*;

public class TestLeecode {

    @Test
    public void test() throws Exception{


//        String value = "512";
//        int x= Integer.parseInt(value); // int -> String
//        System.out.println("转换为整形的值为 " + x);


        // 最大子序列和
        {
            int[] array = new int[]{-1,2,8,0,3,6,-2,9,-1,7};
            System.out.println("maxSum of array = " + maxSumOfsubArrray(array));
            int[] resultArray2 = maxSumOfsubArrray2(array);
            System.out.println("maxSum of array = [" + resultArray2[0] +"," + resultArray2[1]+ ","+resultArray2[2]+"]");
            int[] resultArray3 = maxSumOfsubArrray3(array);
            System.out.println("maxSum of array = [" + resultArray3[0] +"," + resultArray3[1]+ ","+resultArray3[2]+"]");

        }

        //无重复字符最长子串
        {
            String s = "aabcdfbcag";// 5
//            String s = "grthdtghndgsfsareegthnyn";
//        String s = "abcabcbb"; // 3
//        String s = "bbbb"; // 1
//        String s = ""; //0
            int l = lengthOfLongestSubstring(s);
            System.out.println("l= " + l);


        }

        // 整数反转
        {
            int x= 279;
            System.out.println("x= " + reverse(x) );
        }



    }

    // 删除排序数组中的重复项（双指针）


    // 合并两个有序链表
    // 借助指针进行遍历



    // 有效括号
    // 借助栈（后进先出），由于后入栈的左括号要先进行匹配、
    // 匹配上就出栈，一但遍历到某个右括号时，出现无法匹配的情况，则表明，顺序不匹配，则该字符串就是无效的
    // 思维误区：可以考虑到栈的应用（后进先出），但是应避免思维定式，并不是所有元素都必须要入栈，
    // 即并不需要将左括号和有括号均入栈，而是只要利用栈来记录某一种类型括号，即进记录左括号或者右括号即可
    public  boolean isValid(String str){

        return false;
    }

    /*
        59. 最大子数组和
        给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
        子数组 是数组中的一个连续部分。

     */
    // 动态规划
    public int maxSumOfsubArrray(int[] array){
        int sum =0;
        int maxSum = 0;
        for(int x: array){
            sum = Math.max(sum+x,x);
            maxSum = Math.max(maxSum,sum);
        }
        return maxSum;
    }
    // 动态规划，求出最大子数组和对应的子数组（给出序列的左右下标）
    public int[] maxSumOfsubArrray3(int[] array){
        int sum =0;
        int[] resultArray = new int[3];
        for(int i=0;i<array.length;i++){
            sum = Math.max(sum+array[i],array[i]);
            if (sum > resultArray[0]){
                resultArray[0] = sum;
                resultArray[2] = i;
            }
        }
        for (int j=resultArray[2];j>0;j--){
            sum = sum - array[j];
            if ( sum == 0){
                resultArray[1] = j;
                break;
            }
        }
        return resultArray;
    }

    /*遍历求解，求出最大子序列和对应的子序列（给出序列的左右下标）
        依次求出以 array[i]为起始位置的最大子序列和，然后从求得的i个最大子序列和中取最大的那个
     */
    public int[] maxSumOfsubArrray2(int[] array){
        int currentMaxSum = 0;
        int sum =0;
        int rightIndex = 0;
        int[] resultArray = new int[3];
        int length = array.length;
        for (int i = 0;i<length;i++){
            sum = array[i];
            currentMaxSum = array[i];
            rightIndex = i;
            for (int j=i+1;j<length;j++){
                sum = sum + array[j];
                if ( sum > currentMaxSum ){
                    currentMaxSum = sum;
                    rightIndex = j;
                }
            }
            if (currentMaxSum > resultArray[0]){
                resultArray[0]=currentMaxSum;
                resultArray[1]= i;
                resultArray[2]=rightIndex;
            }
        }
        return resultArray;
    }


    /*
        14. 最长公共前缀

        编写一个函数来查找字符串数组中的最长公共前缀。
        如果不存在公共前缀，返回空字符串 ""。
        编写一个函数来查找字符串数组中的最长公共前缀。
        如果不存在公共前缀，返回空字符串 ""。

        该题目若要优化，优化方向，减少比较次数（二分法，分治法）
    */
    public String findLongestStr(String[] str){
        List<StringBuilder> list = new ArrayList<>();
        int minLen = str[0].length();
        int minStrIndex = 0;
        for (int i=0;i<str.length; i++){
            list.add(new StringBuilder(str[i]));
            if (str[i].length() < minLen){
                minLen = str[i].length();
                minStrIndex = i;
            }
        }
        String minStr = str[minStrIndex];
        StringBuilder subStr = new StringBuilder();
        list.remove(minStrIndex);
        char currentChar = minStr.charAt(0);
        boolean flag = false;
        for (int i=0;i<minLen;i++){
            currentChar = minStr.charAt(i);
            for (int j=0;j<minLen;j++){
                if (list.get(j).charAt(i) != currentChar){
                    flag = true;
                }
            }
            if (flag == true){
                break;
            }
            subStr.append(currentChar);
        }
        return subStr.toString();
    }

    // 13. 罗马数字转整数
    public int romanToIntNum(String str){
        return 0;
    }

    /*
        12. 整数转罗马数字
     */

    /*
        11. 盛最多水的容器
     */

    /*
        10.正则表达式匹配
        给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
        '.' 匹配任意单个字符
        '*' 匹配零个或多个前面的那一个元素
        所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
     */


    // 9. 回文数
    /*
        给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
        回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
        -2^31 <= x <= 2^31 - 1
     */
    public boolean isPalindromicNum(int x){
        // 由于给出的原数虽然是整数范围内的数，但是翻转后的结果可能会超出整型范围，所以如果用数学解法就要考虑翻转后的结果的溢出问题
        // 如果发生溢出，那就一定不是回文数，可以直接返回false
        // 最低位为0的数一定不是回文数(翻转后最高位不可为0)
        // 最简单方法是将数据转换为字符串，然后判断

        String xStr = String.valueOf(x);
        int left =0;
        int right = xStr.length()-1;
        while(left != right){
            if (xStr.charAt(left) != xStr.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /*
        8. 字符串转换整数

     */

    /*  7.整数反转
        给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
        如果反转后整数超过 32 位的有符号整数的范围 [-2^31,2^31-1] ，就返回 0。
        假设环境不允许存储 64 位整数（有符号或无符号）。

        int类型长度为4个byte ( 1byte = 8bit)
        int 类型范围为：-2^31 - 2^31
        int 类型最小最大值分别为静态变量 Integer.MIN_VALUE 和 Integer.MAX_VALUE
     */
    public int reverse(int x) {
        int rev = 0;
        int pop = 0;
        while(x!= 0){
            pop = x % 10;
            x = x/10;
            /* 此处 rev == Integer.MAX_VALUE/10 && pop >7 是由于整数相除结果是向下取整的
               所以，当rev == Integer.MAX_VALUE/10 时， rev*10 实际上是小于 Integer.MAX_VALUE 的
                本题最大的问题是需要考虑短整型的溢出问题，
                由于反转后的结果可能会溢出，并且题目又要求不可以用long类型存储结果，所以，就要考虑
                每一次翻转一位数字后就要进行判断，所以考虑跟 Integer.MAX_VALUE/10 进行比较
             */
            if(rev>Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE/10 && pop >7)){
                return 0;
            }
            if(rev<Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE/10 && pop <-8)){
                return 0;
            }
            rev = rev*10 +pop;
        }
        return rev;
    }

    // 整数反转官方题解
    public int reverse1(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /*
        6. Z字型变换
        将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
        比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
        P   A   H   N
        A P L S I I G
        Y   I   R
        之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
        请你实现这个将字符串进行指定行数变换的函数：
        string convert(string s, int numRows);
     */
    public String convert(String s,int numRows){
        // 利用可变字符串StringBuilder(线程不安全)
        // ArrayList底层为数组，可通过下标取值，读取快，插入删除慢
        List<StringBuilder> list = new ArrayList<>();
        for (int i=0;i<numRows;i++){
            list.add(new StringBuilder());
        }
        // 每读取 numRows 个数就需要转换方向
        int falg = 1;
        int listRow = 0;
        char currentChar = s.charAt(0);
        for (int i=0;i<s.length();i++){
            currentChar = s.charAt(i);
            list.get(listRow).append(currentChar);
            if (listRow == 2){
                falg = -1;
            }
            if (listRow == 0){
                falg = 1;
            }
            listRow = listRow +falg;
        }
        StringBuilder  resultStr = new StringBuilder();
        for (StringBuilder x : list){
            resultStr.append(x);
        }
        return resultStr.toString();
    }

    /*
        5. 最长回文子串
        给你一个字符串 s，找到 s 中最长的回文子串。
     */


    /*
        4. 寻找两个正序数组（从小到大排列）的中位数
       中位数：
              数组个数为偶数，中位数为中间两个数的平均数；
              数组个数为奇数，中位数为最中间的一个数
        给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
        算法的时间复杂度应该为 O(log (m+n)) 。
     */
    public int findMediumNum(int[] array1,int[] array2){

        return 0;
    }

    /*
        3. 无重复字符的最长子串
        给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
        依次求出以 s.charAt(i)为起始字符的最长无重复字符子串，最后再从求得的i个最长无重负字符子串中取最长的那个
     */
    public int lengthOfLongestSubstring(String s){
        Map<Character,Integer> charMap = new HashMap<>();
        int n =s.length();
        int maxLength = 0;
        int rightIndex = 0;
        char currentChar;
        for(int i=0;i<n;i++){
            if (i!=0){
                charMap.remove(s.charAt(i-1));
            }
            while (rightIndex < n){
                currentChar = s.charAt(rightIndex);
                if (charMap.containsKey(currentChar)){
                    break;
                }
                charMap.put(currentChar,rightIndex);
                rightIndex++;
            }
            maxLength = Math.max(maxLength,rightIndex-i);
        }
        return maxLength;
    }




}
