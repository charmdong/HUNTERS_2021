package hunters210107_to_210113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11053 {
	static int N;
	static int[] A;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int cnt[] = new int[N];
		Arrays.fill(cnt, 1);
		for(int i=1;i<N;i++) {
			for(int j=0;j<i;j++) {
				if(A[i]>A[j]) {
					cnt[i] = Math.max(cnt[i], cnt[j]+1);
				}
			}
		}
		int max = 0;
		for(int i=0;i<N;i++) {
			max = Math.max(max, cnt[i]);
		}
		System.out.println(max);
	}
}
