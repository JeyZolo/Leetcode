/*652. Find Duplicate Subtrees
DescriptionHintsSubmissionsDiscussSolution
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// use post order of tree search to ensure whether they are the same subtree
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        postOrderTree(root, new HashMap<>(), res);
        return res;
    }
    public String postOrderTree(TreeNode node, HashMap<String,Integer> map, List<TreeNode> res) {
        if (node == null)
            return "#";
        String treeTostr = node.val + postOrderTree(node.left, map, res) + postOrderTree(node.right, map, res);
        if (map.getOrDefault(treeTostr,0) == 1){
            res.add(node);
        }
        map.put(treeTostr,map.getOrDefault(treeTostr,0) + 1);
        return treeTostr;
    }
}

/*
36. Valid Sudoku
DescriptionHintsSubmissionsDiscussSolution
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

A partially filled sudoku which is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true
Example 2:

Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being 
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.*/

    class Solution {
    public boolean isValidSudoku(char[][] board) {
        
        for (int i = 0; i < 9; i++){
            if (checkRow(board,i) == false)
                return false;
            if (checkCol(board, i) == false) {
                return false;
                
            }
        }
        for (int i = 0; i < 3; i ++){
                int x = i * 3;
                for (int j = 0; j < 3; j++){
                    
                    int y = j * 3;
                    if(checkBox(board, x, y) == false)
                        return false;
                }
        }
            return true;
        
    }
    public boolean checkRow(char[][] board, int row){
        int[] nums = new int[10];
        
        for (int i = 0; i < 9; i++){
            if (board[row][i] == '.') continue;
            int index = board[row][i] - '0';
            if(nums[index] == 1)
                return false;
            nums[index] = 1;
        }
        return true;
    }
    public boolean checkCol(char[][] board, int col){
        int[] nums = new int[10];
        
        for (int i = 0; i < 9; i++){
            if (board[i][col] == '.') continue;
            int index = board[i][col] - '0';
            if(nums[index] == 1)
                return false;
            nums[index] = 1;
        }
        return true;
    }
    public boolean checkBox(char[][] board, int row, int col){
        int[] nums = new int[10];
        
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
            if (board[row+i][col+j] == '.') continue;
                int index = board[row+i][col+j] - '0';
            if(nums[index] == 1)
                return false;
            nums[index] = 1;
            
            }
        }
        return true;
    }
   
}
/*
7. Reverse Integer
DescriptionHintsSubmissionsDiscussSolution
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.*

*/
class Solution {
    public int reverse(int x) {
        int res = 0;
        
        while ( x != 0 ){
          
            int newres = res * 10 + x % 10;
            if ((newres - x%10)/10 != res)
                return 0;
            res = newres;

            x = x/10;
        }
        return res;
    }
}
/*
20. Valid Parentheses
DescriptionHintsSubmissionsDiscussSolution
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
*/
// use stack to store the ohter side then when comes to the other side, give a pop and compare
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int i = 0;
        stack.push('x');
        for (i = 0; i < s.length() && !stack.isEmpty(); i++) {
            char c = s.charAt(i);
                        //System.out.println(c);

            if (c == '(')
                stack.push(')');
            if (c == '{') {
                stack.push('}');
            }
            if (c == '[')
                stack.push(']');
            
            if ( c == ')' || c == '}' || c == ']'){
                if (stack.peek() != c) 
                    return false;
                stack.pop();
            }
        }
        return  (stack.peek() == 'x');
    }
}
/*
34. Find First and Last Position of Element in Sorted Array
DescriptionHintsSubmissionsDiscussSolution
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]*/
class Solution {
    public int[] searchRange(int[] nums, int target) {
        //binary search
        int[] res = new int[2];
        res[0] = res[1] = -1;
        if (nums == null || nums.length == 0)
            return res;
        int len = nums.length;
        int low = 0, high = len -1;
        // use low + 1 < high template , after that should check low and high, cuz it will stop at low, high two postions
        while (low + 1 < high) {
            int mid = low + (high - low)/ 2;
            if (nums[mid] < target) {
                low = mid;
            }
            if (nums[mid] >= target) {
                high = mid;
            }
        }
        if (nums[low] == target) 
            res[0] = low;
        else if (nums[high] == target){
            res[0] = high;
        }
        else return res;
        
        high = len - 1;
        while (low + 1< high) {
            int mid = low + (high - low)/ 2;
            if (nums[mid] <= target) {
                low = mid;
            }
            if (nums[mid] > target) {
                high = mid;
            }
        }
        if (nums[high] == target){
            res[1] = high;
        }else if (nums[low] == target){
            res[1] = low;
        }

        return res;
    }
}
/*
146. LRU Cache
*/
class LRUCache {
    class ListNode {
        int key,val;
        ListNode next;
        public ListNode (int key, int val) {
            this.key = key;
            this.val = val;
            next = null;
        }
    }
    HashMap<Integer,ListNode> map;
    int capity;
    ListNode head, tail;
    int count;
    public LRUCache(int capacity) {
        this.capity = capacity;
        map = new HashMap<>();
        head = new ListNode(0,0);
        tail = head;
        count = 0;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        moveToTail(key);
        return tail.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            moveToTail(key);
            tail.val = value;
            return;
        }
        if (count < this.capity) {
            //map.put(key, tail);
            ListNode node = new ListNode(key, value);
            tail.next = node;
            map.put(key, tail);
            tail = node;
            count++;
            return;
        }
        int keyR = head.next.key;
        map.remove(keyR);
        head.next.key = key;
        head.next.val = value;
        map.put(key, head);
        
        moveToTail(key);
        count++;
        return;
    }
    
    public void moveToTail(int key) {
        if (tail.key == key)
            return;
        ListNode pre = map.get(key);
        ListNode node = pre.next;
        if ( tail == node)
            return;
        
        pre.next = node.next;
        tail.next = node;
        
        if(pre.next != null) {//
        // the pre.next has changed its' preNode , so need to find whether the prev.next.next is null or not;
            map.put(pre.next.key, pre);
        }
        map.put(key, tail);
        tail = node;
        
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */