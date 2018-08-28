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
/*
98. Validate Binary Search Tree
DescriptionHintsSubmissionsDiscussSolution
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:

Input:
    2
   / \
  1   3
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
             is 5 but its right child's value is 4.*/

 class Solution {
    TreeNode pre;
    public boolean isValidBST(TreeNode root) {
        //in order search is by order
        if (root == null)
            return true;
        pre = null;
        return isValidBSTree(root);
    
    }
    public boolean isValidBSTree(TreeNode root) {
        //in order search is by order
        if (root == null)
            return true;
        boolean left = isValidBSTree(root.left);
        if (pre == null ||(pre != null && pre.val < root.val)){
            pre = root;
        }else 
            return false;
        boolean right = isValidBSTree(root.right);
        
        return left & right;
    
    }    
}            