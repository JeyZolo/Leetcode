/*
22. Generate Parentheses
DescriptionHintsSubmissionsDiscussSolution
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]*/
// method 1:
 public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    
    public void backtrack(List<String> list, String str, int open, int close, int max){
        
        if(str.length() == max*2){
            list.add(str);
            return;
        }
        
        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }
//method 2:
    class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        helper (res, new StringBuilder(), n*2);
        return res;
    }
    
    public void helper (List<String> res, StringBuilder str, int n) {
        if (n == 0) {
            if (isValid(str.toString()))
                res.add(str.toString());
            return;
        }
        String[] add = {"(",")"};
        for (int i = 0; i < 2; i++){
            helper (res, str.append(add[i]), n-1);
            str.delete(str.length() - 1,str.length());
        }
    }
    public boolean isValid(String s) {
        int plus = 0, minus = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (minus > plus) return false;
            
            if (c == ')') minus ++;
            else if (c == '(') plus ++;
        }
        return minus == plus;
    }
}
/*
204. Count Primes
DescriptionHintsSubmissionsDiscussSolution
Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.*/
class Solution {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }
        
        return count;
    }
}
