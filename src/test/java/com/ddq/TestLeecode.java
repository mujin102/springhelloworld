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
        53. 最大子数组和
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

    /*遍历求解，求出最大子序列的和以及对应的子序列（给出序列的左右下标）
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
        20.有效的括号
        给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
        有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
        示例 1：
            输入：s = "()"
            输出：true
        示例 2：
            输入：s = "()[]{}"
            输出：true
        示例 3：
            输入：s = "(]"
            输出：false
        示例 4：
            输入：s = "([)]"
            输出：false
        示例 5：
            输入：s = "{[]}"
            输出：true
     */

    /*
        15. 三数之和
        给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
        请你找出所有和为 0 且不重复的三元组。
        注意：答案中不可以包含重复的三元组。
        示例 1：
        输入：nums = [-1,0,1,2,-1,-4]
        输出：[[-1,-1,2],[-1,0,1]]
     */
    public List<List<Integer>> threeSum( int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int left = 1;
        int right = nums.length-1;
        int curSum = 0;
        int currentNum = nums[0];
        if (currentNum>0 || nums==null){
            return null;
        }
        for (int i=0;i<nums.length;i++){
            currentNum = nums[0];
            if (i>0 && nums[i]==nums[i-1]){
                break;  // 去除第一个数的重复数字，需要左指针继续往前移动，因为不允许包含重复三元组
            }
            while(left != right){
                if (left>1 && nums[left]== nums[left-1]){
                    left++;  // 去除第二个数的重复数字，需要左指针继续往前移动，因为不允许包含重复三元组
                }
                curSum = 0-currentNum;
                if (nums[left] + nums[right] < curSum){
                    left++;
                }
                if (nums[left] +nums[right] > curSum){
                    right--;
                }
                if (nums[left] + nums[right] == curSum){
                    List<Integer> curList = new ArrayList<>();
                    curList.add(currentNum);
                    curList.add(nums[left]);
                    curList.add(nums[right]);
                    result.add(curList);
                    left++;
                }
            }
        }
        return result;
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
        给你 n 个非负整数 a1，a2，…，an，每个数代表坐标中的一个点 (i, ai) 。
        在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
        找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
        说明：你不能倾斜容器，且 n 的值至少为 2。
        示例：
            输入：[1,8,6,2,5,4,8,3,7]
            输出：49

     */
    public int maxArea(int[] height){
        // 暴力求解
        int maxArea = 0;
        for (int i=0;i<height.length;i++){
            for (int j=i+1;j<height.length;j++){
                maxArea =Math.max(maxArea,(j-i)*Math.min(height[j],height[i]));
            }
        }
        return maxArea;
    }
    // 优化算法，双指针
//    public int maxArea1(int[] height){
//
//    }

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
                本题最大的问题是需要考虑整型的溢出问题，
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
    public String longestPalindrome(String s){
        int maxLen = 0;
        int len1 = 0;
        int len2 = 0;
        int currentLen = 0;
        int left =0;
        int right = 0;
        for (int i=0;i<s.length();i++){
            len1 = findPalindrome(s,i,i);
            len2 = findPalindrome(s,i,i+1);
            currentLen = Math.max(len1,len2);
            if (currentLen> maxLen){
                maxLen = currentLen;
                left = i-(currentLen-1)/2;
                right = i + currentLen/2;
            }
        }
        return s.substring(left,right);
    }
    public int  findPalindrome(String s,int left,int right){
        String res = "";
        while(left>0 && right<s.length()){
            if (s.charAt(left) != s.charAt(right)){
                res = s.substring(left+1,right-1);
                break;
            }
            left--;
            right++;
        }
        return res.length();
    }


    /*
        4. 寻找两个正序数组（从小到大排列）的中位数
       中位数：
              数组个数为偶数，中位数为中间两个数的平均数；
              数组个数为奇数，中位数为最中间的一个数
        给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
        算法的时间复杂度应该为 O(log (m+n)) 。
        提示：
            nums1.length == m
            nums2.length == n
            0 <= m <= 1000
            0 <= n <= 1000
            1 <= m + n <= 2000
            -106 <= nums1[i], nums2[i] <= 106
     */
    public int findMediumNum(int[] array1,int[] array2){
        int index1 = 0;
        int index2 = 0;
        int mediumNum = 0;
        int m = array1.length;
        int n = array2.length;
        int mediumNUmIndex = (m+n)/2;
        int[] result = new int[ m+n];
        int resultIndex = 0;
        if (m==0){
            result = array2;
        }
        if (n==0){
            result = array1;
        }
        while (resultIndex <= mediumNUmIndex){
            if (array1[index1] <= array2[index2]){
                result[resultIndex] = array1[index1];
                index1++;
                resultIndex++;
            }
            if (array1[index1] > array2[index2]){
                result[resultIndex] = array2[index2];
                index2++;
                resultIndex++;
            }
        }
        if ((m+n)%2 == 0){
            mediumNum = (result[mediumNUmIndex-1] + result[mediumNUmIndex])/2;
        }else {
            mediumNum = result[mediumNUmIndex];
        }
        return mediumNum;
    }

    /*
        3. 无重复字符的最长子串
        给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
        依次求出以 s.charAt(i)为起始字符的最长无重复字符子串，最后再从求得的i个最长无重负字符子串中取最长的那个
     */
    // 滑动窗口（本质还是暴力求解） + hash
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
                    break;  // break的优化，可以直接放在循环的判断条件上，
                            // 使得不满足条件的时候不进入循环即可，优化后代码如后续版本

                }
                charMap.put(currentChar,rightIndex);
                rightIndex++;
            }
            maxLength = Math.max(maxLength,rightIndex-i);
        }
        return maxLength;
    }

    // 滑动窗口（本质还是暴力求解） + hash 优化 break 版
    public int lengthOfLongestSubstring1(String s){
        return 0;
    }

    // 双指针 + hash
    public int lengthOfLongestSubstring2(String s){
        Map<Character,Integer> map = new HashMap<>();
        char currentChar = s.charAt(0);
        int left = 0;
        int right = 0;
        int currentLen = 0;
        int maxLen =0;
        while(left<s.length() && right <s.length()){
            currentChar = s.charAt(right);
            if ( !map.containsKey(currentChar)){
                map.put(currentChar,right);
                right++;
            }
            if (map.containsKey(currentChar)){
                currentLen = right -left;
                if (currentLen >maxLen){
                    maxLen = currentLen;
                    while (left<=map.get(currentChar)){
                        map.remove(s.charAt(left));
                        left++;
                    }
                }
            }
        }
        return maxLen;
    }

    /*
      2. 两数相加
        给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
        请你将两个数相加，并以相同形式返回一个表示和的链表。
        你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
        示例 1：
            输入：l1 = [2,4,3], l2 = [5,6,4]
            输出：[7,0,8]
            解释：342 + 465 = 807.
     */

    //  Definition for singly-linked list.
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
          ListNode num1 = l1;
          ListNode num2 = l2;
          ListNode sumList = new ListNode();
          ListNode currentNode = sumList;
          int sum = 0;
          while (num1 !=null || num2 !=null){
              if (num1 != null && num2 != null){
                  sum = num1.val + num2.val + currentNode.val;
              }
              if (num1 != null && num2 == null){
                  sum = num1.val + currentNode.val;
              }
              if (num1 == null && num2 != null){
                  sum = num2.val + currentNode.val;
              }
              currentNode.val = sum%10;
              currentNode.next = new ListNode(sum/10);
              num1 = num1.next;
              num2 = num2.next;
          }
          if (currentNode.next.val == 0){
              currentNode.next = null;
          }
          return sumList;
    }


    /*
      1. 两数之和
        给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，
        并返回它们的数组下标。
        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
        你可以按任意顺序返回答案。
        示例 1：
        输入：nums = [2,7,11,15], target = 9
        输出：[0,1]
        解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     */
    public int[] twoSum(int[]nums,int target){
        Map<Integer,Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(target - nums[i])){
                result[0] = map.get(target -nums[i]);
                result[1] = i;
            }
            map.put(nums[i],i);
        }
        return  result;
    }


}
