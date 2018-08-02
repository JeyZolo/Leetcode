/*Description
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Have you met this question in a real interview?  
Example
MovingAverage m = new MovingAverage(3);
m.next(1) = 1 // return 1.00000
m.next(10) = (1 + 10) / 2 // return 5.50000
m.next(3) = (1 + 10 + 3) / 3 // return 4.66667
m.next(5) = (10 + 3 + 5) / 3 // return 6.00000
*/
public class MovingAverage {
    /*
    * @param size: An integer
    */
    double sum;
    int count;
    double[] nums;
    public MovingAverage(int size) {//size is the silding window size. not the length of nums
        // do intialization if necessary
        count = 0;
        sum = 0 ;
        nums = new double[size];
    }

    /*
     * @param val: An integer
     * @return:  
     */
    public double next(int val) {
        // write your code here
        int len = nums.length;
        if(count > len-1){
            sum -= nums[count%len];
        }
        nums[count%nums.length] = val;
        sum += val;
        count++;
        return count < len ? (double)sum/count : (double)sum/len;
    }
}
/*

494. Implement Stack by Two Queues
Implement a stack by two queues. The queue is first in first out (FIFO). That means you can not directly pop the last element in a queue.

Example
push(1)
pop()
push(2)
isEmpty() // return false
top() // return 2
pop()
isEmpty() // return true
*/


public class Stack {
    /*
     * @param x: An integer
     * @return: nothing
     */
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();
    
    public void push(int x) {
        // write your code here
        queue1.offer(x);
    }

    /*
     * @return: nothing
     */
    public void pop() {
        // write your code here
        Move1to2();
        queue1.poll();
        SwapQueue();

    }

    /*
     * @return: An integer
     */
    public int top() {
        
        Move1to2();//not use param cuz it cannot be transfer by value
       
        int x = queue1.poll();
        queue2.offer(x);
        SwapQueue();
        return x;
        
    }

    /*
     * @return: True if the stack is empty
     */
    public void Move1to2() {
        while (queue1.size() != 1){
            int x = queue1.poll();
            queue2.offer(x);
        }
    }
    public void SwapQueue() {
        Queue<Integer> q = queue1;
        queue1 = queue2;
        queue2 = q1;// wrong bugs!!!
}
    public boolean isEmpty() {
        // write your code here
        return queue1.isEmpty();
    }
}

/*
209. First Unique Character in a String
Find the first unique character in a given string. You can assume that there is at least one unique character in the string.

Example
For "abaccdeff", return 'b'.

*/
public class Solution {
    /**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        // Write your code here
        //1. hashmap to store the count of very char and loop str again, the first 1 count is the res
        //2. use a queue
        int[] map = new int[256];
        
        for (int i = 0; i < str.length(); i++) {
            ++map[str.charAt(i)];
            
        }
        char res = '\0'; // intialize use '\0' cannot leave it as alone
        for (int i = 0; i < str.length(); i++) {
            if (map[str.charAt(i)] == 1)
                {res = str.charAt(i); break;}
        }        
        return res;
    }
}


