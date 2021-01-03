package com.hunters.dec.forth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_12762 {
	static int N;
	static int height[];
	static class node {
		int h;
		int prev;
		public node (int h, int prev) {
			this.h=h;
			this.prev=prev;
		}
		@Override
		public String toString() {
			return "node [h=" + h + ", prev=" + prev + "]";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		height = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}

		int max = 0;

		for (int i = 0; i < N; i++) {
			int left = DecLeft(i);
			int right = IncRight(i);
//			System.out.println("[" + i + "] " + left + " " + right);
			if (left + right > max)
				max = left + right;
		}
		System.out.println(max - 1);

	}

	// 오른쪽 기둥들 중 i기둥을 포함한 가장 긴 증가수열
	// 범위 : i~N-1
	private static int IncRight(int i) {
		int inc[] = new int[N];
		List<node> prev = new ArrayList<>();
		prev.add(new node(0,0));
		for (int p = i; p < N; p++) {
			if(height[p]<=height[i] && p!=i) continue;
			int maxP = 0;
			int maxH = 0;
			for(node n : prev) {
				if(height[p] > n.prev) {
					if(maxH < n.h+1) {
						maxH = n.h+1;
						maxP = height[p];
					}
				}
			}
			prev.add(new node(maxH,maxP));
			inc[p] = maxH;
		}
//		System.out.println(Arrays.toString(inc));
		int max=0;
		for(int p=i;p<N;p++) {
			max = Math.max(max, inc[p]);
		}
		return max;
	}

	// 왼쪽 기둥들 중 i기둥을 포함한 가장 긴 감소수열
	// 범위 : 0 ~ i
	private static int DecLeft(int i) {
		int dec[] = new int[i + 1];
		List<node> prev = new ArrayList<>();
		prev.add(new node(0,987654321));
		for (int q = 0; q <= i; q++) {
			if(height[q]<=height[i] && q!=i) continue;
			int maxP = 0;
			int maxH = 0;
//			System.out.println("Q :"+q);
			for(node n : prev) {
//				System.out.println("현재 높이: "+height[q]+" 이전 높이: "+n.prev);
				if(height[q] < n.prev) {
//					System.out.println("기준 통과");
					if(maxH < n.h+1) {
//						System.out.println("최고 길이 갱신 : "+(n.h+1));
						maxH = n.h+1;
						maxP = height[q];
					}
				}
				
			}
			prev.add(new node(maxH, maxP));
			dec[q] = maxH;
		}
//		System.out.println(Arrays.toString(dec));
		return dec[i];
	}

}
