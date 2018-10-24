/*

  
401. Kth Smallest Number in Sorted Matrix
Find the kth smallest number in at row and column sorted matrix.

Example
Given k = 4 and a matrix:

[
  [1 ,5 ,7],
  [3 ,7 ,8],
  [4 ,8 ,9],
]
return 5

Challenge
Solve it in O(k log n) time where n is the bigger one between row size and column size.


[[1,5,7],[3,7,8],[4,8,9]]
4

*/
public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
     */
     class Point implements Comparable<Point>{
         int x, y;
         int val;
         public Point(int x, int y, int val) {
             this.x = x;
             this.y = y;
             this.val = val;
         }
         public int compareTo(Point that) {
             return this.val - that.val;
         }
     }
    public int kthSmallest(int[][] matrix, int k) {
        // write your code here
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[m][n];
        pq.offer(new Point(0,0,matrix[0][0]));
        int[] dx = {0,1};
        int[] dy = {1,0};
        
        //i record tha number of polls
        for (int i = 0; i < k-1; i++) {
            
            //poll出k-1个数即可，第k个数就是要求的数，所以是i（0，k-2）poll出k-1次
            Point p = pq.poll();

            for (int j = 0; j < 2; j++){
                int x = p.x + dx[j];
                int y = p.y + dy[j];
                if((x >= m || y >= n ) || visited[x][y]) continue;
                pq.offer(new Point(x, y, matrix[x][y]));
                visited[x][y] = true;
              }
            
            
            
        }
        return pq.peek().val;
        
    }
}