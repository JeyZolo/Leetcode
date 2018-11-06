/*215.
Kth Largest Element in an Array
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
O(N lg K) running time + O(K) memory

*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums){
            pq.offer(num);
            //留下k个最大值，第一个就是里面第k个最大值，比如，1，2，3，4，5，第二大：pq，进去2个数，最后只剩下4，5，第二大就是4
            if(pq.size() > k)
                pq.poll();
        }
            
        return pq.poll();
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
/*coding（15min）: 算根号2（直接二分），follow up: 输入可能有负数怎么办，我说输出string，末尾加个i就行，输入是正数就不加，他说好，秒改. more info on 1point3acres
69.
Sqrt(x)*/
class Solution {
    public int mySqrt(int x) {
        // from 1 ..x, find y :y*y = x;
        if(x == 1)
            return 1;
        int low = 1, high = x;
        while(low + 1 < high) {
            int mid = low + (high - low)/2;
            //用mid*mid的话 会有越界的问题
            if(mid  > x/mid) {
                high = mid;
            }else 
                low = mid;
        }
        
        
        if(low * low <= x)
            return low;
        if(high * high <= x)
            return high;
        
        
        return -1;
    }
}
/*题目是给一个List of string pairs, 每一个pair里面是两个名字, 第一个人是第二个人的上级. 要求写一个算法返回其中职位最高的人. 
// 1 扫一遍下级，没有在下级list里面的就是最高上级；如果是完全一对一的链表的话
2. union find的话，直接返回find结果，即可
*/
class Solution {

        ///============method 1-union find======================

    String[] father;
    public String findHighest(String[][] managers) {
        //(1,2),(3,1),(2,3);
        Set<String> set = new HashSet<>();

        for(String[] s : managers) {
            set.add(s[0]);
            set.add(s,1);
        }
        int n = set.size();
        father = new String[n];

        for(String[] s: managers) {
            connect(s[1],s[0]);
        }
        return find(managers[0][0]);

    }
    public String find(String a) {
        String root = father[a];
        if(root.equals(a))
            return a;
        return father[a] = find(father[a]);        

    }
    public void connect(String a, String b) {
        String root_a = find(a);
        String root_b = find(b);
        if(!root_a.equals(root_b)) {
            father[root_a] = root_b;
        }
    }
    ///============method 2 - hashmap======================
    public List<Character> findRoute(List<List<Character>> sub) {
        List<Character> res = new ArrayList<>();
        if(sub == null )
            return res;

        HashSet<Character> outlist = new HashSet<>();
        HashMap<Character, Character> map = new HashMap<>();
        
        for (int i = 0; i < sub.size(); i++) {
            char in = sub.get(i).get(0);
            char out = sub.get(i).get(1);
            map.put(in, out);
            outlist.add(out);
        }
        Character start = ' ';
        for (int i = 0; i < sub.size(); i++) {
            char in = sub.get(i).get(0);
            if(!outlist.contains(in)) {
                start = in;
            }
        }
        //res.add(start);
        // res = {'A'}
        // map = {'C':'D', 'B':'C', 'A':'B'}
        while(map.containsKey(start)) {
            res.add(start);       
            start = map.get(start);
        }
        if(start != ' ')
            res.add(start);
        return res;
    }

}
/*

759.
Employee Free Time
*//**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> result = new ArrayList<>();

        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        avails.forEach(e -> pq.addAll(e));

        Interval temp = pq.poll();
        while(!pq.isEmpty()) {
            if(temp.end < pq.peek().start) { // no intersect
                result.add(new Interval(temp.end, pq.peek().start));
                temp = pq.poll(); // becomes the next temp interval
            }else { // intersect or sub merged
                temp = temp.end < pq.peek().end ? pq.peek() : temp;
                pq.poll();
            }
        }
        return result; 
    }
}


