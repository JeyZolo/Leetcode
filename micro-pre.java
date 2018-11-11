/*402. Remove K Digits
Medium
635
30


Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.*/
class Solution {
    public String removeKdigits(String num, int k) {
        if(num == null || num.length() == 0)
            return "0";
           
        String res = "";
        int size = num.length() - k;
        char[] arr = num.toCharArray();
        if(k >= arr.length)
            return "0";

        Stack<Character> stack = new Stack<>();
        
        //store the increasing char into the stack
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            
            while(k > 0 && !stack.isEmpty() && arr[i] < stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        StringBuilder s = new StringBuilder();
        //pop 多余的char
        while(stack.size() > size) {
            stack.pop();
        }
        while(!stack.isEmpty()) {
            
            s.insert(0,stack.pop());
        }
        int i = 0;
        res = s.toString();
        //处理头部是0的情况
        while(i < res.length() && res.charAt(i) == '0') {
            i++;
        }
        res = res.substring(i);
        return res.length() == 0 ? "0" : res;
    }
}
/*
138. Copy List with Random Pointer
Medium
1029
321


A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

*/
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode node = head;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        
        while(node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        
        node = head;
        while(node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        
        return map.get(head);
            
        
    }
}
/*
151. Reverse Words in a String
Medium
372
1842


Given an input string, reverse the string word by word.

Example:  

Input: "the sky is blue",
Output: "blue is sky the".
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
Follow up: For C programmers, try to solve it in-place in O(1) space.

*/
public class Solution {
    public String reverseWords(String s) {
        String[] str = s.split("\\s+");
        
        int low = 0, high = str.length - 1;
        
        while(low < high) {
            String ss = str[low];
            str[low] = str[high];
            str[high] = ss;
            high--;
            low++;
        }
        return String.join(" ", str).trim();
    }
}
//method 2
public class Solution {
    public String reverseWords(String s) {
        int len = s.length();
        String[] arr = s.trim().split("\\s+");
        if (s == null || s.length() == 0) return s;
        
        StringBuilder res = new StringBuilder();
        
        for (int i = arr.length - 1; i >= 0; i--) {
            res.append(arr[i] + " ");
           //System.out.println(res);
        }
        return res.substring(0, res.length() -1);
        
    }
}
/*
165. Compare Version Numbers
Medium
194
894


Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

*/

class Solution {
    public int compareVersion(String version1, String version2) {
        if(version1 == null || version1.equals(version2)){
            return 0;
        }
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        //int i = 0; int j = 0;
        //System.out.println(arr1[0]+",arr2=" + arr2[0]);
        
    int length = Math.max(arr1.length, arr2.length);
    for (int i=0; i<length; i++) {
    	Integer v1 = i < arr1.length ? Integer.parseInt(arr1[i]) : 0;
    	Integer v2 = i < arr2.length ? Integer.parseInt(arr2[i]) : 0;
    	int compare = v1.compareTo(v2);
    	if (compare != 0) {
    		return compare;
    	}
    }
    
    return 0;
    }
}
/*
253. Meeting Rooms II
Medium
843
17


Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    class TimeSlot implements Comparable<TimeSlot> {
        int time;
        boolean status;
        
        public TimeSlot(int time, boolean status) {
            this.time = time;
            this.status = status;
        }
        @Override
        public int compareTo(TimeSlot that) {
            if(this.time == that.time) {
                return that.status == false ? 1 : -1;//
            }
            return this.time - that.time;
        }
    }
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0)
            return 0;
        PriorityQueue<TimeSlot> pq = new PriorityQueue<>();
        
        for(Interval inter : intervals) {
            pq.offer(new TimeSlot(inter.start, true));
            pq.offer(new TimeSlot(inter.end, false));
        }
        int res = 0;int max = 0;
        
        while(!pq.isEmpty()) {
            TimeSlot tp = pq.poll();
            if(tp.status) res++;
            else res--;
            max = Math.max(res, max);
        }
        return max;
    }
}

/*
348. Design Tic-Tac-Toe
Medium
249
19


Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up:
Could you do better than O(n2) per move() operation?

*/
class TicTacToe {

    /** Initialize your data structure here. */
    
    int n;
    int win;
    int[] rows;
    int[] cols;
    int diagonal = 0, antiDiagonal = 0;;
    public TicTacToe(int n) {
        this.n = n;
        win = 0;
        rows = new int[n];
        cols = new int[n];
        
        //int diacheck = 0, rediacheck = 0;
        
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
            int toAdd = player == 1 ? 1 : -1;

            rows[row] += toAdd;
            cols[col] += toAdd;
            if (row == col)
            {
                diagonal += toAdd;
            }

            if (col == (cols.length - row - 1))
            {
                antiDiagonal += toAdd;
            }

            int size = rows.length;
            if (Math.abs(rows[row]) == size ||
                Math.abs(cols[col]) == size ||
                Math.abs(diagonal) == size  ||
                Math.abs(antiDiagonal) == size)
            {
                return player;
            }

            return 0;
    }
    

}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */

/*
*/
import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
  public static int calculator(String s) {
      Stack<Integer> stack = new Stack<>();
      
      char sign = '+';
      
      for (int i = 0; i < s.length() ; i++) {
        
          char c = s.charAt(i);
          
          
          if(c == '+' || c == '-' || c == '*' || c == '/') {
              sign = c;
          }
          
          if(c >= '0' && c <= '9') {
              int j = i;
              
              while(j <= s.length() - 1 && c >= '0' && c <= '9'){
                
                   j++;
                
                   if(j < s.length()) {
                      
                   }
                  
                  if (j == s.length()) {
                    break;
                  }
                  c = s.charAt(j);
              }
            
            int num = Integer.valueOf(s.substring(i,j));
            i = j -1;
            
            int negetive = 1;   
            if( sign == '+' || sign == '-'){
              
               negetive = (sign == '+' ? 1 : -1);
               stack.push(negetive * num);
              
            } 
            
            if(sign == '*') {
              int pre = stack.pop();
              stack.push(num * pre);
            }
            
            if(sign == '/') {
              int pre = stack.pop();
              stack.push(pre/num);
            }

         }
          
    }    
    int res = 0;
    while(!stack.isEmpty()) {
         res += stack.pop();
    }
    return res;
  }
  
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java " + Runtime.version().feature());

    int res = calculator("-6+ 7*3");
    System.out.println(res);

    for (String string : strings) {
      System.out.println(string);
    }
  }
}
// Can you edit it?
//(6+17-3*6)/4
//stack: ( 6 +17 -18 )

