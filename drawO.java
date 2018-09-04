import java.io.*;
import java.util.*;

class Solution {

public int changeStr(String s) {
	int res = 0;
	for (int i = 0; i < s.length(); i++) {
		if (i > 0 && s.charAt(i) == s.charAt(i-1)) {
			s = s.substring(0,i) + '0' + s.substring(i+1);
			res++;
		}
	}
	return res;
}
/*
是在一个不重复的integer list中找到所有size为m的组合 然后每个组合中挑出所有pair 比较绝对值
然后在每个组合的绝对值中 找最小差值
然后放在所有组合中 比较一个最大值 return这个最大值
比如 {1,2,3,5,7} m = 4
==> {1,2,3,7} {1,2,3,5} {1,2,5,7} {1,3,5,7} {2,3,5,7}
在每一对中找出 最小的diff. 留学申请论坛-一亩三分地
分别是 1  1 1 2 1 所以最后return 2*/

public int findMAXDif (List<Integer> list, int m) {
	return 0;
}
/*
1. Compress String*/
 public int compress(char[] chars) {
        
        int i = 0,  count = 0,  index = 0;
        // use index to store valid info
        while (i < chars.length) {
            char c = chars[i];
            chars[index++] = c;
            count = 1;
            while (++i < chars.length && c == chars[i]){
                count++;
            }
            if (count <= 1)  continue;
            for(char cc : Integer.toString(count).toCharArray()) 
                    chars[index++] = cc;
           
        }
        return index;
    }
    /*

2. Min difference of list of strings,
给两个list of string， 返回list of integer. 
每个index上的对应string check一下是不是anagram， 如果是 返回0， 否则返回改变几个character能组成anagra*/

public List<Integer> findAnagram (List<String> l1, List<String> l2) {
	//check anagram  if not ,find diff chars between str and return diff nums
	List<Integer> res = new ArrayList<>();
	int len = 0, len1 = l1.size(), len2 = l2.size();
	if (l1.size() != l2.size()) {
		len = Math.min(len1, len2);
	}
	int i = 0,count = 0;
	for (; i < len; i++) {
		count = isAnagram(l1.get(i), l2.get(i));
		res.add(count);
				//System.out.println(count);

	}
	if (len1 < len2) {
		while (i < len2){
			res.add(l2.get(i++).length());
		}
	}else {
		while (i < len1) {
			res.add(l1.get(i++).length());
		}
	}

	return res;

}
    public int isAnagram(String s, String t) {
        int[] alphabet = new int[26];int count = 0;
        
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;

         for (int i : alphabet) if (i != 0) count++;


        return count/2;
    }
/*
1. balanced or not 给两个list，一个是String, 只含'<','>'两种字符，一个是每个String对应的max replace count
balanced要求是<>组成对且<在>之前出现，>可以改成<>,算是一次replace, 最多能改count次，能改成balanced就是1，不能就是0. 
返回值是List,放每个String能否改成balanced. 

*/
public static boolean minReplace(String str, int k) {
	int count = 0;
	for (int i = 0; i < str.length(); i++) {
		if (str.charAt(i) == '(') {
			count++;
		} else {
			count--;
		}
		if (count < 0) {
			k--;
			count = 0;
		}
	}
	return count == 0 && k >= 0;
}
/*
1. cardinallity sort
* Sort an array by number of 1s in the binary of integer, if number of 1s are same,
* the smaller one should be placed first.
*
* Example.
*
* input [1,2,3,4,5]
* return [1,2,4,3,5]


2. 
/*
* Given a positive integer target, count all the combinations of contiguous positive
* integers that sum up to the target.
*
* For Example,
* target = 15
* return 3
*
* since
* 15 = 4 + 5 + 6. from: 1point3acres 
* 15 = 1 + 2 + 3 + 4 + 5
* 15 = 7 + 8
* */



public static void main (String[] args) {
	Solution ob = new Solution();
	int num = ob.changeStr("abbbbb");
	//System.out.println(num);

	List<String> s = new ArrayList<>();
	s.add("abcd");
	s.add("dfg");
	List<String> t = new ArrayList<>();
	t.add("dcba");
	t.add("fbg");
	t.add("cs");

	List<Integer> res = new ArrayList<>();
	res = ob.findAnagram(s,t);
	for (Integer n : res){
		System.out.println(n);
	}
	boolean ex = ob.minReplace("((()()()))",5);
	System.out.println(ex);

}

}
