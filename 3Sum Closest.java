/*16. 3Sum Closest
DescriptionHintsSubmissionsDiscussSolution
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).*/
class Solution {
    public int threeSumClosest(int[] nums, int target) {
       if (nums == null || nums.length < 3) {
            return -1;
        }
                
        int len = nums.length;
        Arrays.sort(nums);
        
        int minSum = nums[0] + nums[1] + nums[len-1];int sum = 0;
        
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            int left = i + 1, right = len - 1;
            
            while (left < right) {
                sum = nums[left] + nums[right] + num;
                if (sum < target) left++;
                else right --;
                
                if (Math.abs(sum - target) < Math.abs(minSum - target)) {
                    minSum = sum;
                    //System.out.println(res);
                }
                
            }            
        }
        return minSum;
    }
}