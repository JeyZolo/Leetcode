/*
297. Serialize and Deserialize Binary Tree
DescriptionHintsSubmissionsDiscussSolution
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

Seen this question in a real interview before? */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    String NULL = "null";
    String comma = ",";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // level by level add the node into string
        StringBuilder res = new StringBuilder();
        if(root == null)
            return NULL;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            //int size = queue.size();
            TreeNode node = queue.poll();
            
            if (node != null)
                res.append(String.valueOf(node.val) + comma);
            else {
                res.append(NULL + comma);
                continue;
            }
            
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals(NULL)) return null;
        System.out.println(data);
        //TreeNode root = null;
        String[] tree = data.split(comma);
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(tree[0]));
        // forget to add queue.offer(root);
        int level = 1;int i = 1;
        
        while ( i < tree.length) {

                TreeNode node = queue.poll();
                System.out.println(node.val);
            
                if(i < tree.length && !tree[i].equals(NULL)) {
                    TreeNode left = new TreeNode(Integer.valueOf(tree[i]));
                    
                    node.left = left;
                    queue.offer(left);
                }
                if(++i < tree.length && !tree[i].equals(NULL)) {
                    TreeNode right = new TreeNode(Integer.valueOf(tree[i]));
                    node.right = right;
                    queue.offer(right);
                }
                ++i;
        
        }
        return root;
        
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
/*
743. Network Delay Time
DescriptionHintsSubmissionsDiscussSolution
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:
N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.*/
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] dist = new int[N + 1];int max = 0;
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        
        for (int i = 0; i < N; i++) {
            for (int[] row : times){
                int w = row[0],v = row[1], t = row[2];
                if(dist[w] != Integer.MAX_VALUE && dist[v] > dist[w] + t) {
                    dist[v] = dist[w] + t;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dist[i]);
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }
}

