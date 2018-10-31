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
