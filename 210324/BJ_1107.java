import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1107 {

	private static String N;
	private static int M, answer;
	private static boolean[] isBroken = new boolean[10];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = br.readLine();
		M = Integer.parseInt(br.readLine());

		for (int index = 0; index < 10; index++) {
			isBroken[index] = false;
		}

		answer = Math.abs(Integer.parseInt(N) - 100);
		
		if(M != 0) {
			String[] brokenList = br.readLine().split(" ");

			for (int index = 0; index < brokenList.length; index++) {
				isBroken[Integer.parseInt(brokenList[index])] = true;
			}
		}
		
		solution(N.length() - 1, "", 0);
		solution(N.length(), "", 0);
		solution(N.length() + 1, "", 0);
		
		System.out.println(answer);
	}

	private static void solution(int limit, String cur, int len) {
		if(len == limit) {
			if("".equals(cur)) {
				return;
			}
			
			int diff = len + Math.abs(Integer.parseInt(N) - Integer.parseInt(cur));
			
			answer = Math.min(answer, diff);
			return;
		}
		
		for(int index = 0; index < 10; index++) {
			if(!isBroken[index]) {
				solution(limit, cur + index, len + 1);
			}
		}
	}

}
