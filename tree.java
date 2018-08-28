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
/*226. Invert Binary Tree
DescriptionHintsSubmissionsDiscussSolution
Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1*/
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
        
    }
}
/*
617. Merge Two Binary Trees
DescriptionHintsSubmissionsDiscussSolution
Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:
Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7*/
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
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null)
            return t1 == null ? t2 : t1;
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left,t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        
        return t1;
    }
}
/*
173. Binary Search Tree Iterator
DescriptionHintsSubmissionsDiscussSolution
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.*/
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//in order search item, next item is next smallest;

public class BSTIterator {
    //inorder search next is next smallest
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if (!hasNext())
            return -1;
        TreeNode node = stack.pop();
        pushAll(node.right);            
        return node.val;
    }
    public void pushAll( TreeNode root) {
        while (root != null){
            stack.push(root);
            root = root.left;
        }
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
/*
96. Unique Binary Search Trees
DescriptionHintsSubmissionsDiscussSolution
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3*/
   class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++){
                dp[i] += dp[j - 1] * dp[i - j];// G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0) 

            }
        }
        return dp[n];
    }
}
/*
100. Same Tree
DescriptionHintsSubmissionsDiscussSolution
Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false*/
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null)
            return (p == null) && (q == null);
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
/*
654. Maximum Binary Tree
DescriptionHintsSubmissionsDiscussSolution
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1*/
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return constructMaxBT(nums, 0 , nums.length-1);
    }
    public TreeNode constructMaxBT(int[] nums, int begin, int end) {
        if (begin > end)
            return null;
        int max = nums[begin];int div = begin;
        for (int i = begin; i <= end; i++) {
            if (max < nums[i]){
                max = nums[i];
                div = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = constructMaxBT(nums, begin,div-1);
        root.right = constructMaxBT(nums, div+1, end);
        return root;
    }

}
/*
105. Construct Binary Tree from Preorder and Inorder Traversal
DescriptionHintsSubmissionsDiscussSolution
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7*/
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0)
            return null;
        
        // 1. find the head from preorder root = preorder[0], then divided into two parts from inorder
        // 2. do the same thing as step 1 for the two parts
        return buildToTree(preorder, inorder, 0, 0, inorder.length-1);
        
    }
    public TreeNode buildToTree(int[] preorder, int[] inorder, int div, int begin, int end) {
        //
        if (begin > end)
            return null;
        if (begin == end){
            return new TreeNode(inorder[begin]);
        }
        //divide two parts for inorder using div
        int subs = 0;int mark = 0;
        for (int j = div; j < preorder.length; j++){
            int num = preorder[j];
            for (int i = begin; i <= end; i++) {
                if (inorder[i] == num)
                 {   subs = i;
                     mark = 1;
                     break;
                 }
            }
            if (mark == 1) break;
        }
        TreeNode root = new TreeNode(inorder[subs]);
        root.left = buildToTree(preorder, inorder, div+1, begin, subs - 1);
        root.right = buildToTree(preorder, inorder, div+1, subs + 1, end);
        return root;
        
    }
}