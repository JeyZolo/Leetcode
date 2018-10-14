/*139. Word Break
DescriptionHintsSubmissionsDiscussSolution
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
Seen this question in a real interview before?  
Difficulty:Medium
Total Accepted:254.7K
Total Submissions:777.4K
Contributor:LeetCode
Companies   

Related Topics 

Similar Questions 
Word Break II
Java	
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0)
            return false;
        Set<String> set = new HashSet<>();
        for (String w: wordDict) {
            set.add(w);
        }
        HashMap<String,Boolean> map = new HashMap<>();
        return findWordDict(s, set, map);
        
    }
    // find the substring of s and if it's in the set, search for next using DFS, store the res into map in case memory exceed

    public boolean findWordDict(String str, Set<String> set, HashMap<String,Boolean> map) {
        if (str.length() == 0) {
            return true;
        }
        if(map.containsKey(str))
            return map.get(str);
        
        for (int i = 1; i <= str.length(); i++) {
            //for (int j = i + 1; j <= str.length(); j++) {
                String substr = str.substring(0, i);
                    
                    boolean res = findWordDict(str.substring(i), set,map);
                    if(res == true)
                        return true;
                    map.put(substr, res);
                }
            //}
        }
        map.put(str, false);
        return false;
    }
}

/*
140. Word Break II
DescriptionHintsSubmissionsDiscussSolution
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:*/
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String w: wordDict) {
            set.add(w);
        }

        return DFS(s, set, new HashMap<String, LinkedList<String>>());
    }       

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s)) 
            return map.get(s);

        LinkedList<String>res = new LinkedList<String>();     
        if (s.length() == 0) {
            res.add("");
            return res;
        }               
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist) 
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
            }
        }       
        map.put(s, res);
        return res;
    }
}


