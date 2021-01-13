package BaekJoon_210107_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @packageName : BaekJoon_210107_13
 * @fileName : BJ_16985_Maaaaaaaaaze.java
 * @author : Mingeon
 * @date : 2021. 1. 13.
 * @language : JAVA
 * @classification : bfs
 * @time_limit : 2sec
 * @required_time : 04:00 ~ 13:00
 * @submissions : 1
 * @description
 */

public class BJ_16985_Maaaaaaaaaze {

	static int[][][] maze; // 원본 배열
	static int[][][] copyMaze; // 복사한 배열

	static int minMove = Integer.MAX_VALUE; // 최소 이동 거리
	static int[] order; // 판을 놓는 순서
	static boolean[] visited; // 조합을 위한 방문관리

	// 6방 탐색
	static int[] xdir = { 0, 0, 0, 0, -1, 1 };
	static int[] ydir = { 0, 0, -1, 1, 0, 0 };
	static int[] zdir = { -1, 1, 0, 0, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		maze = new int[5][5][5]; // 원본 배열
		copyMaze = new int[5][5][5]; // 복사한 배열

		order = new int[5]; // 판을 놓는 순서
		visited = new boolean[5]; // 조합을 위한 방문관리

		for (int layer = 0; layer < 5; layer++) {
			for (int row = 0; row < 5; row++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int col = 0; col < 5; col++) {
					maze[layer][row][col] = Integer.parseInt(st.nextToken());
				} // End of col
			} // End of row
		} // End of layer

		perm(0);

		System.out.println(minMove == Integer.MAX_VALUE ? -1 : minMove);
		br.close();
	}// End of Main

	// 층을 쌓는 순서
	private static void perm(int r) {
		// 층을 쌓은 경우
		if (r == 5) {
			// 미로 제작
			for (int i = 0; i < 5; i++) {
				// maze[order[i]]의 0번 값을 copyMaze[i]의 0번째로 길이만큼 복사
				System.arraycopy(maze[order[i]], 0, copyMaze[i], 0, copyMaze.length);
			}

			// 회전 조합
			for (int i = 0; i < 4; i++) {
				rotate(4);
				for (int j = 0; j < 4; j++) {
					rotate(3);
					for (int k = 0; k < 4; k++) {
						rotate(2);
						for (int d = 0; d < 4; d++) {
							rotate(1);
							for (int f = 0; f < 4; f++) {
								rotate(0);
								// 출발지와 도착지가 1인 경우
								if (copyMaze[0][0][0] == 1 && copyMaze[4][4][4] == 1) {
									bfs(0, 0, 0);
								}
							}
						}
					}
				}
			}
		}

		// 층을 쌓을 순열
		for (int i = 0; i < 5; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[i] = r;
				perm(r + 1);
				order[i] = -1;
				visited[i] = false;
			}
		}
	}

	// BFS 탐색
	private static void bfs(int a, int b, int c) {
		Queue<PosMaze> q = new LinkedList<PosMaze>();
		boolean[][][] visited = new boolean[5][5][5];
		q.offer(new PosMaze(a, b, c, 0));

		while (!q.isEmpty()) {
			PosMaze p = q.poll();
			int x = p.x;
			int y = p.y;
			int z = p.z;
			int cnt = p.cnt;

			// 도착
			if (z == 4 && x == 4 && y == 4) {
				minMove = Math.min(minMove, cnt);
				break;
			}

			// 탐색
			for (int d = 0; d < 6; d++) {
				int dx = x + xdir[d];
				int dy = y + ydir[d];
				int dz = z + zdir[d];

				// 범위 && 방문 && 이동가능 "0"이 아닌경우
				if (isIn(dx, dy, dz) && !visited[dz][dx][dy] && copyMaze[dz][dx][dy] != 0) {
					visited[dz][dx][dy] = true;
					q.offer(new PosMaze(dx, dy, dz, cnt + 1));
				}
			}

		}
	}

	// 시계방향 90도 회전
	private static void rotate(int layer) {
		int[][] temp = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				temp[i][j] = copyMaze[layer][4 - j][i];
			}
		}

		// 복사
		System.arraycopy(temp, 0, copyMaze[layer], 0, temp.length);
	}

	// 범위 검사
	private static boolean isIn(int x, int y, int z) {
		if (x < 0 || y < 0 || z < 0 || x >= 5 || y >= 5 || z >= 5) {
			return false;
		}
		return true;
	}
}

// 위치 관리
class PosMaze {
	int x;
	int y;
	int z;
	int cnt;

	public PosMaze(int x, int y, int z, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.cnt = cnt;
	}

}