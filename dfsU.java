/*
698. Partition to K Equal Sum Subsets
DescriptionHintsSubmissionsDiscussSolution
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.*/
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum%k != 0)
            return false;
        int[] visited = new int[len];
        return dfs(nums, visited, k, 0,0, 0,sum/k);//bug1: sum =>sum/k
        
    }
    public boolean dfs(int[] nums, int[] visited, int k, int start, int curSum,int curNum, int sum) {
        if ( k == 1 )
            return true;
        if(curNum > 0 && curSum == sum)
            return dfs(nums, visited, k-1, 0,0,0, sum);
        
        for (int i = start; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            if(dfs(nums, visited, k, i+1,curSum+nums[i], curNum++, sum)) 
                return true;
            
            visited[i] = 0;
        }
        return false;
    }
}
/*
227. Basic Calculator II
DescriptionHintsSubmissionsDiscussSolution
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.*/
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';int num = 0;
        //先处理乘除，再处理加减，所以遇到+号放入正数，遇到-号放入负数，遇到*，／就处理后再放入栈中
        for (int i = 0; i < s.length(); i++) {
            //if (i < s.length())
            char c = s.charAt(i);
            
            if (i < s.length() && Character.isDigit(c)) {
                num = 10 * num + c - '0';
            }
            
            if((!Character.isDigit(c) && c != ' ' )|| i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '*') {
                    stack.push(num * stack.pop());
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = c;
            }
        }
        num = 0;
        while (!stack.isEmpty()) {
            num += stack.pop();
        }
        return num;
    }
}