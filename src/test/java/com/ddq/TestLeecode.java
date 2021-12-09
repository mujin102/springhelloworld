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

    // 最长公共前缀
    // 该题目若要优化，优化方向，减少比较次数（二分法，分治法）
    public String findLongestStr(String[] str){

        return "";
    }

    // 罗马数字转整数
    public int romanToIntNum(String str){
        return 0;
    }



    // 回文数
    public boolean isPalindromicNum(int x){

        return false;
    }

    // 寻找两个正序数组（从小到大排列）的中位数
    // 中位数：
    //        数组个数为偶数，中位数为中间两个数的平均数；
    //        数组个数为奇数，中位数为最中间的一个数
    public int findMediumNum(int[] array1,int[] array2){

        return 0;
    }


    /*
        最大子序列和
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
    // 动态规划，求出最大子序列和对应的子序列（给出序列的左右下标）
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
        无重复字符的最长子串
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

    /*  整数反转
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
            // 此处 rev == Integer.MAX_VALUE/10 && pop >7 是由于整数相除结果是取整的
            // 所以，当rev == Integer.MAX_VALUE/10 时， rev*10 实际上是小于 Integer.MAX_VALUE 的
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


}
