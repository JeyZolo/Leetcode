/*姚柳玲
分两次循环，第一个循环，每个各走一步，然后走到最后，短的那个先到，
短的付给长的，这样等长的到null时，短的也走到了两个链表的差值，长的再赋给短的，
这样就相当于把差值先走掉，然后一起走，就可以走到共同点；*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode t1 = headA;
        ListNode t2 = headB;
        if(t1 == null || t2 == null)
            return null;
        
        while(t1 != t2) {
            t1= t1 == null ? headB : t1.next;
            t2 = t2 == null ? headA : t2.next;
        }
        return t1;
    }
}

public class Solution {
    
    //based on level order traversal
    public void connect(TreeLinkNode root) {
    	//cur的下一层和pre是在同一层，
    	/*
   	1      --cur 在这层
   /  \
  2    3   --pre,head 在这层
 / \    \
4   5    7
		*/
    	//记录下一层的头指针
        TreeLinkNode head = null; //head of the next level
        //记录这一层上一个指针
        TreeLinkNode prev = null; //the leading node on the next level
        //记录当前层
        TreeLinkNode cur = root;  //current node of current level

        while (cur != null) {
            
            //处理当前层和下一层的next指针问题
            while (cur != null) { //iterate on the current level
                //left child，当前node的左孩子不为空，
                if (cur.left != null) {
                	//左孩子的前一个node不为空，则把前一个的next赋给左孩子，否则head就是左孩子，最后pre给左孩子
                    if (prev != null) {
                        prev.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    prev = cur.left;
                }
                //right child，再处理右孩子
                if (cur.right != null) {
                    if (prev != null) {
                        prev.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    prev = cur.right;
                }
                //move to next node
                cur = cur.next;
            }
            
            //move to next level
            cur = head;
            head = null;
            prev = null;
        }
        
    }
}
/*
88.
Merge Sorted Array
Description
Solution
Submissions
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
*/
//从后往前存，这样不会覆盖掉之前的值
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1 == null || nums2 == null)
            return ;
        int i = m-1, j = n-1;
        int k = m + n -1;
        int min = Integer.MIN_VALUE;
        
        while(k >= 0) {
            nums1[k--] =(i >= 0 ? nums1[i] : min )> 
                (j >= 0 ? nums2[j] : min) ? nums1[i--] : nums2[j--];
        }
        
        
    }
}

/*
33.
Search in Rotated Sorted Array
Description
Solution
Submissions
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
先确定mid在哪里，是在上方，还是下方，上方就是》nums[low]，然后确定target在什么位置，要么在low,mid后面，要么在low,mid之间
看方向： target如果在哪块区域，t>=mid,t>=low，或者t<mid,t< low，同一方向上，这块区域时,看low，high指针
*/

class Solution {
    public int search(int[] A, int target) {
        int n = A.length;
        int lo=0,hi=n-1;
        // find the index of the smallest value using binary search.
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
        while(lo<hi){
            int mid=(lo+hi)/2;
            if(A[mid]>A[hi]) lo=mid+1;
            else hi=mid;
        }
        // lo==hi is the index of the smallest value and also the number of places rotated.
        int rot=lo;
        lo=0;hi=n-1;
        // The usual binary search and accounting for rotation.
        while(lo<=hi){
            int mid=(lo+hi)/2;
            int realmid=(mid+rot)%n;
            if(A[realmid]==target)return realmid;
            if(A[realmid]<target)lo=mid+1;
            else hi=mid-1;
        }
        return -1; 
    }
}
//23.
//Merge k Sorted Lists
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0)
            return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        
        for(int i = 0; i < lists.length; i++) {
            //think about if the sublist is[];EX: [[1],[],[2]]
            if(lists[i] == null) continue;
            pq.offer(lists[i]);
        }
        ListNode head = new ListNode(0);
        ListNode tp = head;
        while(!pq.isEmpty()) {
            ListNode node = pq.poll();
            tp.next = node;
            tp = tp.next;
            
            if(node.next != null)
                pq.offer(node.next);
            
        }
        return head.next;
    }
}

/*
378.
Kth Smallest Element in a Sorted Matrix
Description
Solution
Submissions
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note: 
PQ思路1:         //从小到大，poll的第k个就是kth smallest，所以poll, k-1次，然后第k次就是结果

思路2: 从大到小排列，存k个数，最后留下的是k个最小数，队列头部就是kth smallest
这个是思路1:
*/
class Solution {
    class Pair implements Comparable<Pair>{
        int val;
        int x, y;
        public Pair(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        @Override
        public int compareTo(Pair that){
            return this.val - that.val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0)
            return 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        int n = matrix[0].length;
        for(int i = 0; i < matrix.length; i++) {
            pq.offer(new Pair(i, 0, matrix[i][0]));
            
        }
        for(int i = 0; i < k - 1; i++) {
        //从小到大，poll的第k个就是kth smallest，所以poll, k-1次
            Pair tp = pq.poll();
            int x = tp.x;
            int y = tp.y;
            
            if(y != n-1) {
                pq.offer(new Pair(x,y+1,matrix[x][y+1]));
            }
            
        }
        return pq.poll().val;
        
    }
}

/*
20.
Valid Parentheses
Description
Solution
Submissions
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
Ou*/
class Solution {
    public boolean isValid(String s) {
        if(s == null)
            return true;
        Stack<Character> stack = new Stack<>();
        
        char[] arr = s.toCharArray();
        stack.push('x');
        for(int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if(c == '{') {
                stack.push('}');
            }
            if(c == '(') {
                stack.push(')');
            }
            if(c == '[') {
                stack.push(']');
            }
            
            if(c == ']' || c == ')' || c == '}') {
                if(stack.pop() != c)
                    return false;
            }
        }
        return stack.peek() == 'x';
    }
}
/*
1. 给一个array of tuple，每个tuple <a, b> 代表一个candidate飞去sf和nyc的cost，分别是a和b，所有candidate要平均飞去sf和nyc，
如果是奇数就，单出来的就飞去最小cost的那个city，问最小total cost。楼主直接按照diff排序解的.  
2. 给一个array 和 数字 m，求这个array的还有m个odd number的beautiful subarray的个数。楼主用一维DP解的*/
//按照 nyc - sf 排序，最短路径
class Solution {
	int min = Integer.MAX_VALUE;
	int limit;
	public int findMinCost(int[][] cost) {
			int len = cost.length;
			boolean[] visited = new boolean[len];
			int half = len/2;
			limit = half;

			helper(cost, 0, visited, 0, 0);
			return min;

	}
	public void helper(int[][] cost, int half,boolean[] visited, int cost, int count) {
			
			if(count == cost.length) {
				min = Math.min(min, cost);
				return;
			}
			for(int i = 0; i < cost.length; i++) {
				if(visited[i]) continue;
				visited[i] = true;
				if(half < limit) {
					helper(cost[i][0], half + 1, visited,cost + cost[i][1], count + 1);
				}

				helper(cost[i][1], half, visited,cost + cost[i][1], count + 1);
				visited[i] = false;

			}
	}
}

