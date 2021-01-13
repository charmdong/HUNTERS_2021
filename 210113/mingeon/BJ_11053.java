package BaekJoon_210107_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @packageName : BaekJoon_210107_13
 * @fileName : BJ_11053_가장긴증가하는부분수열.java
 * @author : Mingeon
 * @date : 2021. 1. 12.
 * @language : JAVA
 * @classification : DP
 * @time_limit : 1sec
 * @required_time : 19:23 ~ 20:18
 * @submissions : 1
 * @description
 */

public class BJ_11053_가장긴증가하는부분수열 {

	static int size; // 수열 A의 크기
	static int[] arr; // 수열 A
	static int[] dp; // 수열 A에 대한 dp를 보관

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine()); // 수열 A의 크기
		arr = new int[size]; // 수열 A
		dp = new int[size]; // 부분수열을 보관할 배열

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < size; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
		} // End of input

		for (int idx = 0; idx < size; idx++) {
			dp[idx] = 1;

			// 이전 수들의 부분수열 길이(dp[])에 대한 반복문
			for (int pre = 0; pre < idx; pre++) {

				// 현재의 수가 더 큰 경우 && 이전의 수에 대한 증가수열의 길이(dp[])가 더 크거나 경우
				if (arr[pre] < arr[idx] && dp[idx] <= dp[pre]) {
					dp[idx] = dp[pre] + 1;
				}
			}

		} // End of idx sentence
		
		int maxLen = 0;
		for (int i : dp) {
			maxLen = Math.max(maxLen, i);
		}

		System.out.println(maxLen);
		br.close();

	} // End of main

}
