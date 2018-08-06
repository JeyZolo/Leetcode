import java.io.*
import java.util.*

class WordSearch{
	int[] dx = {0,0,1,-1};
	int[] dy = {1,-1,0,0};

	//------------word search 1----------------------
	public boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0 ||board[0].length == 0) return false;
		int m = board.length, n = board[0].length;
		boolean[][] visited = new booleanpm[n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				visited[i][j] = true;
				boolean match = dfs (board, word, i, j, visited);
				if (match == true)
					return true;
				visited[i][j] = false;
			}
		}
		return false;

	}

	public boolean dfs(char[][] board, String word, int a, int b, boolean[][] visited) {
		if (word.length() == 0)
			return true;
		if (board[i][j] != word.charAt(0))
			return false;


		for (int i = 0; i < 4; i++) {
			int x = dx[i] + a;
			int y = dy[i] + b;

			if (!valid(x,y,board.length, board[0].length) || visited[x][y]) continue;
			visited[x][y] = true;
			boolean match = dfs(board, word.substring(1), x,y, visited);
			if(match == true)
				return true;

			visited[x][y] = false;
		}
		//return false;//if the word lenth == 1, it will get a wrong result
		return word.lenght() == 1? true : false;
	}

	public boolean valid(int x,int y, int m, int n) {
		return x >= 0 && y >= 0 && x < m && y < n;
	}

	//------------word search 2----------------------
	// method 1:
 public List<String> wordSearchII(char[][] board, List<String> words) {
        // write your code here
        if (board == null || board.length == 0) {
            return new ArrayList<>();
        }
        if (board[0] == null || board[0].length == 0) {
            return new ArrayList<>();
        }
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        Map<String,Boolean> prefix = getAllPrefix(words);
        Set<String> res = new HashSet<>(); 
        for (int i = 0; i < board.length; i ++) {
            for ( int j = 0; j < board[0].length; j++) {
                //start from i, j 
                visited[i][j] = true;
                //bug 1 start from i an j, get string value of i,j
                helper (board, String.valueOf(board[i][j]), prefix,res, i, j, visited);
                visited[i][j] = false;
            }
        } 
        // bug 2: use set to list :
        return new ArrayList<String>(res);
    }
    //dfs all the result from board;
    public void helper(char[][] board, String word, Map<String,Boolean> prefix, Set<String> res, int x, int y, boolean[][] visited) {
        //if prefix not contains word, return; bug 3: most important: use words mistakely;
        if(!prefix.containsKey(word))
            return;
        
        if(prefix.get(word)) {
            res.add(word);
        }
        int n = board.length, m = board[0].length;
        for (int i = 0; i < 4; i++) {
            int xx = x +dx[i];
            int yy = y + dy[i];
            if (!isValidPostion(xx,yy,n,m) || visited[xx][yy]) continue;
                
            visited[xx][yy] = true;
            helper(board, word + board[xx][yy], prefix, res, xx,yy,visited);
            visited[xx][yy] = false;
                
            
        }
    }
    
    public boolean isValidPostion(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
    // find all prefix of dict: if borad get a word is not in prefix, return ,it saves the time space;
    public Map<String,Boolean>  getAllPrefix(List<String> words) {
        Map<String,Boolean> prefix = new HashMap<>();
        
        for (int i = 0; i < words.size(); i++) {
            for(int j = 1; j <= words.get(i).length()-1; j ++) {
                String substr = words.get(i).substring(0,j);
                //System.out.println(substr);
                if(!prefix.containsKey(substr))//))// should add this incase that the subtr appear is true, overwrite the res
                    prefix.put(substr,false);
            }
            prefix.put(words.get(i),true);
        }
        return prefix;
    }
//----------------- mehod 2:
    public List<String> findWords(char[][] board, String[] words) {
    List<String> res = new ArrayList<>();
    TrieNode root = buildTrie(words);
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            dfs (board, i, j, root, res);
        }
    }
    return res;
}

public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
    char c = board[i][j];
    if (c == '#' || p.next[c - 'a'] == null) return;
    p = p.next[c - 'a'];
    if (p.word != null) {   // found one
        res.add(p.word);
        p.word = null;     // de-duplicate
    }

    board[i][j] = '#';
    if (i > 0) dfs(board, i - 1, j ,p, res); 
    if (j > 0) dfs(board, i, j - 1, p, res);
    if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
    if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
    board[i][j] = c;
}
//------------------trieNode
public TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();
    for (String w : words) {
        TrieNode p = root;
        for (char c : w.toCharArray()) {
            int i = c - 'a';
            if (p.next[i] == null) p.next[i] = new TrieNode();
            p = p.next[i];
       }
       p.word = w;
    }
    return root;
}

class TrieNode {
    TrieNode[] next = new TrieNode[26];
    String word;
}

}




