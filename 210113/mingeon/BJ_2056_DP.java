package BaekJoon_210107_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @packageName : BaekJoon_210107_13
 * @fileName : BJ_2056_작업.java
 * @author : Mingeon
 * @date : 2021. 1. 12.
 * @language : JAVA
 * @classification : DP or topological sorting
 * @time_limit : 2sec
 * @required_time : 00:23 ~ 01:35
 * @submissions : 1
 * @description
 */

public class BJ_2056_작업_DP {

	static int N; // 수행해야 할 작업이 갯수
	static int[] work; // 각 작업을 수행하는데 걸리는 시간
	static int minTime; // 모든 작업을 완료하기 위한 최소 시간

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		work = new int[N + 1];// 각 작업을 수행하는데 걸리는 시간

		minTime = 0;
		for (int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int time = Integer.parseInt(st.nextToken()); // 해당 작업에 걸리는 시간

			work[i] = time;

			// 선형관계
			int depCnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < depCnt; j++) {

				int linear = Integer.parseInt(st.nextToken());

				// 가장 긴 수행 시간을 선택 ( 1, 2 ,3 번이 선형관계라면 그중 가장 긴 시간)
				work[i] = Math.max(work[i], work[linear] + time);
			}

			minTime = Math.max(minTime, work[i]);
		}
		System.out.println(minTime);
		br.close();
	}

}
