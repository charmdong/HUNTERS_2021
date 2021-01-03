package com.hunters.dec.forth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1차 시도 : 21% 틀렸습니다.
 * 2차 시도 : 메모리 초과
 */

public class BJ_1525 {
	static int[] fin = {1,2,3,4,5,6,7,8,0};
	
	//각 인덱스에서 교환할 수 있는 인덱스
	static int[][] dir = {{1,3},{0,2,4},{1,5},{0,4,6},{1,3,5,7},
						{2,4,8},{3,7},{4,6,8},{5,7}};
	// visited관리는 비트마스킹 이용
	static HashSet<Long> visited;
	
	static class Dot {
		int idx, cnt;
		int[] puzzle;
		public Dot(int idx, int cnt, int[] puzzle) {
			this.idx = idx;
			this.cnt = cnt;
			this.puzzle = puzzle;
		}
		@Override
		public String toString() {
			return "Dot [idx=" + idx + ", cnt=" + cnt + ", puzzle=" + Arrays.toString(puzzle) + "]";
		}
		
	}
	
	static int res = -1;
	
	public static void main(String[] args) throws IOException {
		int[] puzzle = new int[9];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Dot zero = null;
		for(int i=0;i<3;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 0) {
					zero = new Dot(3*i+j,0,null);
				}
				puzzle[3*i+j] = n; // 메모리 절약을 위해 1차원 배열 사용
			}
		}
		zero.puzzle = puzzle;
		visited = new HashSet<>();
		visited.add(getBit(puzzle));
		BFS(zero);
		System.out.println(res);
	}
	
	private static void BFS(Dot zero) {
		Queue<Dot> queue = new LinkedList<>();
		queue.add(zero);
		while(!queue.isEmpty()) {
			Dot q = queue.poll();

			if(checkFin(q)) {
				res = q.cnt;
				return;
			}
			
			for(int i=0;i<dir[q.idx].length;i++) {
				// 교환하였을때 배열
				int nextIdx = dir[q.idx][i];
				int tmp = q.puzzle[nextIdx];
				int puzzleTmp[] = Arrays.copyOf(q.puzzle, 9);
				puzzleTmp[nextIdx] = puzzleTmp[q.idx];
				puzzleTmp[q.idx] = tmp;
				// 교환한 배열이 이미 해본 경우의 수이면 continue
				if(visited.contains(getBit(puzzleTmp))) continue;
				visited.add(getBit(puzzleTmp));
				queue.add(new Dot(nextIdx, q.cnt+1, puzzleTmp));
			}

		}
	}

	public static boolean checkFin(Dot q) {
		for(int i=0;i<9;i++) {
			if(q.puzzle[i] != fin[i]) {
				return false;
			}
		}
		return true;
	}
	
	// 1차원 배열을 2비트로 변환
	public static Long getBit(int[] puzzle) {
		long s = 0L;
		int order = 0;
		for(int i=0;i<9;i++) {
			s |= puzzle[i] << (4*order);
			order++;
		}
		return s;
	}

	

}
