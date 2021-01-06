package BaekJoon_2020_12_30__2021_01_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @packageName : BaekJoon_2020_12_30__2021_01_06
 * @fileName : BJ_1759_암호만들기.java
 * @author : Mingeon
 * @date : 2020. 12. 31.
 * @language : JAVA
 * @classification : 브루트포스 알고리즘, 조합론, 백트래킹
 * @time_limit : 2sec
 * @required_time : 14:00 ~ 14:48
 * @submissions : 3
 * @description
 */

public class BJ_1759_암호만들기 {

	static int L; // 암호의 길이
	static int C; // 문자들의 갯수
	static char[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		} // End of input

		// 사전순을 위한 정렬
		Arrays.sort(arr);
		dfs(0, "");
	}

	private static String dfs(int idx, String str) {

		if (str.length() == L && isValid(str)) {
			System.out.println(str);
			return str;
		}
		if (idx >= C) {
			return str;
		}

		// 현재 인덱스 포함
		dfs(idx + 1, str + arr[idx]);

		// 현재 인덱스 스킵
		dfs(idx + 1, str);

		return str;
	}

	// 모음 1개 이상, 자음 2개 이상 포함 확인
	private static boolean isValid(String str) {
		if (str.contains("a") || str.contains("e") || str.contains("i") || str.contains("o") | str.contains("u")) {
			str = str.replace("a", "");
			str = str.replace("e", "");
			str = str.replace("i", "");
			str = str.replace("o", "");
			str = str.replace("u", "");
			if (str.length() >= 2) {
				return true;
			}
		}

		return false;
	}

}
