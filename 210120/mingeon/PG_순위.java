package Hunters_210113_20;

public class PG_순위 {

	public static void main(String[] args) {
		int[][] results = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
		int n = 5;
		순위 s = new 순위();
		System.out.println(s.solution(n, results));
	}

}

class 순위 {

	public int solution(int n, int[][] results) {
		int answer = 0;
		int[][] rel = new int[n + 1][n + 1];
		int maxVal = 1000000;

		// 초기값
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				rel[i][j] = maxVal;
			}
		}

		// input
		for (int[] r : results) {
			rel[r[0]][r[1]] = 1;
		}

		// 중간 노드
		for (int k = 1; k < n + 1; k++) {
			// 출발 노드
			for (int i = 1; i < n + 1; i++) {
				// 도착 노드
				for (int j = 1; j < n + 1; j++) {
					// 거치는 경로의 가중치가 더 작은 경우
					if (rel[i][j] > rel[i][k] + rel[k][j]) {
						rel[i][j] = rel[i][k] + rel[k][j];
					}
				}
			}
		}

//		출력 확인
//		for (int i = 1; i < n + 1; i++) {
//			for (int j = 1; j < n + 1; j++) {
//				System.out.printf("%7d", rel[i][j]);
//				System.out.print(" ");
//			}
//			System.out.println();
//		}

		for (int i = 1; i < n + 1; i++) {
			boolean flag = true;
			for (int j = 1; j < n + 1; j++) {

				// 본인 제외
				if (i == j) {
					continue;
				}

				// 방문한 적이 없는 경우
				if (rel[i][j] == maxVal && rel[j][i] == maxVal) {
					flag = false;
					break;
				}
			}

			// 해당 노드에 다른 모든 노드들의 출입이 있는 경우
			if (flag) {
				answer++;
			}
		}

		return answer;
	}
}