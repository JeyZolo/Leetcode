import java.util.*;
import java.io.*;

/*
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
*/
// mthod 1: no reverse no stack
/**
 * Definition for singly-linked list.
 */


class Solution {

public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
         HashMap<Integer, Integer> hm1 = new HashMap<>(); //Store the 'index' and the value of List1
        HashMap<Integer, Integer> hm2 = new HashMap<>(); //Store the 'index' and the value of List2
        int i = 1, j = 1;
        
        while(l1 != null){
            hm1.put(i, l1.val);
            l1 = l1.next;
            i++;
        }
        while(l2 != null){
            hm2.put(j, l2.val);
            l2 = l2.next;
            j++;
        }
        
        int carry = 0;
        i--; j--;
        ListNode head = null;
        
      //Create new nodes to the front of a new LinkedList
        while(i > 0 || j > 0 || carry > 0){

            int a = i > 0 ? hm1.get(i) : 0;
            int b = j > 0 ? hm2.get(j) : 0;
            int res = (a + b + carry) % 10;
            
            ListNode newNode = new ListNode(res);
            newNode.next = head;
            head = newNode;
            
            carry = (a + b + carry) / 10;
            i--; j--;
        }
        return head;
    }

// method 2: Use stack
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        
        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }
        
        return list.val == 0 ? list.next : list;
    }

// method 3: use reverse list
  public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {                
         if(l1 == null) return l2;
         if(l2 == null) return l1;
         
         int flag = 0;
         ListNode res = new ListNode(0);
         ListNode tmp = new ListNode(0);
         res = tmp;
           
         l1 = reverse(l1);
         l2 = reverse(l2);
           
         while(l1 != null || l2 != null){
             int a = l1 == null?0:l1.val;
             int b = l2 == null?0:l2.val;
             ListNode ss = new ListNode((a+b+flag)%10);
             flag = (a+b+flag) /10;
             tmp.next = ss;
             tmp = ss;
               
             l1 = l1 == null? null:l1.next;
             l2 = l2 == null? null:l2.next;
             
         }
        
         if(flag == 1){
             
             ListNode ss = new ListNode(1);
             tmp.next = ss;
             
         }                       
           return reverse(res.next);            
    }
    
    
    public ListNode reverse(ListNode l1){
        
        if(l1.next == null) return l1;
        
        ListNode p = new ListNode(0);
        ListNode q = new ListNode(0);
        
        p = l1;
        q = l1.next;
        p.next = null;
        
        while(q != null){            
            p = q;
            q = q.next;
            p.next = l1;
            l1 = p;            
        }        
       return l1;
    }
    /*
    2. 一个数组里有两种颜色 排序数组使得每个颜色的排列在一起； followup 如果有三种颜色怎么办？
LP：high standards, bias for action*/
public static String[] sortColor(String[] list) {
	if(list == null || list.length == 0)
		return list;
	String color1 = list[0];
	String color2 = "";
	int low = 0, high = list.length - 1;

	while (low < high) {
		while(low < high && list[low].equals(color1)) low++;
		while(low < high && !list[high].equals(color1)) high--;
		if (!list[low].equals(color1) && list[high].equals(color1)) {
			color2 = list[low];
			list[low] = color1;
			list[high] = color2;
		}

	}
	return list;

}
/*
692.
Top K Frequent Words
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
T*/
class StrPair implements Comparable<StrPair>{
    String word;
    int freq;
    public StrPair(String word, int f) {
        this.word = word;
        this.freq = f;
    }
    @Override
    public int compareTo(StrPair that){
        if (that.freq == this.freq) {
            return that.word.compareTo(this.word);// two string can use compareTo
        }else 
            return this.freq - that.freq;// 降序排列
    }
}
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0)
            return new ArrayList<>();
        PriorityQueue<StrPair> pq = new PriorityQueue<>();
        HashMap<String,Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        
        for (String w: words) {
            if(!map.containsKey(w)) {
                map.put(w, 1);
                //pq.offer(new StrPair(w, 1));
            }else {
                map.put(w, map.get(w) + 1);
            }
        }
        for (Map.Entry<String,Integer> pair: map.entrySet()) {// not EntrySet
            
            pq.offer(new StrPair(pair.getKey(), pair.getValue()));
            if(pq.size() > k)
               pq.poll();
            
        }
        int i = 0;
        while(!pq.isEmpty()) {
            res.add(0, pq.poll().word);
            //i++;
        }
        return res;
    }

public static void main(String[] args) {
	String[] colors = {"red","red","white","red","white","white","red"};
	String[] res = sortColor(colors);
	for (String s: res) {
		System.out.println(s);

	}
}
}