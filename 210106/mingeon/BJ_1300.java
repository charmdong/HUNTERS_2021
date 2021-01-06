package BaekJoon_2020_12_30__2021_01_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1300_K번째수 {

	static int N; // 배열의 크기
	static int k; // 구해야하는 인덱스의 번호

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		int left = 1;
		int right = k;
		int ans = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			int cnt = getCnt(mid, N);
			if (cnt >= k) {
				ans = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(ans);
	}

	static int getCnt(int x, int n) {
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			cnt += Math.min(x / i, n);
		}
		return cnt;
	}
}
