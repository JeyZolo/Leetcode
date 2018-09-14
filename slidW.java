/*
438. Find All Anagrams in a String
DescriptionHintsSubmissionsDiscussSolution
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".*/
// method 1
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int lens = s.length(), lenp = p.length();
        
        for (int i = 0; i <= lens - lenp; i++) {
            String ss = s.substring(i,i+lenp);
            if(isAnagrams(ss,p)){
                res.add(i);
            }
        }
        return res;
    }
    public boolean isAnagrams(String s, String p) {
        int len = s.length();
        int[] alb = new int[256];
        
        for (int i = 0; i < len; i++) {
            alb[s.charAt(i)]++;
            alb[p.charAt(i)]--;
        }
        for (int num : alb) {
            if(num != 0)
                return false;
        }
        return true;
    }
}
//method 2 sliding windows

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int lens = s.length(), lenp = p.length();
        int[] alb = new int[26];
        for (int i = 0; i <lenp ; i++) {
            alb[p.charAt(i)-'a']++;
        }
        
        int start = 0, end = 0;int match = 0;
        //sliding windows
        
        while(end < lens) {
            //if cur point is exist in string p, it's match with p
            if (alb[s.charAt(end) - 'a'] >= 1)
                match++;
            //it's matched or not matched, mark it as visited
            alb[s.charAt(end) - 'a']--;
            end++;
            if (match == lenp) {
                res.add(start);
                //find next string
            }
            //when visit lenp char in s
            if (end - start == lenp) {
                // if start char in p, need to decrease match
                if (alb[s.charAt(start) - 'a'] >= 0) {
                    match --;
                }
                alb[s.charAt(start) - 'a']++;
                start++;
                
            }
            
        }
        
        
        return res;
    }
}
/*342. Power of Four
DescriptionHintsSubmissionsDiscussSolution
Pick One
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
Example 1:
Input: 16
Output: true


Example 2:
Input: 5
Output: false
Follow up: Could you solve it without loops/recursion?*/

class Solution {
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }
}
/*

New Playground
jing76
186. Reverse Words in a String II
DescriptionHintsSubmissionsDiscussSolution
Given an input string , reverse the string word by word. 

Example:

Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
Note: 

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?*/
class Solution {
    public void reverseWords(char[] str) {
        int len = str.length;
        
        //reverse the array then, reverse every word
        reverseWord(str, 0, len-1);
        //reverse every word
        int i = 0;
        while (i < len) {
            int j = i;
            while (j < len && str[j] != ' '){
                j++;
            }
            reverseWord(str, i, j - 1);
            i = j+1;
        }
        
    }
    public void reverseWord(char [] str, int low , int high) {
        //int low = 0, high = len - 1;
        while (low < high) {
            char t = str[low];
            str[low] = str[high];
            str[high] = t;
            low++;
            high--;
        }
    }
}
/*
557. Reverse Words in a String III
DescriptionHintsSubmissionsDiscussSolution
Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.*/class Solution {
    public String reverseWords(String s) {
        //to char array
        
        char[] str = s.toCharArray();
        int i = 0, j = 0;
        while (i < s.length()) {
             j = i;
            while (j < s.length() && str[j] != ' ') {
                j++;
            }
            reverseStr(str, i, j -1);
            i = j+1;
        }
        return String.valueOf(str);
    }
    public void reverseStr(char[] s, int i, int j) {
        
        while (i < j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
            i ++;
            j --;
        }
        
    }
}/*
224. Basic Calculator
DescriptionHintsSubmissionsDiscussSolution
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.*/
class Solution {
    public int calculate(String s) {
        // change all - before (  to opsite marks
        //use stack to store the res and sign befoe (), cuz if calcute the res in (), 
        //it all new res as a num, so we need the previous res and pre sign for it
        Stack<Integer> stack = new Stack<>();
        int sign = 1;int num = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            }else if (c == '+') {
                res += sign * num;
                sign = 1;
                num = 0;
            }else if (c == '-') {
                res += sign * num;
                sign = -1;
                num = 0;
            }else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
                num = 0;
            }else if (c == ')') {
            	//add the last num before )
                res += sign * num;
                //add the res before (
                res = stack.pop() * res + stack.pop();
                sign = 1;
                num = 0;
            }
        }
        if (num != 0)
             res += sign * num;
        return res;
    }
}
/*
339. Nested List Weight Sum
DescriptionHintsSubmissionsDiscussSolution
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 10 
Explanation: Four 1's at depth 2, one 2 at depth 1.
Example 2:

Input: [1,[4,[6]]]
Output: 27 
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.*/
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
//recursion
class Solution {
    int sum = 0;
    public int depthSum(List<NestedInteger> nestedList) {
        sum = 0;
        getSumList(nestedList, 1);
        return sum;
    }
    public void getSumList(List<NestedInteger> nestedList, int dept) {
        
        //get the element of list
        for (NestedInteger n:  nestedList) {
            if (n.isInteger()) {
                sum = sum + n.getInteger() * dept;
            }else {
                getSumList(n.getList(), dept+1);
            }
            
        }
    }
}
//Itertive- BFS
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        //use BFS
        if(nestedList == null) {
            return 0;
        }
        int sum = 0, level = 1;
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger n = queue.poll();
                if (n.isInteger()) {
                    sum += level * n.getInteger();
                }else {
                    queue.addAll(n.getList());
                }
            }
            level++;
        }
        return sum;
        
    }
}
/*
364. Nested List Weight Sum II
DescriptionHintsSubmissionsDiscussSolution
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8 
Explanation: Four 1's at depth 1, one 2 at depth 2.
Example 2:

Input: [1,[4,[6]]]
Output: 17 
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.*/
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    int sum ;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        sum = 0;
        int level = depth(nestedList);
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);
        //System.out.println(level);
        //use bfs to get the sum;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger n = queue.poll();
                if(n.isInteger()) {
                    //System.out.println(n.getInteger());
                    //System.out.println(level);
                    sum += level * n.getInteger();
                    //System.out.println(sum);
                }else {
                    queue.addAll(n.getList());
                }
                
            }
            level--;//bug 1;level is out of for loop
        }
        return sum;
    }
    //use dfs to get depth 
    public int depth(List<NestedInteger> nestedList) {
        int level = 0;int max = 0;
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) {
                continue;
            }else {
                level = depth(n.getList()) + 1;
                max = Math.max(level,max);//need to check the max level, every element level is different
            }
        }
        if (level == 0) {
            level = 1;
            max = 1;//bug2: need to give 1 to max else it will be 0,
        }
        return max;
        
    }
}

//method 2:
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int unweighted = 0, weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()){
                	//add last level and this level Integer
                    unweighted += ni.getInteger();
                    System.out.println(unweighted);
                }
                else
                    nextLevel.addAll(ni.getList());
            }
            //add last level result and this level result
            weighted += unweighted;//mutiple add weighted level by level 2+[1,1]+[1,1], 
            System.out.println("w="+weighted);
            nestedList = nextLevel;
        }
        return weighted;
    }
}
/*
103. Binary Tree Zigzag Level Order Traversal
DescriptionHintsSubmissionsDiscussSolution
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]*/
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        //bfs the tree
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> sublist = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(level % 2 == 0)
                    sublist.add(node.val);
                else sublist.add(0,node.val);
                
                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);  
            }
            level++;
            res.add(new ArrayList<>(sublist));
            //direction = direction == true? false: true;
        }
        return res;
    }
}
