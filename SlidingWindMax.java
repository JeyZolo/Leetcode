/*239. Sliding Window Maximum
DescriptionHintsSubmissionsDiscussSolution
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

Follow up:
Could you solve it in linear time?
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null) {
            return null;
        }
        
        if(nums.length == 0) {
            return new int[0];
        }
        
        int[] res = new int[nums.length - k + 1];
        int j = 0;
        //store the max in the list
        Deque<Integer> dq = new LinkedList<>();
        
        for (int i = 0; i < nums.length; i++) {
            //2. if less than k number in dq, just add the nums
            // and check if it is greater than the top one;
            int cur = nums[i];
            while (!dq.isEmpty() && nums[dq.getLast()] < cur) {
                dq.removeLast();
            }
            if(!dq.isEmpty() && i - dq.getFirst() == k) {
                dq.removeFirst();
            }
            
            //1. add the nums into dq
            dq.offer(i);
            // when the window goes to k, it can add the max into list
            if(!dq.isEmpty() && i >= k - 1) {
                res[j++] = nums[dq.getFirst()];
            }
        }
        return res;
    }
}