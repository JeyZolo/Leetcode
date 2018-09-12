/*
438. Find All Anagrams in a String
DescriptionHintsSubmissionsDiscussSolution
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".*/
// method 1
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int lens = s.length(), lenp = p.length();
        
        for (int i = 0; i <= lens - lenp; i++) {
            String ss = s.substring(i,i+lenp);
            if(isAnagrams(ss,p)){
                res.add(i);
            }
        }
        return res;
    }
    public boolean isAnagrams(String s, String p) {
        int len = s.length();
        int[] alb = new int[256];
        
        for (int i = 0; i < len; i++) {
            alb[s.charAt(i)]++;
            alb[p.charAt(i)]--;
        }
        for (int num : alb) {
            if(num != 0)
                return false;
        }
        return true;
    }
}
//method 2 sliding windows

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int lens = s.length(), lenp = p.length();
        int[] alb = new int[26];
        for (int i = 0; i <lenp ; i++) {
            alb[p.charAt(i)-'a']++;
        }
        
        int start = 0, end = 0;int match = 0;
        //sliding windows
        
        while(end < lens) {
            //if cur point is exist in string p, it's match with p
            if (alb[s.charAt(end) - 'a'] >= 1)
                match++;
            //it's matched or not matched, mark it as visited
            alb[s.charAt(end) - 'a']--;
            end++;
            if (match == lenp) {
                res.add(start);
                //find next string
            }
            //when visit lenp char in s
            if (end - start == lenp) {
                // if start char in p, need to decrease match
                if (alb[s.charAt(start) - 'a'] >= 0) {
                    match --;
                }
                alb[s.charAt(start) - 'a']++;
                start++;
                
            }
            
        }
        
        
        return res;
    }
}
/*342. Power of Four
DescriptionHintsSubmissionsDiscussSolution
Pick One
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
Example 1:
Input: 16
Output: true


Example 2:
Input: 5
Output: false
Follow up: Could you solve it without loops/recursion?*/

class Solution {
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }
}
/*

New Playground
jing76
186. Reverse Words in a String II
DescriptionHintsSubmissionsDiscussSolution
Given an input string , reverse the string word by word. 

Example:

Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
Note: 

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?*/
class Solution {
    public void reverseWords(char[] str) {
        int len = str.length;
        
        //reverse the array then, reverse every word
        reverseWord(str, 0, len-1);
        //reverse every word
        int i = 0;
        while (i < len) {
            int j = i;
            while (j < len && str[j] != ' '){
                j++;
            }
            reverseWord(str, i, j - 1);
            i = j+1;
        }
        
    }
    public void reverseWord(char [] str, int low , int high) {
        //int low = 0, high = len - 1;
        while (low < high) {
            char t = str[low];
            str[low] = str[high];
            str[high] = t;
            low++;
            high--;
        }
    }
}
/*
557. Reverse Words in a String III
DescriptionHintsSubmissionsDiscussSolution
Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.*/class Solution {
    public String reverseWords(String s) {
        //to char array
        
        char[] str = s.toCharArray();
        int i = 0, j = 0;
        while (i < s.length()) {
             j = i;
            while (j < s.length() && str[j] != ' ') {
                j++;
            }
            reverseStr(str, i, j -1);
            i = j+1;
        }
        return String.valueOf(str);
    }
    public void reverseStr(char[] s, int i, int j) {
        
        while (i < j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
            i ++;
            j --;
        }
        
    }
}


