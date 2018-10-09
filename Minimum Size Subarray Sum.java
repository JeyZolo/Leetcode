/*209. Minimum Size Subarray Sum
DescriptionHintsSubmissionsDiscussSolution
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). */
class Solution {
	//use sliding window
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        
        int len = nums.length;int j = 0 ,mark = 0, sum = 0;
        
        for (int i = 0; i < nums.length; i++) { 
            
            while(j < nums.length && sum < s) {
                sum += nums[j];
                j++;
            }
            
            if(sum >= s){
                len = Math.min(len, j - i );
                mark = 1;
            }
            sum -= nums[i];
           
        }
        return mark == 1 ? len : 0;
    }
}
/*
236. Lowest Common Ancestor of a Binary Tree
DescriptionHintsSubmissionsDiscussSolution
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself
             according to the LCA definition.
Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
            
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        //三种情况： 分布在左右两边，在左边，在右边：左右两边时，往左边找和右边找的结果都是非null，然后得到的是root
        //都在右边：则左边是null，直接返回右边的值即可，左边同
        return left == null ? right : right == null ? left : root;
    }
}