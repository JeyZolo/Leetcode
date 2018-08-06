/*
Sort a linked list, improve the performance by multi-thread on multi-core system.  
148. Sort List
DescriptionHintsSubmissionsDiscussSolution
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    //merge sort
    public ListNode sortList(ListNode head) {
        
        return mergeSort(head);
    }
    public ListNode mergeSort(ListNode head) {
        
        if(head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head;
        
        while (fast != null && fast.next != null && fast.next.next != null) { //bug2:// need to chech fast.next.next, so it will stop at middle
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        ListNode left = mergeSort(head);
        right = mergeSort(right);
        return merge(left, right, new ListNode(0));

    }
    public ListNode merge(ListNode left, ListNode right, ListNode tmp) {
        int max = Integer.MAX_VALUE;//bug1: not min, select min so give it max
        ListNode dummy = tmp;
        while (left != null || right != null) {
            tmp.next = (left == null ? max : left.val) < (right == null ? max : right.val)? left : right;
            if (left != null && tmp.next == left)
                left = left.next;
            if (right != null && tmp.next == right)
                right = right.next;
            tmp = tmp.next;
        }
        return dummy.next;
    }
    
}
//=============================================
public class MergeSort extends RecursiveAction {
    final int[] numbers;
    final int startPos, endPos;
    final int[] result;

    private void merge(MergeSort left, MergeSort right) {
        int i=0, leftPos=0, rightPos=0, leftSize = left.size(), rightSize = right.size();
        while (leftPos < leftSize && rightPos < rightSize)
            result[i++] = (left.result[leftPos] <= right.result[rightPos])
                ? left.result[leftPos++]
                : right.result[rightPos++];
        while (leftPos < leftSize)
            result[i++] = left.result[leftPos++];
        while (rightPos < rightSize)
        result[i++] = right.result[rightPos++];
    }

    public int size() {
        return endPos-startPos;
    }

    protected void compute() {
        if (size() < SEQUENTIAL_THRESHOLD) {
            System.arraycopy(numbers, startPos, result, 0, size());
            Arrays.sort(result, 0, size());
        } else {
            int midpoint = size() / 2;
            MergeSort left = new MergeSort(numbers, startPos, startPos+midpoint);
            MergeSort right = new MergeSort(numbers, startPos+midpoint, endPos);
            coInvoke(left, right);
            merge(left, right);
        }
    }
}
/*
How to find the shortest path from city A to B.  

Implement a string sorting algorithm in C without the use of any standard library calls.  
Implement a doubly linked list delete operation.
*/
class Solution {
    class DLinkedList {
        int val;
        DLinkedList pre, next;
        public DLinkedList(int val) {
            this.val = val;
            this.pre = null;
            this.next = null;
        }

    }

    public deleteDNode (DLinkedList node) {
        DLinkedList pre = node.pre;
        DLinkedList next = node.next;

        pre.next = next;
        next.pre = pre;
    }
}
/*
Given a short C program, find all bugs. 

printing elements in sorted order of a row sorted matrix. 

 Design an elevator system
 */
//  Alien Dictionary
 
 class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = constructGraph(words);
        return topologicalSorting(graph);
    }
    
        
    private Map<Character, Set<Character>> constructGraph(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        
        // create nodes
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (!graph.containsKey(c)) {
                    graph.put(c, new HashSet<Character>());
                }
            }
        }
        
        // create edges
        for (int i = 0; i <  words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                if (words[i].charAt(index) != words[i + 1].charAt(index)) {
                    graph.get(words[i].charAt(index)).add(words[i + 1].charAt(index));
                    break;
                }
                index++;
            }
        }

        return graph;
    }
    
    private Map<Character, Integer> getIndegree(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();
        for (Character u : graph.keySet()) {
            indegree.put(u, 0);
        }
        
        for (Character u : graph.keySet()) {
            for (Character v : graph.get(u)) {
                indegree.put(v, indegree.get(v) + 1);
            }
        }     
        
        return indegree;
    }

    private String topologicalSorting(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = getIndegree(graph);
        // as we should return the topo order with lexicographical order
        // we should use PriorityQueue instead of a FIFO Queue
        Queue<Character> queue = new PriorityQueue<>();
        
        for (Character u : indegree.keySet()) {
            if (indegree.get(u) == 0) {
                queue.offer(u);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character head = queue.poll();
            sb.append(head);
            for (Character neighbor : graph.get(head)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        if (sb.length() != indegree.size()) {
            return "";
        }
        return sb.toString();
    }
}

/*
1. Two Sum
DescriptionHintsSubmissionsDiscussSolution
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        if (nums == null || nums.length == 0)
            return res;
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                res[0] = map.get (target - num);
                res[1] = i;
                return res;
            }
            map.put (num, i);
        }
        return res;
    }
}

// 3 sum
public List<List<Integer>> threeSum(int[] num) {
    Arrays.sort(num);
    List<List<Integer>> res = new LinkedList<>(); 
    for (int i = 0; i < num.length-2; i++) {
        if (i == 0 || (i > 0 && num[i] != num[i-1])) {
            int lo = i+1, hi = num.length-1, sum = 0 - num[i];
            while (lo < hi) {
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    while (lo < hi && num[lo] == num[lo+1]) lo++;
                    while (lo < hi && num[hi] == num[hi-1]) hi--;
                    lo++; hi--;
                } else if (num[lo] + num[hi] < sum) lo++;
                else hi--;
           }
        }
    }
    return res;
}
//112. Path Sum

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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && sum == root.val){
            return true;
        }
        return hasPathSum(root.left, sum - root.val) ||
                hasPathSum(root.right, sum - root.val);
    }
}
/*1. Tree Node Path Sum,destination node可以不是leaf，把所有路径print出来
2. BST，求任意两node的距离，followup是有什么情况解法是cover不了的*/
//113. Path Sum II

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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        findPath(root, res, new ArrayList<>(), sum);
        return res;          
    }
    public void findPath(TreeNode root,List<List<Integer>> res, List<Integer> sublist, int sum ) {
        if (root == null)
            return ;
        sublist.add(root.val);
        
        if (root.left == null && root.right == null && sum - root.val == 0) {
           // sublist.add(root.val);
            res.add(new ArrayList<>(sublist));
            sublist.remove(sublist.size() - 1);
            return;
        }
                 
        findPath(root.left, res, sublist, sum - root.val);
        findPath(root.right, res, sublist, sum - root.val);
            
        sublist.remove(sublist.size() - 1);
    }
}
//437. Path Sum III
/*DescriptionHintsSubmissionsDiscussSolution
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

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

    // search from root , 
    public int pathSum(TreeNode root, int sum) {

        if (root == null) return 0;
         return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        
    }
    public int findPath(TreeNode root, int sum) {
        if (root == null)
            return 0;
        
        return (sum == root.val ? 1: 0) + findPath(root.left, sum - root.val) +
                        findPath(root.right, sum - root.val);

        
    }
}
/*

1. 利口腰三把，设计iterator
/*第一遍扫的时候巧妙运用next指针， 开始数组是1->2->3->4  。 然后扫描过程中 先建立copy节点 1->1`->2->2`->3->3`->4->4`, 然后第二遍copy的时候去建立边的copy， 拆分节点, 一边扫描一边拆成两个链表，这里用到两个dummy node。第一个链表变回  1->2->3 , 然后第二变成 1`->2`->3`  */
//No HashMap version
public class Solution {
    private void copyNext(RandomListNode head) {
        while (head != null) {
            RandomListNode newNode = new RandomListNode(head.label);
            newNode.random = head.random;
            newNode.next = head.next;
            head.next = newNode;
            head = head.next.next;
        }
    }

    private void copyRandom(RandomListNode head) {
        while (head != null) {
            if (head.next.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }

    private RandomListNode splitList(RandomListNode head) {
        RandomListNode newHead = head.next;
        while (head != null) {
            RandomListNode temp = head.next;
            head.next = temp.next;
            head = head.next;
            if (temp.next != null) {
                temp.next = temp.next.next;
            }
        }
        return newHead;
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        copyNext(head);
        copyRandom(head);
        return splitList(head);
    }
}
//2. 利口妖气， 设计log system存request和response
public class Solution {
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        // write your code here
        String[] phone = {"","abc","def","ghi", "jkl", "mno", "pqrs", "tuv","wxyz"};
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) 
            return res;
        dfs (res, phone,"", digits);
        return res;
    }
    public void dfs (List<String> res, String[] phone,String str, String digits) {
        
        if(digits.length() == 0) {
            res.add(str);
            return;
        }

            int index = digits.charAt(0) - '0'-1;
            String substr = phone[index];
            for (int i = 0; i < substr.length(); i++) {
                dfs (res, phone, str + substr.substring(i,i+1), digits.substring(1));
            }
    }