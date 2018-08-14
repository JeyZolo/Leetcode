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
/*
use PQ to store intervals and then search from head, if cannot merge head two, list add the head, then push the second into PQ again;

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
/*349. Intersection of Two Arrays
DescriptionHintsSubmissionsDiscussSolution
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Note:

Each element in the result must be unique.
The result can be in any order.*/
//o(n)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        // sort them then find common elements
        //hashmap to store elements of nums1, then loop nums2 to find elements in nums1
        HashMap<Integer,Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) continue;
            map.put(nums1[i], 1);
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i]))
                set.add(nums2[i]);
        }
        int[] res = new int[set.size()];int i = 0;
        for (Integer num : set) {
            res[i++] = num;
        }
        return res;
    }
}
//O(nlogn)
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }
}
