package Hunters_210113_20;

/**
 * @packageName : Hunters_210113_20
 * @fileName : PG_자물쇠와열쇠.java
 * @author : Mingeon
 * @date : 2021. 1. 14.
 * @language : JAVA
 * @classification :
 * @time_limit :
 * @required_time : 02:14 ~
 * @submissions : 1
 * @description
 */
 
public class PG_자물쇠와열쇠 {
	public static void main(String[] args) {
		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		자물쇠와열쇠 s = new 자물쇠와열쇠();
		System.out.println(s.solution(key, lock));
	}

}

class 자물쇠와열쇠 {
	static int keyLen;
	static int lockLen;
	static int[][] key;
	static int[][] lock;

	// lock의 기존 위치
	static int startLock;
	static int endLock;

	public boolean solution(int[][] key, int[][] lock) {
		boolean answer = true;
		this.key = key;
		this.lock = lock;
		keyLen = key.length;
		lockLen = lock.length;
		startLock = keyLen - 1;
		endLock = startLock + lock.length;

		int[][] copyLock = makeLock(lock);

		// key 회전
		for (int rc = 0; rc < 4; rc++) {

			int endPoint = copyLock.length - key.length;
			// key를 이동하며 copyLock을 탐색
			for (int i = 0; i <= endPoint; i++) {
				for (int j = 0; j <= endPoint; j++) {

					// key을 copyLock에 삽입
					for (int row = 0; row < keyLen; row++) {
						for (int col = 0; col < keyLen; col++) {
							copyLock[i + row][j + col] += key[row][col];
						}
					}

					// 열리는 경우
					if (isOpen(copyLock)) {
						return answer;
					}
					copyLock = makeLock(lock);
				}
			} // End of move key
			
			// 회전
			key = rotate(key);
		} // End of rotate key

		return false;
	}

	// lock의 범위를 넓혀 생성
	private static int[][] makeLock(int[][] lock) {
		int len = lockLen + 2 * (keyLen - 1);
		int[][] temp = new int[len][len];

		int row = 0;
		int col;
		for (int i = startLock; i < endLock; i++) {
			col = 0;
			for (int j = startLock; j < endLock; j++) {
				temp[i][j] = lock[row][col++];
			}
			row++;
		}

		return temp;
	}

	// 자물쇠가 열리는지 탐색
	private static boolean isOpen(int[][] copyLock) {

		for (int i = startLock; i < endLock; i++) {
			for (int j = startLock; j < endLock; j++) {
				// 자물쇠와 열쇠의 합이 1이 아닌 경우
				if (copyLock[i][j] != 1) {
					return false;
				}
			}
		}

		return true;
	}

	// 키를 시계방향 90도 회전
	private static int[][] rotate(int[][] key) {
		int len = key.length;
		int[][] temp = new int[len][len];

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				temp[i][j] = key[len - j - 1][i];
			}
		}

		return temp;
	}
}