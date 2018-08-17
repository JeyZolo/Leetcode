/*
368. Largest Divisible Subset
DescriptionHintsSubmissionsDiscussSolution
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]*/
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        int len = nums.length;
        int[] count = new int[len];//count the Divisible Subset
        int[] pre = new int[len]; // store every nums[i]'s previous element'
        Arrays.sort(nums);
        int max = 0, index = 0;
        
        for (int i = 0; i < len; i++){
            count[i] = 1;
            pre[i] = -1;
            
            for(int j = i-1; j>=0; j--){
                if(nums[i]%nums[j] == 0){
                    if(count[j] + 1 > count[i]){
                        count[i] = count[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if(count[i] > max){
                max = count[i];
                index = i;
            }
        }
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }
}
/*
279. Perfect Squares
DescriptionHintsSubmissionsDiscussSolution
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.*/
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 0; i * i <= n; ++i) {
            dp[i * i] = 1;
        }

        for (int i = 0; i <= n; ++i) {
            for (int j = 1; j * j <= i; ++j) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}
/*
55. Jump Game
DescriptionHintsSubmissionsDiscussSolution
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.*/
class Solution {
    public boolean canJump(int[] nums) {
        //int[] sum = new int[nums.length];
        int max = 0;
        //max is from cur index how farthest index, it can jump
        for (int i = 0; i < nums.length; i++){
            if (i > max) return false;
            //from i, it can jump to index
            int index = nums[i] + i;
            max = Math.max(index, max);
            
        }
        return true;
    }
}
/*
300. Longest Increasing Subsequence
DescriptionHintsSubmissionsDiscussSolution
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        if(nums == null || nums.length == 0) return 0;// need to check null and len==0
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int MAX = 1;// the least solution is 1;
        //for every element ,find pervious element j < i and also find its max count of subsequence
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
             max = 0;
            for (int j = i - 1; j >=0; j--){
                if(nums[j] < nums[i]){
                    max = Math.max(dp[j], max);
                }
            }
            dp[i] = max + 1;
            MAX = Math.max(dp[i], MAX);
        }
        return MAX;
    }
}