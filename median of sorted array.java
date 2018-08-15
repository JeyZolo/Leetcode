/*
LintCode-Logo
Home
Algorithms
AI
VIP
Language
avatarjing43
Back
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