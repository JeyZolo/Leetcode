／*
654. Sparse Matrix Multiplication
Given two Sparse Matrix A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example
A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*／
public class Solution {
    /**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int m = A[0].length;
        int k = B[0].length;

        int[][] C = new int[n][k];

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (A[i][j] != 0)
                    for (int l = 0; l < k; ++l)
                        C[i][l] += A[i][j] * B[j][l];
        return C;
        }
}
/*
840. Range Sum Query - Mutable
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Notice
1.The array is only modifiable by the update function.
2.You may assume the number of calls to update and sumRange function is distributed evenly.*/
class NumArray {
    int[] sum ;
    public NumArray(int[] nums) {
        sum = new int[nums.length+1];
        sum[0] = 0;
        for (int i=1;i <= nums.length;i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
    }
    
    public void update(int i, int val) {
        int num = sumRange(i,i);
        int add = val - num;
        for(int j = i+1; j < sum.length; j++){
            sum[j] = sum[j] + add;//bug1 add from item self
        }
    }
    
    public int sumRange(int i, int j) {
        return sum[j+1]-sum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
/*]
41. Maximum Subarray
Given an array of integers, find a contiguous subarray which has the largest sum.

Example
Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.

Challenge
Can you do it in time complexity O(n)?

Notice
The subarray should contain at least one number.*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code here
        int len = nums.length;
        
        int sum = 0;int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            sum = Math.max( sum , 0);
        }
        return max;
    }
}
