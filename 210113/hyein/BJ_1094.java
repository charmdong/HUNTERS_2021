package hunters210107_to_210113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 막대기
 */
public class BJ_1094 {
	static int X;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		X = Integer.parseInt(br.readLine());
		
		int res = 0;
		int S = 64;
		
		int sum = 0;
		while(S>0 && sum != X) {
			if((X-sum)>=S) {
				sum += S;
				res++;
			}
			
			S /= 2;
		}
		System.out.println(res);
	}
}
