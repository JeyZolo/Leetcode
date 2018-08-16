/*

65. Median of two Sorted Arrays
Description
There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.

Have you met this question in a real interview?  
Example
Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.

Given A=[1,2,3] and B=[4,5], the median is 3.



  
65. Median of two Sorted Arrays
There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.

Example
Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.

Given A=[1,2,3] and B=[4,5], the median is 3.

Challenge
The overall run time complexity should be O(log (m+n)).


[1,2,3,4,5,6]
[2,3,4,5]

Shortcut: Command + Enter*/
public class Solution {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        if (A.length < B.length) return findMedianSortedArrays(B,A);
        int lena = A.length, lenb = B.length;
        double l1=0,l2=0,r1=0,r2=0;
        int lo = 0, hi = lenb;
        
        while (lo <= hi) {
            int midb = (lo + hi) /2;
            int mida = (lena + lenb + 1)/2 - midb;
            
            l1 = (mida == 0) ? Integer.MIN_VALUE : A[mida-1];
            l2 = (midb == 0) ? Integer.MIN_VALUE : B[midb-1];
            r1 = (mida == lena) ? Integer.MAX_VALUE : A[mida];
            r2 = (midb == lenb) ? Integer.MAX_VALUE : B[midb];
            
            if (l1 > r2){
                lo = midb +1;
            }else if (l2 > r1) {
                hi = midb - 1;
            }else {
                if((lena+lenb)%2 == 0) return (Math.max(l1,l2) + Math.min(r1,r2))/2;//
                return Math.max(l1,l2);
            }
        }
        return -1;
    }
}
/*
63. Unique Paths II
DescriptionHintsSubmissionsDiscussSolution
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?



An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
*/
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        /*
        for the boarder, it will be 1, cuz it can only move down or right 边上的只能从上面下来，或者左边朝右过来，所以只有一种方案，中间的，等于上面和左边方案之和
        */
       if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] paths = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[i][0] != 1) {
                paths[i][0] = 1;
            } else {
                break;
            }
        }
        
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[0][i] != 1) {
                paths[0][i] = 1; 
            } else {
                break;
            }
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] != 1) {
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
                } else {
                    paths[i][j] = 0;
                }
            }
        }
        
        return paths[n - 1][m - 1];
    }
}
/*
111. Climbing Stairs
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example
Given an example n=3 , 1+1+1=2+1=1+2=3

return 3*/
public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        // write your code here
        int[] dp = new int[2];
        if (n <= 2) return n;
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i%2] = dp[(i-1)%2] + dp[(i-2)%2];
        }
        return dp[(n-1)%2];
    }
}
/*
120. Triangle
DescriptionHintsSubmissionsDiscussSolution
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
*/
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) return -1;
        if(triangle.size() == 1) return triangle.get(0).get(0);
        
        int MAX = Integer.MAX_VALUE;int min = MAX;
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                
                int a = (i > 0 && j < triangle.get(i-1).size()) ? triangle.get(i-1).get(j) : MAX;
                //System.out.println(a);
                int b = (i>0 && j > 0 )? triangle.get(i-1).get(j-1) : MAX;
                int num = triangle.get(i).get(j);
                triangle.get(i).set(j, num + Math.min(a,b) );
                if(i == triangle.size() - 1)
                    min = Math.min(triangle.get(i).get(j), min);
            }
        }
        return min;
    }
}