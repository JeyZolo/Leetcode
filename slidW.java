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
// use level to decide how to add element to list
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
/*
655. Print Binary Tree
DescriptionHintsSubmissionsDiscussSolution
Print a binary tree in an m*n 2D string array following these rules:

The row number m should be equal to the height of the given binary tree.
The column number n should always be an odd number.
The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
Each unused space should contain an empty string "".
Print the subtrees following the same rules.
Example 1:
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]
Example 2:
Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
Example 3:
Input:
      1
     / \
    2   5
   / 
  3 
 / 
4 
Output:

[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]*/
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
    //  满二叉树，full BFS, count of node is pow(2,height) -1; 列是这个值
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new LinkedList<>();
        int height = root == null ? 1 : getHeight(root);
        int rows = height, columns = (int) (Math.pow(2, height) - 1);
        List<String> row = new ArrayList<>();
        for(int i = 0; i < columns; i++)  row.add("");
        for(int i = 0; i < rows; i++)  res.add(new ArrayList<>(row));
        populateRes(root, res, 0, rows, 0, columns - 1);
        return res;
    }

    public void populateRes(TreeNode root, List<List<String>> res, int row, int totalRows, int i, int j) {
        if (row == totalRows || root == null) return;
        res.get(row).set((i+j)/2, Integer.toString(root.val));
        populateRes(root.left, res, row+1, totalRows, i, (i+j)/2 - 1);
        populateRes(root.right, res, row+1, totalRows, (i+j)/2+1, j);
    }

    public int getHeight(TreeNode root) {
         if (root == null) return 0;
         return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
/*
253. Meeting Rooms II
DescriptionHintsSubmissionsDiscussSolution
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Timeslot {
    int time;
    boolean status;
    
    public Timeslot(int t, boolean s) {
        this.status = s;
        this.time = t;
    }
        
}
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        //List<Timelot> list = new ArrayList<>();
        if (intervals == null || intervals.length == 0)
            return 0;
        PriorityQueue<Timeslot> pq = new PriorityQueue<>(2*intervals.length, new Comparator<Timeslot>(){//Comparator
            @Override
            public int compare(Timeslot o1, Timeslot o2) {//public
                if (o1.time != o2.time)
                    return o1.time - o2.time;
                else return o1.status == false ? -1: 1;//
            }
        });
        for (Interval i : intervals) {
            Timeslot s = new Timeslot(i.start, true);
            Timeslot t = new Timeslot(i.end, false);
            pq.offer(s);
            pq.offer(t);
        }
        int res = 0;int count = 0;
        while(!pq.isEmpty()) {
            Timeslot p = pq.poll();
            if(p.status == true) {
                count++;
            }else {
                count--;
            }
            res = Math.max(count, res);
        }
        return res;
    }
                                                         
}
//2
 class Point implements Comparable<Point>{
        int t;
        int stus;
        public Point(int a, int b){
            this.t = a;
            this.stus = b;
        }
        @Override
        public int compareTo(Point that){
            if(that.t==this.t)
                return that.stus-this.stus;//相等时，要先land，再起飞，按照降序排列，1，0，1是降落，0是起飞
            return this.t-that.t;//按照升序排列
        }
    }
public class Solution {
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        // write your code here
        if(airplanes==null) return 0;
        PriorityQueue<Point> queue = new PriorityQueue<>();
        for(Interval inter: airplanes){
            queue.offer(new Point(inter.start,0));
            queue.offer(new Point(inter.end,1));
        }
        int count=0;int max = 0;
        while(!queue.isEmpty()){
            Point tp = queue.poll();
            //System.out.println(tp.t);
            if(tp.stus==0) count++;
            else count--;
            max = Math.max(max,count);
        }
        return max;
    }
}

/*
numbers of island II
*/
int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

public List<Integer> numIslands2(int m, int n, int[][] positions) {
    List<Integer> result = new ArrayList<>();
    if(m <= 0 || n <= 0) return result;

    int count = 0;                      // number of islands
    int[] roots = new int[m * n];       // one island = one tree
    Arrays.fill(roots, -1);            

    for(int[] p : positions) {
        int root = n * p[0] + p[1];     // assume new point is isolated island
        roots[root] = root;             // add new island
        count++;

        for(int[] dir : dirs) {
            int x = p[0] + dir[0]; 
            int y = p[1] + dir[1];
            int nb = n * x + y;
            if(x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) continue;
            
            int rootNb = findIsland(roots, nb);
            if(root != rootNb) {        // if neighbor is in another island
                roots[root] = rootNb;   // union two islands 
                root = rootNb;          // current tree root = joined tree root
                count--;               
            }
        }

        result.add(count);
    }
    return result;
}

public int findIsland(int[] roots, int id) {
    while(id != roots[id]) id = roots[id];
    return id;
}
//Path Compression (Bonus)
//If you have time, add one line to shorten the tree. The new runtime becomes: 19ms (95.94%).

public int findIsland(int[] roots, int id) {
    while(id != roots[id]) {
        roots[id] = roots[roots[id]];   // only one line added
        id = roots[id];
    }
    return id;
}
/*／*
690. Employee Importance
DescriptionHintsSubmissionsDiscussSolution
You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.

For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.

Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.

Example 1:
Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
Output: 11
Explanation:
Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
Note:
One employee has at most one direct leader and may have several subordinates.*/
/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
//BFS to store all neighbor into queue, poll all out and add the importance into it;
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        int value = 0;
        HashMap<Integer,Employee> map = new HashMap<>();
        
        for (Employee  e : employees) {
            map.put(e.id, e);
        }
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(map.get(id));
        
        while (!queue.isEmpty()) {
           int size = queue.size();
            
            while (size-- > 0) {
                Employee e = queue.poll();
                value += e.importance;
                for (Integer i : e.subordinates) {
                    queue.offer(map.get(i));
                }
            }
        }
       return value;
    }
}
//9/16
/*
68. Text Justification
DescriptionHintsSubmissionsDiscussSolution
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
*/
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        
        if (words == null || words.length == 0 || maxWidth == 0)
            return res;
        int i = 0, j = 0;int n = words.length;
        for (i = 0; i < words.length; i = j) {
            // get how many word in one line
            int len = -1;
            //calculate the word in one line can reach where
            for (j = i; j < n && len + words[j].length() + 1 <= maxWidth;++j ){
                len +=  words[j].length()  + 1;
            }
            int space = 1;
            int extra = 0;
            //calculate the space between words and the end space
            if(j != i + 1 && j != n) {
                space = (maxWidth - len) / (j - i - 1) + 1;
                extra = (maxWidth - len) %(j - i - 1);
                
            }
            StringBuilder row = new StringBuilder(words[i]);
            
            for (int k = i + 1; k < j; ++k) {
                //append space after every word
                addSpace(row, space);
                //apend the extra space to every word from left to right
                if(-- extra >= 0) row.append(' ');
                row.append(words[k]);
            }
            //add the left space to the end
            addSpace(row, maxWidth - row.length());
            res.add(row.toString());
        }
        return res;
    }
    public void addSpace(StringBuilder row, int count) {
        while (count -- > 0) {
            row.append(' ');
        }
    }
}

