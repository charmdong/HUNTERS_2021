package BaekJoon_2020_12_30__2021_01_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @packageName : BaekJoon_2020_12_30__2021_01_06
 * @fileName : BJ_3055_탈출.java
 * @author : Mingeon
 * @date : 2020. 12. 31.
 * @language : JAVA
 * @classification :
 * @time_limit : 1sec
 * @required_time : 01:10 ~ 03:05
 * @submissions : 5
 * @description 94
 * 95번째 줄을 찾느라 1시간 반이 걸렸다. 
 * 큐나 스택 같은 자료구조를 이용한 반복문을 사용할 경우에는 조건을 완료했을 때, 반드시 clear를 할 것!
 */

public class BJ_3055_탈출 {

	static int R; // R행
	static int C; // C열
	static char[][] map; // 숲의 정보
	static boolean[][] visited;// 방문 관리
	static int minCnt; // 최소 시간
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static Queue<Pos> moveQ = new LinkedList<Pos>();
	static Queue<Pos> waterQ = new LinkedList<Pos>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];
		minCnt = Integer.MAX_VALUE;
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				char c = str.charAt(j);
				map[i][j] = c;
				// 고슴도치의 위치
				if (c == 'S') {
					moveQ.offer(new Pos(i, j));
					visited[i][j] = true;
				}
				// 물의 위치
				else if (c == '*') {
					waterQ.offer(new Pos(i, j));
				}
			}
		}

		// 이동 횟수
		int move = 0;
		while (!moveQ.isEmpty()) {
			spread(); // 물이 찰 예정인 칸으로 이동이 불가하므로 먼저 실행.
			bfs(++move); // 고슴도치의 이동
		}

		System.out.println(minCnt == Integer.MAX_VALUE ? "KAKTUS" : minCnt);
		br.close();
	}

	// 고슴도치의 이동 탐색
	private static void bfs(int move) {
		int cnt = moveQ.size();

		// 기존의 크기 동안
		while (cnt-- > 0) {
			Pos pos = moveQ.poll();
			int row = pos.row;
			int col = pos.col;

			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nextRow = row + dy[d];
				int nextCol = col + dx[d];

				// 범위 검사 && 고슴도치의 이동 가능 검사
				if (isIn(nextRow, nextCol) && isMove(nextRow, nextCol)) {

					// 비버의 굴 발견
					if (map[nextRow][nextCol] == 'D') {
						minCnt = move; // 최소값 탐색 완료
						moveQ.clear(); // 호출하는 곳의 while문 조건을 위한 Queue 초기화
						return; // 종료
					}

					// 방문한적이 없는 경우
					if (!visited[nextRow][nextCol]) {
						moveQ.offer(new Pos(nextRow, nextCol)); // 임시 Queue에 삽입
						visited[nextRow][nextCol] = true; // 방문 체크
					}
				}
			}
		}
	}

	// 물을 채울 곳을 탐색
	private static void spread() {
		int cnt = waterQ.size();

		// 기존의 크기 동안
		while (cnt-- > 0) {
			Pos waterPos = waterQ.poll();
			int row = waterPos.row;
			int col = waterPos.col;

			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nextRow = row + dy[d];
				int nextCol = col + dx[d];

				// 범위 검사 && 물이 찰 수 있는 경우
				if (isIn(nextRow, nextCol) && map[nextRow][nextCol] == '.') {
					waterQ.offer(new Pos(nextRow, nextCol));
					map[nextRow][nextCol] = '*';
				}
			}
		}
	}

	// 고슴도치의 이동 가능 여부
	private static boolean isMove(int row, int col) {
		if (map[row][col] == '.' || map[row][col] == 'D') {
			return true;
		}
		return false;
	}

	// 범위 검사
	private static boolean isIn(int row, int col) {
		if (row < 0 || row >= R || col < 0 || col >= C) {
			return false;
		}
		return true;
	}

}

class Pos {
	int row;
	int col;

	public Pos(int row, int col) {
		this.row = row;
		this.col = col;
	}

}
