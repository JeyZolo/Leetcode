/*
593.
Valid Square
Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:
Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
Note:

All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.
*/
class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        long[] dist = {distance(p1,p2),distance(p1,p3),
                      distance(p1,p4),distance(p2,p3),
                      distance(p2,p4),distance(p3,p4)};
        long lside = 0;long side = 0;
        for(long len : dist) {
            lside = Math.max(len, lside);
            if(len != lside) {
                side = len;
            }
            
        }
        int lcount = 0, count = 0;
        for(long len : dist) {
            if(len == lside)
                lcount++;
            if(len == side)
                 count++;
        }
        return lcount == 2 && count == 4;
        
    }
    public long distance(int[] p1, int[] p2) {
        return (long)Math.pow(p1[0]-p2[0], 2) + (long)Math.pow(p1[1]-p2[1], 2);
    }
}
/*
564.
Find the Closest PalindromeGiven an integer n, find the closest integer (not including itself), which is a palindrome.

The 'closest' is defined as absolute difference minimized between two integers.

Example 1:
Input: "123"
Output: "121"
Note:
The input n is a positive integer represented by string, whose length will not exceed 18.
If there is a tie, return the smaller one as answer.*/
//题意，找距离该数值绝对距离最小的回文
public class Solution {
    public String mirroring(String s) {
        String x = s.substring(0, (s.length()) / 2);
        return x + (s.length() % 2 == 1 ? s.charAt(s.length() / 2) : "") + new StringBuilder(x).reverse().toString();
    }
    public String nearestPalindromic(String n) {
        if (n.equals("1"))
            return "0";

        String a = mirroring(n);
        long diff1 = Long.MAX_VALUE;
        diff1 = Math.abs(Long.parseLong(n) - Long.parseLong(a));
        if (diff1 == 0)
            diff1 = Long.MAX_VALUE;

        StringBuilder s = new StringBuilder(n);
        int i = (s.length() - 1) / 2;
        while (i >= 0 && s.charAt(i) == '0') {
            s.replace(i, i + 1, "9");
            i--;
        }
        if (i == 0 && s.charAt(i) == '1') {
            s.delete(0, 1);
            int mid = (s.length() - 1) / 2;
            s.replace(mid, mid + 1, "9");
        } else
            s.replace(i, i + 1, "" + (char)(s.charAt(i) - 1));
        String b = mirroring(s.toString());
        long diff2 = Math.abs(Long.parseLong(n) - Long.parseLong(b));


        s = new StringBuilder(n);
        i = (s.length() - 1) / 2;
        while (i >= 0 && s.charAt(i) == '9') {
            s.replace(i, i + 1, "0");
            i--;
        }
        if (i < 0) {
            s.insert(0, "1");
        } else
            s.replace(i, i + 1, "" + (char)(s.charAt(i) + 1));
        String c = mirroring(s.toString());
        long diff3 = Math.abs(Long.parseLong(n) - Long.parseLong(c));

        if (diff2 <= diff1 && diff2 <= diff3)
            return b;
        if (diff1 <= diff3 && diff1 <= diff2)
            return a;
        else
            return c;
    }
}

/*
599.
Minimum Index Sum of Two Lists
Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

Example 1:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".
Example 2:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
Note:
The length of both lists will be in the range of [1, 1000].
The length of strings in both lists will be in the range of [1, 30].
The index is starting from 0 to the list length minus 1.
No duplicates in both lists.
Seen th
*///题意： 找两个表里面共同的string,index之和最小的
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String,Integer> map = new HashMap<>();
        if(list1 == null || list2 == null)
            return new String[0];
        for(int i = 0; i < list1.length; i++) {
            if(!map.containsKey(list1[i]))
                map.put(list1[i], i);
        }
        Stack<Integer> stack = new Stack<>();
        
        int minIndex = list1.length+list2.length;
        for(int i = 0; i < list2.length;i++) {
            if(map.containsKey(list2[i])){
                int sum = map.get(list2[i]) + i;
                map.put(list2[i],sum);
                while(!stack.isEmpty() && 
                      map.get(list2[stack.peek()]) > sum){
                    stack.pop();
                }
                if(stack.isEmpty()  || 
                   map.get(list2[stack.peek()]) == sum)
                    stack.push(i);
            }
        }
        String[] res = new String[stack.size()];
        int j = 0;
        while(!stack.isEmpty()){
            int i = stack.pop();
            res[j++] = list2[i];
        }

        return res;
    }
}
//用list
public class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap < String, Integer > map = new HashMap < String, Integer > ();
        for (int i = 0; i < list1.length; i++)
            map.put(list1[i], i);
        List < String > res = new ArrayList < > ();
        int min_sum = Integer.MAX_VALUE, sum;
        for (int j = 0; j < list2.length && j <= min_sum; j++) {
            if (map.containsKey(list2[j])) {
                sum = j + map.get(list2[j]);
                if (sum < min_sum) {
                    res.clear();
                    res.add(list2[j]);
                    min_sum = sum;
                } else if (sum == min_sum)
                    res.add(list2[j]);
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
/*

239.
Sliding Window Maximum
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note: 
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:*/
//滑动窗口，求每个窗口的最大值
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return new int[0];
        int len = nums.length;
        Deque<Integer> dq = new LinkedList<>();
        int[] res = new int[len - k + 1];
        int j = 0;
        //思路： 遍历每个元素，检查当前值与队列尾部的值，比较大小，大于队列里面的值，就把小于他的值全都删除
        //2. 将该值放进队列尾端
        //3. 检查是否超过了k个元素，队列头部的index是否在k个元素内，如不是，删除头部元素；
        //三步走：把小的值删除，只留下大值，大值在队列里面递减存储，每次加入，看是不是比队列里的大，看是不是超过了k个范围的窗口，然后存入该值，队列头部的值就是滑动窗口的最大值；
        for(int i = 0; i < len; i++) {
            int num = nums[i];
            while(!dq.isEmpty() && nums[dq.getLast()] < num){
                dq.pollLast();
            }
            dq.offer(i);

            //检查是否已经超过k个窗口
            if(dq.getFirst() + k <= i){
                dq.pollFirst();
            }
            //i走到k-1时，这个窗口结束，这个窗口的头部就是最大值
            if(i >= k-1){
                res[j++] = nums[dq.getFirst()];
            }
        }
        return res;
    }
}
//{"a","b","c","a"}只出现一次的最大长度
/*
332.
Reconstruct Itinerary
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
Seen this question in a real interview before?

Yes

No
Contributor
dietpepsi
*/
//从头走，走到走不动，把最后一个放入list，然后再 接着走
class Solution {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
        List<String> route = new LinkedList();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.empty()) {
            while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty()){
                
                stack.push(targets.get(stack.peek()).poll());
                //System.out.println(stack.peek());
            }
            route.add(0, stack.pop());
        }
        return route;  
    }
}
///word ladder2
public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */
    /*
    思路：先bfs算出到每个字母的最短距离，再dfs，算出所有方案；
    */
  public List<List<String>> findLadders(String start, String end, List<String> dict) {
 
        List<List<String>> res = new ArrayList<>();
        HashMap<String,List<String>> map = new HashMap<>();
        HashMap<String,Integer>dist = new HashMap<>();
        Set<String> set = new HashSet<>();
        
        for(String w: dict) {
            set.add(w);
        }
        
        if(!set.contains(end)) {
            return res;
        }

        set.add(start);
        bfs (start,end,set,dist,map);
        dfs (res,start,end,set,dist,new ArrayList<>(),map);
        return res;
    }
    public void bfs(String start, String end, Set<String> dict,
                    HashMap<String,Integer>dist,
                    HashMap<String,List<String>> map) {
            Queue<String> queue = new LinkedList<>();
            queue.offer(start);
            int level = 0;
            dist.put(start,0);
            
            while(!queue.isEmpty()) {
                level ++;
                int size = queue.size();
                for (int i = 0; i <size; i++) {
                    String str = queue.poll();
                    if(!map.containsKey(str))
                        map.put(str, new ArrayList<>());
                    char[] arr = str.toCharArray();
                    for (int k = 0; k <arr.length; k++){
                        char c = arr[k];
                        for (int j = 0; j < 26; j++) {
                            
                            arr[k] = (char)('a' + j);
                            String newstr = new String(arr);
                            if(!dict.contains(newstr) || newstr.equals(str)) continue;
                            if(!dist.containsKey(newstr)){
                                dist.put(newstr, level);
                                queue.offer(newstr);
                            }
                            map.get(str).add(newstr);
                        }
                        arr[k] = c;
                    }
        
                }
                
            }
            
        }
    public void dfs(List<List<String>> res, String str, String end, Set<String> dict              ,HashMap<String,Integer> dist,List<String> path,
                HashMap<String,List<String>> map) {
            int distance = 0;
            path.add(str);

            if(str.equals(end)) {
                res.add(new ArrayList<>(path));
            }else {
                 for (String substr : map.get(str)) {
                    if (dist.containsKey(substr) && dist.get(substr) == dist.get(str) +1 ){
                        dfs(res,substr,end,dict,dist,path,map);
                    }
                }               
            }
            path.remove(path.size()-1);

        }
}
/*
56.
Merge Intervals
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considerred overlapping.*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
//思路：按照start排序，近PQ，然后每个end与后面的start比较，如果有交叉，就更新end，放入结果list中
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals == null || intervals.size() == 0)
            return intervals;
        PriorityQueue<Interval> pq = new PriorityQueue<>(intervals.size(), new Comparator<Interval>(){
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start == o2.start)
                    return o1.end - o2.end;
                return o1.start - o2.start;
            }
        });
        for(Interval inter : intervals) {
            pq.offer(inter);
        }
        List<Interval> res = new ArrayList<>();
        while(!pq.isEmpty()) {
            Interval from = pq.poll();
            int start = from.start;
            int end = from.end;
            while(!pq.isEmpty() && end >= pq.peek().start) {
                end = Math.max(end, pq.peek().end);
                pq.poll();
            }
            from.end = end;
            res.add(from);
        }
        return res;
    }
}
/*
238.
Product of Array Except Self
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)*/
//思路：第一遍将上一个数的乘积给output，第二遍，把后面的乘积给output
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0)
            return nums;
        int[] output = new int[nums.length];
        output[0] = 1;
        for(int i = 1; i < nums.length; i++) {
            output[i] = nums[i - 1] * output[i - 1];
        }
        int right = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            output[i] = output[i] * right;
            right = nums[i] * right;
        }
        return output;
    }
}
/*
380.
Insert Delete GetRandom O(1)
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();
思路： 加一个list,array能平均概率，map里存的是值和对应的index，
每个数加入到最后面，然后删除的时候，把最后面的数和该值调换，删除最后的值就可以了。
// Removes 1 from the set, returns true. Set now conta*/
class RandomizedSet {

    /** Initialize your data structure here. */
    HashMap<Integer,Integer> map;
    int index;
    List<Integer> list;
    Random rand= new Random();
    public RandomizedSet() {
        map = new HashMap<>();
        index = 0;
        list = new ArrayList<>();
        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(!map.containsKey(val)){
            map.put(val, list.size());
            list.add(val);
            return true;
        }
        return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;
        int last = list.get(list.size() - 1);
        int index = map.get(val);
        if(index < list.size()){
            list.set(index, last);
            map.put(last,index);
        }
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
/*
Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] alb = new int[256];
        if(s == null || s.length() == 0)
            return 0;
        int j = 0;int maxLen = 0;
        for(int i = 0; j < s.length() && i < s.length(); i++) {
            char c = s.charAt(j);
            
            while(j < s.length() && alb[c] == 0) {
                alb[c]++;
                j++;
                if(j < s.length())
                    c = s.charAt(j);
            }
            maxLen = Math.max(maxLen, j - i);
            alb[s.charAt(i)]--;
        }
        return maxLen;
    }
}
// if it's String[]
class Solution {
    public List<String> lengthOfLongestSubstring(String[] s) {
        HashMap<String,Integer> map = new HashMap<>();
        if(s == null || s.length == 0)
            return 0;
        int j = 0;int maxLen = 0;int first = 0;
        for(int i = 0; j < s.length && i < s.length; i++) {
            String sub = s[j];
            
            while(j < s.length() && !map.containsKey(sub)) {
                map.put(sub,1);
                j++;
                if(j < s.length())
                    c = s.charAt(j);
            }
            if(maxLen < j - i) {
            	first = i;
            	maxLen = j - i;
            }
            if(map.get(s[i]) == 1)
            	map.remove(s[i]);
            map.put(s[i], map.get(s[i])-1);
        }
        for(int i = first; i < maxLen; i++) {
        	res.add(s[i]);
        }
        return res;
    }
}
/*
347.
Top K Frequent Elements
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.*/
class Solution {
    //use PQ 用堆排序
    class Pair implements Comparable<Pair> {
        int val, freq;
        public Pair(int val, int freq)  {
            this.val = val;
            this.freq = freq;
        }
        public int compareTo(Pair that) {
            return this.freq - that.freq;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], 0);
            }
            map.put(nums[i], map.get(nums[i]) + 1);
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            
            pq.offer(new Pair(entry.getKey(), entry.getValue()));
            if(pq.size() > k)
                pq.poll();
        }
        while(!pq.isEmpty()) {
            res.add(0,pq.poll().val);
        }
        return res;
                     
    }
}
//用桶排序
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        //use bucket to store num with the sam freq
        List<Integer>[] bucket = new List[nums.length + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], 0);// line13 add freq
            }
            map.put(nums[i], map.get(nums[i])+1);
        }
        for (Map.Entry<Integer,Integer> pair : map.entrySet()) {
            int freq = pair.getValue();
            //System.out.println(freq);
            if(bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(pair.getKey());
        }
        for (int i = nums.length; i > 0 && k > 0; i--) {
            if (bucket[i] == null) continue;
            for (Integer num : bucket[i]){
                res.add(num);
                k--;
                if (k <= 0) return res;
            }
        }
        return res;
        
    }
}
/*
253.
Meeting Rooms II
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
Seen*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
//扫描线:排序所有的time，分为开房间，关房间，开房间，如果没关上，下一个开房间就要增加一个房间，关上了，就减掉一个房间，如果同一时间，先关上房间，再打开房间；找到最大数即可；
class Solution {
    class TimeRange implements Comparable<TimeRange> {
        int time;
        boolean status;
        public TimeRange(int time, boolean status) {
            this.time = time;
            this.status = status;
        }
        public int compareTo(TimeRange that) {
            //compare the time, if time is the same ,which one is first, if status is false, false shouble be first, close first then can open;
            if(that.time == this.time)
                return that.status == false? 1 : -1;
            return this.time - that.time;
        }
    }
    public int minMeetingRooms(Interval[] intervals) {
        int len = intervals.length;
        if(intervals == null || intervals.length == 0)
            return 0;
        PriorityQueue<TimeRange> pq = new PriorityQueue<>();
        
        for(Interval inter : intervals) {
            int start = inter.start;
            int end = inter.end;
            pq.offer(new TimeRange(start, true));
            pq.offer(new TimeRange(end, false));
            
        }
        int count = 0, max = 0;
        while(!pq.isEmpty()) {
            TimeRange t = pq.poll();
            if(t.status == true)
                count++;
            else count--;
            max = Math.max(max, count);
        }
        return max;
        
    }
}
/*
139.
Word Break
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Examp*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for(String w : wordDict){
            set.add(w);
        }
        HashMap<String,Boolean> map = new HashMap<>();
        return dfs(s, set, map);
    }
    public boolean dfs(String s, Set<String> set,
                       HashMap<String,Boolean> map) {
        boolean r = false;
        if(s.length() == 0)
            return true;
        
        if(map.containsKey(s))
            return map.get(s);
        for(int i = 0; i <= s.length(); i++){
            String substr = s.substring(0,i);
            if(set.contains(substr) ) {
                
                boolean res = dfs(s.substring(i), set, map);
                if(res == true)
                    return true;
                map.put(substr, res);
            }
        }
        map.put(s, r);
        return false;
    }
}
/*
49.
Group Anagrams
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.
See*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String,List<String>> map = new HashMap<>();
        
        for(String s: strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
/*
200.
Number of Islands
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
Seen*/
class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        int count = 0;
        
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1'){
                    
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    public void dfs(char[][] grid, int i, int j) {
        if(i < 0 || j < 0 || i >= grid.length 
           || j >= grid[0].length || grid[i][j] == '0' )
            return;
        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};
        grid[i][j] = '0';
        //visted[i][j] = true;
        for(int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            dfs(grid, x, y);
        }
        return;
    }
}