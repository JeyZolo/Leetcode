

class Solution {
    public String addStrings(String num1, String num2) {
        //return num1;
        StringBuilder res = new StringBuilder();
        
        int m = num1.length() - 1, n = num2.length() - 1;
        int leading = 0;
        while (m >=0 || n >=0 || leading != 0) {
            char a =(m >= 0) ? num1.charAt(m--): '0';
            char b = (n >= 0) ?num2.charAt(n--) : '0';
            int sum = leading + (int)(a - '0' + b - '0');
            leading = sum/10;
            sum = sum%10;
            res.insert(0,sum);
            
        }

        return res.toString();
    }
}
/*

128. Longest Consecutive Sequence
DescriptionHintsSubmissionsDiscussSolution
Pick One
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
Your algorithm should run in O(n) complexity.
Example:
Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
题意：数组中找到连续的最长的一串数字的长度，不算重复；
思路：放入哈希表中，遍历每个数，
1.将其+-1得到的数，如果在哈希表中，增加长度，
2.循环1
3. 得到的长度与最大长度比较，将最大值赋给最大长度
4.返回最大长度
代码：
*/
class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i]))
                map.put(nums[i], 1);
            else map.put(nums[i],map.get(nums[i]) + 1);
        }
        
        int len = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i])) continue;
            int num = nums[i];
            int sublen = 1;int low = num-1, high = num+1;
            while(map.containsKey(low)) {// bug 1: low++放在这里了，不妥，这样的话删除该值就会出错；
                
                sublen++;
                //low--;
                map.remove(low);
                low--;
            }
            while(map.containsKey(high)){
                sublen++;
                map.remove(high);
                high++;
            }
            map.remove(num);
            len = Math.max(sublen, len);
        }
        return len;
        
    }
}
/**
101. Symmetric Tree
DescriptionHintsSubmissionsDiscussSolution
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3

   */
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return twoSymmetric(root.left, root.right);
        
    }
    public boolean twoSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) 
            return true;
        if(left == null || right == null)
            return false;
        if (left.val != right.val)
            return false;
        
        return twoSymmetric(left.left, right.right) && twoSymmetric(left.right, right.left);
    }


/*
3. Total number of different bits for two 32-bit integers.
*/

	public int diffBits(int a, int b) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if ( a== 0 && b == 0) break;
			int aa = a & 1;
			int bb = b & 1;
			count += aa ^ bb;
			a = a >> 1;
			b = b >> 1;

		}
		return count;
	}

}

/**
146. LRU Cache
DescriptionHintsSubmissionsDiscussSolution
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );
/*
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
**/
//use single list, store the previous node of current node into map, if get one, move it to tail
class LRUCache {
    class ListNode {
        int val,key;
        ListNode next;
        
        public ListNode(int key, int val) {
            this.val = val;
            this.key = key;
            this.next = null;
        }
    }
    ListNode head,tail;
    HashMap<Integer, ListNode> map;
    int capacity,size;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new ListNode(0, 0);
        map = new HashMap<>();
        tail = head;
        //head.next = tail;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        moveTotail(key);
        return tail.val;
    }

    // put the key to map, if less than capacity, put it to tail; else remove the first node, and put it to tail
    public void put(int key, int value) {
        if (get(key) != -1) {
            ListNode prev = map.get(key);
            prev.next.val = value;
            return;
        }
        
        if (size < capacity) {
            size++;
            ListNode curt = new ListNode(key, value);
            tail.next = curt;
            map.put(key, tail);
            
            tail = curt;
            return;
        }
        
        // replace the first node with new key, value
        ListNode first = head.next;
        map.remove(first.key);
        
        first.key = key;
        first.val = value;
        map.put(key, head);
        
        moveTotail(key);
        
    }
    
    
    //move to tail: if the key in tail, return, else 
    public void moveTotail (int key) {
        ListNode prev = map.get(key);
        ListNode curt = prev.next;
        
        if (tail == curt) {
            return;
        }
        
        prev.next = prev.next.next;
        tail.next = curt;
        // need to find whether the prev.next.next is null or not;
        if (prev.next != null) {
            map.put(prev.next.key, prev);
        }
        map.put(curt.key, tail);
        
        tail = curt;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
/*kth largest value in BST*/
class Solution {
    int count = 0;
    Stack<Integer> stack = new Stack<>();
    public int kthSmallest(TreeNode root, int k) {

        findKthSmall(root, k);
        return stack.pop();
    }
    
    public void findKthSmall (TreeNode node, int k) {
        if (node == null)
            return;
        if(count >= k) return;
        findKthSmall (node.right, k);
        
        if(count < k) {
          stack.push(node.val);count++;  
        }else {
            return;
        }
        
        findKthSmall (node.left, k);
        
    }
}

class Solution {
    public int getCombinantions (int n) {
        int res = 0;
        int[] dp = new int[n];
        if (n < 2) return n;
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-2] + dp[i-1];
        }
        return dp[n-1];
    }
}
/*
3. one puzzle on time and distance .. couldnt remember it sorry,
*/

/*
4. implement a single linked list , simple one :) 
*/
class ListNode {
    int val;
    ListNode next;
    public ListNode (int val) {
        this.val = val;
        next = null;
    }
}
/* 
in-order traverse a binary tree  
*/

class Solution {
    public void inorderSearch( TreeNode node) {
        if (node == null)
            return;

        inorderSearch(node.left);
        System.out.println(node.val);
        inorderSearch(node.right);

    }
}
