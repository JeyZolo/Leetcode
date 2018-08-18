/*31. Next Permutation
DescriptionHintsSubmissionsDiscussSolution
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1*/
class Solution {
    /*
    example: 1/3.2: a = 1, b = 2, swap a and b => 2/1/3, revese from 2/13=>2/13 get 213
    */
    public void nextPermutation(int[] nums) {
        int a = -1, b = 0;
        int len = nums.length;
        //find first ele in nums from end to head, which nums[i] < nums[i+1]
        
        for (int i = len-2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                a =  i;
                break;
            }
        }
        if (a == -1){// check whether it is from big to small order
            reverse(nums, 0 , len-1);
            return;
        }
        for (int i = len - 1; i > a; i--){ //find first b which is larger tham a from end to a+1
            if(nums[i] > nums[a]){
                b = i;
                break;
            }
        }
        swap(nums, a, b);
        reverse(nums, a+1, len-1);//reverse from a+1
        return;
    }
    public void swap (int[] nums, int a , int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
    public void reverse(int[] nums, int a, int b){
        
        while (a < b){
            swap(nums, a, b);
            a++;
            b--;
        }
    }
}