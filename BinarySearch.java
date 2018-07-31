/*=====Binary Search
We basically ignore half of the elements just after one comparison.

Compare x with the middle element.
If x matches with middle element, we return the mid index.
Else If x is greater than the mid element, then x can only lie in right half subarray after the mid element. So we recur for right half.
Else (x is smaller) recur for the left half.


*/
import java.io.*;
import java.util.HashMap;


// Java implementation of recursive Binary Search
class BinarySearch
{
	// Returns index of x if it is present in arr[l..
	// r], else return -1
	int binarySearch(int arr[], int l, int r, int x)
	{
		if (r>=l)
		{
			int mid = l + (r - l)/2;

			// If the element is present at the 
			// middle itself
			if (arr[mid] == x)
			return mid;

			// If element is smaller than mid, then 
			// it can only be present in left subarray
			if (arr[mid] > x)
			return binarySearch(arr, l, mid-1, x);

			// Else the element can only be present
			// in right subarray
			return binarySearch(arr, mid+1, r, x);
		}

		// We reach here when element is not present
		// in array
		return -1;
	}
	//==============454: 4 sum-2=========================
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
       
        HashMap <Integer,Integer> map =new HashMap<>();
        
        for(int i=0;i<A.length;i++){
            for(int j=0;j<B.length;j++){
                int sum = A[i]+B[j];
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        int res=0;
        for(int i=0;i<C.length;i++){
            for(int j=0;j<D.length;j++){
                res+=map.getOrDefault(-1*(C[i]+D[j]),0);
            }
        }
        return res;
    }

//=========================3/16/2018  leetcode:152====================
    /* if mid<low it means that the min in the first half ,so high=mid, 
    else if mid>=low the min in the second half , so low = mid+1
    */
    public int findMin(int[] nums) {
        int len = nums.length;
        if(len==1) return nums[0];
        if(len==0) return 0;
        int low=0,high=len-1,mid=0;
        //if(nums[low]<nums[high]) return nums[low];
       
        while(low<high){
            mid=low+(high-low)/2;
            System.out.println(high);
            if(nums[low]<nums[high]) return nums[low];
            else if(nums[mid]<nums[low])
               high=mid;
            else if(nums[mid]>=nums[low]) low=mid+1;
            
            
        }
        return nums[low];
        
        
    }
	// Driver method to test above
	public static void main(String args[])
	{
		BinarySearch ob = new BinarySearch();
		int arr[] = {2,3,4,10,40};
		int n = arr.length;
		int x = 10;
		int result = ob.binarySearch(arr,0,n-1,x);
		if (result == -1)
			System.out.println("Element not present");
		else
			System.out.println("Element found at index " + 
												result);
		int[] A={1,2},B={2,-1},C={0,1},D={-1,1};
		int res = fourSumCount(A,B,C,D);
		System.out.println(res);

	}
}
