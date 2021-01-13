package hunters210107_to_210113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2056 {
	static int N;
	static ArrayList<ArrayList<Integer>> list;
	static int T[]; // 해당 작업에 걸리는 시간 저장하는 배열
	static int S[]; //DP계산 한 시간 값을 넣는 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for(int n=0;n<=N;n++) {
			list.add(new ArrayList<Integer>());
		}
		T = new int[N+1];
		S = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			int prevN = Integer.parseInt(st.nextToken());
			for(int j=0;j<prevN;j++) {
				list.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i=N;i>0;i--) {
			if(S[i]!=0) continue;
			DP(i);
		}
		
		int res = 0;
		for(int i=1;i<=N;i++) {
			if(S[i] > res) res = S[i];
		}
		System.out.println(res);
	
	}

	private static int DP(int idx) {
		if(idx==1 || list.get(idx).size() == 0) {
			S[idx] = T[idx];
			return S[idx];
		}
		S[idx] = 0;
		for(int i : list.get(idx)) {
			if(S[i]==0) {
				S[idx] = Integer.max(S[idx], DP(i));
			}
			else {
				S[idx] = Integer.max(S[idx], S[i]);
			}
		}
		S[idx] += T[idx];
		return S[idx];
	}

}
