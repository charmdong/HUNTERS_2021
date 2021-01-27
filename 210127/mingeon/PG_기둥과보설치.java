package Hunters_210120_27;

public class PG_기둥과보설치 {

	public static void main(String[] args) {
		int n = 5;
		int[][] build_frame = { { 0, 0, 0, 1 }, { 2, 0, 0, 1 }, { 4, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 },
				{ 2, 1, 1, 1 }, { 3, 1, 1, 1 }, { 2, 0, 0, 0 }, { 1, 1, 1, 0 }, { 2, 2, 0, 1 } };
		기둥과보설치 s = new 기둥과보설치();
		System.out.println(s.solution(n, build_frame));
	}

}

class 기둥과보설치 {
	static boolean[][] pillars; // 기둥
	static boolean[][] beams; // 보
	static int n;

	// 가로좌표, 세로좌표, 0:기둥 1:보, 0:삭제 1:설치
	public int[][] solution(int n, int[][] build_frame) {
		int[][] answer;
		int cnt = 0;
		pillars = new boolean[n + 1][n + 1];
		beams = new boolean[n + 1][n + 1];
		this.n = n;

		for (int[] frame : build_frame) {
			int col = frame[0]; // 열
			int row = frame[1]; // 행
			int what = frame[2]; // '기둥==0'or'보==1'
			int act = frame[3]; // '삭제==0'or'설치==1'

			switch (what) {
			// 기둥
			case 0:
				// 삭제
				if (act == 0) {
					pillars[row][col] = false;
					cnt--;
					if (!confirmPillar(row, col)) {
						pillars[row][col] = true;
						cnt++;
					}

				}
				// 설치
				else {
					if (validPillar(row, col)) {
						pillars[row][col] = true;
						cnt++;
					}
				}
				break;

			// 보
			case 1:
				if (act == 0) {
					beams[row][col] = false;
					cnt--;
					if (!confirmBeam(row, col)) {
						beams[row][col] = true;
						cnt++;
					}
				} else {
					if (validBeam(row, col)) {
						beams[row][col] = true;
						cnt++;
					}
				}
				break;

			} // End of what

		} // End of build_frame

		// 출력문 정리
		answer = new int[cnt][3];
		int idx = 0;
		for (int col = 0; col <= n; col++) {
			for (int row = 0; row <= n; row++) {

				if (pillars[row][col]) {
					answer[idx][0] = col;
					answer[idx][1] = row;
					answer[idx][2] = 0;
					idx++;
				}
				if (beams[row][col]) {
					answer[idx][0] = col;
					answer[idx][1] = row;
					answer[idx][2] = 1;
					idx++;
				}
			}
		}

//		for (int[] ans : answer) {
//			System.out.println("{" + ans[0] + "," + ans[1] + "," + ans[2] + "}");
//		}

		return answer;
	}

	// 기둥 삭제 여부
	private boolean confirmPillar(int row, int col) {
		// 위의 기둥
		if (isPillar(row + 1, col) && !validPillar(row + 1, col)) {
			return false;
		}
		// 위의 보
		if (isBeam(row + 1, col) && !validBeam(row + 1, col)) {
			return false;
		}
		// 좌상단 위의 보
		if (isBeam(row + 1, col - 1) && !validBeam(row + 1, col - 1)) {
			return false;
		}

		return true;
	}

	// 보 삭제 여부
	private boolean confirmBeam(int row, int col) {
		// 해당 위치 기둥
		if (pillars[row][col] && !validPillar(row, col)) {
			return false;
		}
		// 해당 위치 오른쪽 기둥
		if (isPillar(row, col + 1) && !validPillar(row, col + 1)) {
			return false;
		}
		// 왼쪽 보
		if (isBeam(row, col - 1) && !validBeam(row, col - 1)) {
			return false;
		}
		// 오른쪽 보
		if (isBeam(row, col + 1) && !validBeam(row, col + 1)) {
			return false;
		}
		return true;
	}

	// 기둥 조회
	private boolean isPillar(int row, int col) {
		if (row < 0 || row > n || col < 0 || col > n) {
			return false;
		}
		return pillars[row][col];
	}

	// 보 조회
	private boolean isBeam(int row, int col) {
		if (row < 0 || row > n || col < 0 || col > n) {
			return false;
		}
		return beams[row][col];
	}

	// '기둥' 유효성 검사
	private boolean validPillar(int row, int col) {
		// 바닥인 경우 || 기둥 위인 경우 || 보 위인 경우
		return row == 0 || isPillar(row - 1, col) || isBeam(row, col - 1) || isBeam(row, col);
	}

	// '보' 유효성 검사
	private boolean validBeam(int row, int col) {
		// 한쪽 끝 부분에 기둥이 있는 경우 || 양쪽 끝 부분이 다른 보와 연결된 경우
		return isPillar(row - 1, col) || isPillar(row - 1, col + 1) || (isBeam(row, col - 1) && isBeam(row, col + 1));
	}

}