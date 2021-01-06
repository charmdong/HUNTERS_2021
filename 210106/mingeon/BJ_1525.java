//package BaekJoon_2020_12_30__2021_01_06;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
///**
// * @packageName : BaekJoon_2020_12_30__2021_01_06
// * @fileName : BJ_1525_퍼즐.java
// * @author : Mingeon
// * @date : 2020. 12. 31.
// * @language : JAVA
// * @classification :
// * @time_limit :
// * @required_time :
// * @submissions :
// * @description
// */
//
//public class BJ_1525_퍼즐 {
//
//	static int[][] map; // 퍼즐의 정보
//	static int cnt; // 이동 횟수
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		map = new int[3][3];
//		cnt = Integer.MAX_VALUE;
//		Info zero = null;
//
//		for (int i = 0; i < 3; i++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			for (int j = 0; j < 3; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//				if (map[i][j] == 0) {
////					zero = new Info(i, j, 0);
//				}
//			}
//		} // End of input
//
//		bfs(zero);
//		System.out.println(cnt == Integer.MAX_VALUE ? -1 : cnt);
//		br.close();
//	}
//
//	private static void bfs(Info zero) {
//		Queue<Info> q = new LinkedList<Info>();
//
//	}
//
//	// '0'의 위치 이동
//	private static void change(Info target, Info zero) {
//		int temp = map[target.row][target.col];
//		map[target.row][target.col] = 0;
//		map[zero.row][zero.col] = temp;
//	}
//
//	// 범위 검사
//	private static boolean isIn(int row, int col) {
//		if (0 < row || row >= 3 || col < 0 || col >= 3) {
//			return false;
//		}
//		return true;
//	}
//
//}
