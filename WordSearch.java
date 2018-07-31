import java.io.*
import java.util.*

class WordSearch{
	int[] dx = {0,0,1,-1};
	int[] dy = {1,-1,0,0};
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
}
