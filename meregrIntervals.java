/*
56. Merge Intervals
DescriptionHintsSubmissionsDiscussSolution
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considerred overlapping.
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
    public List<Interval> merge(List<Interval> list) {
        
        List<Interval> res = new ArrayList<>();
        if (list == null || list.size() == 0) return res;
            int len = list.size();
            PriorityQueue<Interval> queue = new PriorityQueue<Interval>(len, new Comparator<Interval>() {
                @Override
                public int compare(Interval o1, Interval o2) {
                    if (o1.end == o2.end)
                        return o2.start - o1.start;
                    return o2.end - o1.end;
                }
                
            });
            for (Interval ex: list) {
                queue.offer(ex);
            }
   
        while (!queue.isEmpty()) {
            Interval ex1 = queue.poll();
            if (queue.isEmpty()) {
                res.add(ex1);
                break;
            }
             Interval ex2 = queue.poll();
             //System.out.println(ex1.start);
            if (ex1.start <= ex2.end) {
                ex1.start = Math.min(ex1.start,ex2.start);
                queue.offer(ex1);
            }else {
                res.add(ex1);
                queue.offer(ex2);
            }
        }
        return res;
    }
}
/*public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
            
    }*/
