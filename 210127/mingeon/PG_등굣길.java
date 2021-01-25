package Hunters_210120_27;

import java.util.LinkedList;
import java.util.Queue;

public class PG_등굣길 {

	public static void main(String[] args) {
		등굣길 s = new 등굣길();
		int m = 4;
		int n = 3;
		int[][] puddles = { { 2, 2 } };
		System.out.println(s.solution(m, n, puddles));
	}

}

class 등굣길 {

	static int m;
	static int n;
	static int[][] dp;

	public int solution(int m, int n, int[][] puddles) {
		int answer = 0;
		dp = new int[n + 1][m + 1];
		this.m = m;
		this.n = n;

		dp[1][1] = 1;
		// 물에 잠긴 곳 입력
		for (int[] pud : puddles) {
			dp[pud[1]][pud[0]] = -1;
		}

		// 탐색
		for (int row = 1; row <= n; row++) {
			for (int col = 1; col <= m; col++) {

				// 잠긴 경우
				if (dp[row][col] == -1) {
					dp[row][col] = 0;
				}
				// 아닌 경우
				else {
					if (row == 1) {
						dp[row][col] += dp[row][col - 1];
					} else {
						dp[row][col] = (dp[row][col - 1] + dp[row - 1][col]) % 1000000007;
					}
				}
			}
		}

		answer = dp[n][m];
		return answer;
	}
}