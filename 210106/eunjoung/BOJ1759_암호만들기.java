package boj1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_dkaghaksemfrl {

	static int N,M;
	static char src[];
	static int[] arr,tmp;
	static boolean[] visit;
	static StringBuilder sb ;
	static void go(int idx, int before) {
		if(idx==N) {
			boolean flag = false;
			int cnt = 0;
			String s = "";
			for(int i=0;i<N;i++) {
				s += src[arr[i]];
				if(src[arr[i]] == 'a'||src[arr[i]] == 'e'||src[arr[i]] == 'i'||src[arr[i]] == 'o'||src[arr[i]] == 'u')
					flag = true;
				else
					cnt++;
			}
			if(flag && cnt>=2)
				sb.append(s + "\n");
		}else {
			for(int i=before;i<M;i++) {
				if(!visit[i]) {
					arr[idx] = tmp[i];
					visit[i] =true;
					go(idx+1,i);
					visit[i] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		src = new char[M];
		tmp = new int[M];
		arr = new int[N];
		visit = new boolean[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			src[i] = st.nextToken().charAt(0); 
			tmp[i]=i;
		}
		Arrays.sort(src);
		go(0,0);
		System.out.println(sb);
	
		
		
	}
}
