/*
94. Binary Tree Inorder Traversal
DescriptionHintsSubmissionsDiscussSolution
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]*/
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
    Stack<TreeNode> stack;
    public List<Integer> inorderTraversal(TreeNode root) {
        stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        
        while (root != null || !stack.isEmpty()) {
            //push all the left into stack
            while (root != null ){
                stack.push(root);
                root = root.left;
            }
            //pop the stack and put it into result
            TreeNode left = stack.pop();
            res.add(left.val);
            // go to the right and inorder to search the right tree
            root = left.right;
            
        }
        return res;
    }
}
/*
144. Binary Tree Preorder Traversal
DescriptionHintsSubmissionsDiscussSolution
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]*/
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode cur = root;
        
        while (cur != null || !stack.isEmpty()) {
            //add root node to result list , and push its right into stack, and go to left,it becomes root,left,then no left,go to right
            while (cur != null) {
                res.add(cur.val);
                if (cur.right != null)
                    stack.push(cur.right);
                cur = cur.left;
            }
            
            // pop the right node and then preorder the right tree
            if (!stack.isEmpty())// bug1:forget check whether stack is empty
                cur = stack.pop();

        }
        return res;
        
    }
}
/*255. Verify Preorder Sequence in Binary Search Tree
DescriptionHintsSubmissionsDiscussSolution
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Consider the following binary search tree: 

     5
    / \
   2   6
  / \
 1   3
Example 1:

Input: [5,2,6,1,3]
Output: false
Example 2:

Input: [5,2,1,3,6]
Output: true*/
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stack = new Stack<>();
        int low = Integer.MIN_VALUE;
        
        for (int p : preorder) {
            // if new is less than older it is false
            if (p < low)
                return false;
            // find the largest lowest num which is less than p
            while (!stack.isEmpty() && stack.peek() < p){
                low = stack.pop();
            }
            stack.push(p);
            
        }
        return true;
    }
}