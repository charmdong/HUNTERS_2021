package BaekJoon_210107_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @packageName : BaekJoon_210107_13
 * @fileName : BJ_1094_막대기.java
 * @author : Mingeon
 * @date : 2021. 1. 11.
 * @language : JAVA
 * @classification :
 * @time_limit : 2sec
 * @required_time : 23:47 ~ 00:13
 * @submissions : 1
 * @description
 */

public class BJ_1094_막대기 {

	static int X; // 만들고 싶은 막대 길이
	static int cnt; // X를 만들기 위한 막대의 갯수
	static int sum; // 가지고 있는 막대 길이의 합
	static ArrayList<Integer> stick; // 자른 막대를 보관

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		stick = new ArrayList<Integer>();
		X = Integer.parseInt(br.readLine()); // 만들고 싶은 막대 길이

		stick.add(64); // 가지고 있는 막대 추가

		// 1. 지민이가 가지고 있는 막대의 길이를 모두 더한다. 처음에는 64cm 막대 하나만 가지고 있다.
		sum = 64;

		// 이때, 합이 X보다 크다면, 아래와 같은 과정을 반복한다.
		while (sum > X) {

			// 1-1. 가지고 있는 막대 중 길이가 가장 짧은 것을 절반으로 자른다.
			int lastIdx = stick.size() - 1; // 마지막 인덱스.
			int temp = stick.get(lastIdx) / 2; // 가장 짧은 막대를 2로 나눈다.
			stick.remove(lastIdx); // 꺼낸 막대 제거

			// 1-2. 만약, 위에서 자른 막대의 절반 중 하나를 버리고 남아있는 막대의 길이의 합이 X보다 크거나 같다면,
			if (sum - temp >= X) {
				// 1-2. 위에서 자른 막대의 절반 중 하나를 버린다.
				stick.add(temp);
			} else {
				stick.add(temp);
				stick.add(temp);
			}

			sum = 0; // 초기화
			for (int i : stick) {
				sum += i;
			}

		} // End of while

		// 2. 이제, 남아있는 모든 막대를 풀로 붙여서 Xcm를 만든다.
		System.out.println(stick.size());
		br.close();
	}

}
