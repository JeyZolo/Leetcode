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
/*
49. Group Anagrams
DescriptionHintsSubmissionsDiscussSolution
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<List<String>>(map.values()); 
    }
}

/*
347. Top K Frequent Elements
DescriptionHintsSubmissionsDiscussSolution
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.*/
//==1== Useing Heap

class Solution {
    class Interval {
    int key;
    int freq;
    public Interval (int key,int freq) {
        this.key = key;
        this.freq = freq;
    }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            }
            map.put(nums[i], map.get(nums[i])+1);
        }
        PriorityQueue<Interval> pq = new PriorityQueue<>(map.size(), new Comparator<Interval>(){
            @Override 
            public int compare(Interval o1, Interval o2) {
                return o2.freq - o1.freq;
            }
        });
        for (Map.Entry<Integer,Integer> pair : map.entrySet()) {
            Interval ex = new Interval (pair.getKey(),pair.getValue());
            pq.offer(ex);
        }
        while (!pq.isEmpty() && k-- > 0) {
            res.add(pq.poll().key);
        }
        return res;

        
    }
}
// bucket sort
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        //use bucket to store num with the same freq, and then add it to res list
        List<Integer>[] bucket = new List[nums.length + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], 0);// line13 add freq
            }
            map.put(nums[i], map.get(nums[i])+1);
        }
        for (Map.Entry<Integer,Integer> pair : map.entrySet()) {
            int freq = pair.getValue();
            
            if(bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(pair.getKey());
        }
        for (int i = nums.length; i > 0 && k > 0; i--) {
            if (bucket[i] == null) continue;
            for (Integer num : bucket[i]){
                res.add(num);
                k--;
                if (k <= 0) return res;
            }
        }
        return res;
        
    }
}
/*
151. Reverse Words in a String
DescriptionHintsSubmissionsDiscussSolution
Given an input string, reverse the string word by word.

Example:  

Input: "the sky is blue",
Output: "blue is sky the".
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
Follow up: For C programmers, try to solve it in-place in O(1) space.*/
public class Solution {
    public String reverseWords(String s) {
        int len = s.length();
        String[] arr = s.trim().split("\\s+");
        if (s == null || s.length() == 0) return s;
        
        StringBuilder res = new StringBuilder();
        
        for (int i = arr.length - 1; i >= 0; i--) {
            res.append(arr[i] + " ");
           //System.out.println(res);
        }
        return res.substring(0, res.length() -1);
        
    }
}

