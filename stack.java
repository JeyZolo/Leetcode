/*×636. Exclusive Time of Functions
Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

×/*/
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        int preTime = 0;
        
        for (String log :logs) {
            String[] logArr = log.split(":");
            
            int id = Integer.valueOf(logArr[0]);
            String op = logArr[1];
            int time = Integer.valueOf(logArr[2]);
            if(!stack.isEmpty())
                res[stack.peek()] += time - preTime;
            preTime = time;
            if (op.equals("start")){
                stack.push(id);
            }else {
                preTime++;
                res[stack.pop()]++;
                
            }
        }
        return res;
    }
}
/*
166. Fraction to Recurring Decimal
DescriptionHintsSubmissionsDiscussSolution
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"
*/
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder res = new StringBuilder();
        
        if (numerator == 0)
            return "0";
        if ((numerator > 0) ^(denominator > 0)) 
            res.append("-");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        
        res.append(num/den);
        num = num%den;
        
        if (num == 0){
            return res.toString();
        }
        res.append(".");
        
        //handle with num behind .
        HashMap<Long, Integer> map = new HashMap<>();
        //num = den;
        map.put(num, res.length());//give the index to insert (
        while (num != 0) {
            num = num *10;
            res.append(num/den);
            num %= den;
            if(!map.containsKey(num)){
                map.put(num, res.length());
            }else {
                res.insert(map.get(num),  "(");
                res.append(")");
                break;
            }
        }
        return res.toString();
    }
}
/*
267. Palindrome Permutation II
DescriptionHintsSubmissionsDiscussSolution
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []
*/
class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0 )
            return res;
        //give all possible result, use dfs
        char[] arr = s.toCharArray();
        //give pemutation for half character
        int[] c = new int[256];int count = 0;
        for (char cc : arr){
            if(c[cc] == 0) count++;
            c[cc]++;
        }
        StringBuilder str = new StringBuilder();
        if (count == 1) {
            res.add(s);
            return res;
        }
        Arrays.sort(arr);
        String mid = "";int mark = 0;
        for (int i = 0; i < arr.length;){
            char cc = arr[i];
            int k = c[cc];
            i = i + k;
            if (mark == 0 && k%2 != 0){ mark = 1;mid += cc;}
            else if (k%2 != 0) return res;
            k /= 2;
            
            while(k-- > 0) {
                str.append(cc);
            }
        }
        boolean[] visited = new boolean[arr.length];
        dfs (res,new StringBuilder(),str,visited, mid);
        return res;
    }
    public void dfs(List<String> res,StringBuilder substr, StringBuilder str, boolean[] visited,String mid) {
        if (substr.length() == str.length()){
			
			//to reverse twice to get orignal substr for next part
            res.add(substr.reverse() + mid + substr.reverse());
            return;
        }
        for (int i = 0; i <str.length(); i++) {
            if (i > 0 && visited[i-1] &&str.charAt(i) == str.charAt(i-1)) continue;
            if(visited[i]) continue;
            substr.append(str.charAt(i));
            visited[i] = true;
            dfs(res, substr, str, visited,mid);
            substr.deleteCharAt(substr.length()-1);
            visited[i] = false;
            
        }
    }
}/*
359. Logger Rate Limiter
DescriptionHintsSubmissionsDiscussSolution
Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
*/
class Logger {

    /** Initialize your data structure here. */
    HashMap<String, Integer> map;
    public Logger() {
        map =  new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message)){
            map.put(message, timestamp);
            return true;
        }else {
            int time = map.get(message);
            if(timestamp - time < 10){
                return false;
            }
            map.put(message,timestamp);
            return true;
        }
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
 /*189. Rotate Array
DescriptionHintsSubmissionsDiscussSolution
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
*/class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
}

    public void reverse(int[] nums, int start, int end) {
    while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
    

    }
}
}
/*
266. Palindrome Permutation
DescriptionHintsSubmissionsDiscussSolution
Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true
Example 3:

Input: "carerac"
Output: true
*/
class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] arr = new int[256];
        
        for (char c : s.toCharArray()) {
            arr[c]++;
        }
        int mark = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] %2 == 0) continue;
            if(mark == 1) {
                return false;
            }else {
                mark = 1;
            }
        }
        return true;
    }
}