package Hunters_210113_20;

/**
 * @packageName : Hunters_210113_20
 * @fileName : PG_43105_정수삼각형.java
 * @author : Mingeon
 * @date : 2021. 1. 13.
 * @language : JAVA
 * @classification : DP
 * @time_limit :
 * @required_time : 11:30 ~ 12:26
 * @submissions : 1
 * @description
 */

public class PG_43105_정수삼각형 {

	public static void main(String[] args) {
		int[][] triangle = { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };
		int layer = triangle.length;
		int answer = 0;

		// copy triangle
		int[][] sum = new int[layer][];
		for (int i = 0; i < layer; i++) {
			sum[i] = new int[i + 1];
		}
		for (int i = 0; i < layer; i++) {
			System.arraycopy(triangle[i], 0, sum[i], 0, triangle[i].length);
		}

		// 탐색
		for (int row = 0; row < layer - 1; row++) { // 행

			for (int col = 0; col < triangle[row].length; col++) { // 열

				int nextRow = row + 1;
				int nextCol1 = col;
				int nextCol2 = col + 1;

				// 밑줄 왼쪽 범위 검사
				if (isIn(nextRow, nextCol1, layer, triangle[nextRow].length)) {

					// 왼쪽의 값
					int left = sum[row][col] + triangle[nextRow][nextCol1];

					// 기존의 덧셈보다 큰 경우 갱신
					if (sum[nextRow][nextCol1] < left) {
						sum[nextRow][nextCol1] = left;
					}
				}

				// 밑줄 오른쪽 범위 검사
				if (isIn(nextRow, nextCol2, layer, triangle[nextRow].length)) {
					int right = sum[row][col] + triangle[nextRow][nextCol2];

					// 기존의 덧셈보다 큰 경우 갱신
					if (sum[nextRow][nextCol2] < right) {
						sum[nextRow][nextCol2] = right;
					}
				}

			}
		} // End of search

		// 최댓값 탐색
		for (int i : sum[layer - 1]) {
			answer = Math.max(answer, i);
		}

		System.out.println(answer);

	}

	// 범위 검사
	private static boolean isIn(int row, int col, int height, int width) {
		if (row < 0 || row >= height || col < 0 || col >= width) {
			return false;
		}
		return true;
	}

}
