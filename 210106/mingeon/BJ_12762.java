package BaekJoon_2020_12_30__2021_01_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @packageName : BaekJoon_2020_12_30__2021_01_06
 * @fileName : BJ_12762_롤러코스터.java
 * @author : Mingeon
 * @date : 2021. 1. 1.
 * @language : JAVA
 * @classification : DP(다이나믹 프로그래밍)
 * @time_limit : 1sec
 * @required_time : 09:00 ~
 * @submissions : 1
 * @description
 */

public class BJ_12762_롤러코스터 {

	static int N; // 구간의 길이
	static ArrayList<Integer> pillar; // 기둥의 정보

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int ret = N;

		pillar = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int preheight = -1; // 이전 기둥
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());

			// 연속적으로 높이가 같은 기둥 제거
			if (preheight == height) {
				ret--;
			} else {
				pillar.add(height);
				preheight = height;
			}
		} // End of input

		System.out.println(ret);
		br.close();
	}

	// 하이라이트가 성립하는지 확인
	
}
