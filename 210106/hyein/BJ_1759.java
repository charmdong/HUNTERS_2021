package com.hunters.dec.forth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1759 {
	static int L,C;
	static char str[];
	static List<String> sortedRes;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		str = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int c=0;c<C;c++) {
			str[c] = st.nextToken().charAt(0);
		}
		
		sortedRes = new ArrayList<>();
		
		//C개 중 L개 선택
		comb(new char[L], 0, 0, L, C);
		Collections.sort(sortedRes);;
		for(int i=0;i<sortedRes.size();i++)
			System.out.println(sortedRes.get(i));
		
	}
	
	private static void comb(char[] res, int depth, int idx, int r, int n) {
		if (r==0) {
			// 최소 한개의 모음과 최소 두개의 자음으로 구성되었는지 확인
			if(check(res)) {
				char[] tmp = Arrays.copyOf(res, res.length);
				Arrays.sort(tmp);
				sortedRes.add(String.valueOf(tmp));
			}
			// 정렬
			return;
		}
		if(depth == n) return;
		
		res[idx] = str[depth];
		comb(res,depth+1,idx+1, r-1,n);
		comb(res,depth+1, idx, r,n);
	}

	private static boolean check(char[] res) {
		int jaCnt = 0;
		int moCnt = 0;
		
		String mo = "aeiou";
		
		for(char ch : res) {
			if(mo.contains(String.valueOf(ch))) {
				moCnt++;
			} else 
				jaCnt++;
		}
		return (moCnt>=1) ? ((jaCnt>=2) ? true : false) : false;
	}


}
