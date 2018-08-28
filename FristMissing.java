/*
41. First Missing Positive
DescriptionHintsSubmissionsDiscussSolution
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 18?*/
class Solution {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        //Arrays.sort(nums);
        int i = 0;
        while(i < len) {
            if (nums[i] == i+1|| nums[i] <=0 || nums[i] > len) i++;
            else if (nums[nums[i]-1] != nums[i]) swap(nums, i, nums[i]-1);
            else i++;
        }
        i = 0;
        
        while (i < len && nums[i] == i+1){
            i++;
        }
        return i+1;
        
        
    }
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
/*
54. Spiral Matrix
DescriptionHintsSubmissionsDiscussSolution
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]%*/
// use four pointer and interate every elemnet from left to right, from up to down then right to let, then down to up
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 ) return res;
        int m = matrix.length, n = matrix[0].length;
        
        
        int left = 0, right = n -1, up = 0, down = m -1;
        while(left < right && up < down){

            for (int i = left; i < right; i++){
                res.add(matrix[left][i]);
            }

            for (int i = up; i < down; i++) {
                res.add(matrix[i][right]);
            }

            for (int i = right; i > left; i--){
                res.add(matrix[down][i]);
            }

            for (int i = down; i > up; i--) {
                res.add(matrix[i][left]);
            }
            
            left ++;
            right--;
            up++;
            down--;
        }
        if (left == right) {
            for (int i = up; i <= down; i++){
                res.add(matrix[i][left]);
            }
        }
        else if (up == down) {
            for (int i = left; i <= right; i++){
                res.add(matrix[up][i]);
            }
        }
        return res;
    }
    /*
    389. Find the Difference
DescriptionHintsSubmissionsDiscussSolution
Given two strings s and t which consist of only lowercase letters.

String t is generated by random shuffling string s and then add one more letter at a random position.

Find the letter that was added in t.

Example:

Input:
s = "abcd"
t = "abcde"

Output:
e

Explanation:
'e' is the letter that was added.*/
class Solution {
    public char findTheDifference(String s, String t) {
        int[] arr = new int[26];
        char res = 'a';
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i)-'a']--;
        }
        for(int i = 0; i < 26; i++) {
            if (arr[i] < 0){ 
                 res = (char)('a' + i);
                break;
            }
        }
        return res;
    }
}
}