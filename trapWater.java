/*
42. Trapping Rain Water
DescriptionHintsSubmissionsDiscussSolution
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6*/

class Solution {
    public int trap(int[] height) {
        int len = height.length;
        int left = 0, right = len - 1;
        if (left >= right) return 0;
        int leftmost = height[left];
        int rightmost = height[right];
        int res = 0;
        
        while (left < right) {
            if (leftmost < rightmost) {
                left ++;
                if (leftmost > height[left]){
                    res += leftmost - height[left];
                }else {
                    leftmost = height[left];
                }
            }else {
                right --;
                
                if(rightmost > height[right]){
                    res += rightmost - height[right];
                }else {
                    rightmost = height[right];
                }
            }
        }
        return res;
    }
}
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

*/
//use DFS to find every substring and also use HashMap to store the result to avoid timeexceed
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s.length() == 0) {
            return true;
        }
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        
        return findWordBreak(s, set, new HashMap<>());
    }
    public boolean findWordBreak(String s, Set<String> wordDict, Map<String,Boolean> map) {

        if(s.length() == 0) {
            return true;
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }
        for (int i = 0; i <= s.length(); i++) {
            String str = s.substring(0, i);
            if (wordDict.contains(str)) {
                boolean check = findWordBreak(s.substring(i), wordDict,map);
                map.put(str, check);
                if(check == true)
                    return true;
            }
        }
        map.put(s, false);// need to add the res to map
        return false;
    }
    
}