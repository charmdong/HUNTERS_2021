package com.hunters.dec.forth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_3055 {
	static int R,C;
	static char[][] map;
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	static boolean visited[][];
	
	static class Node implements Comparable<Node> {
		int r,c;
		int time;
		int ch;
		public Node (int r, int c, int time, int ch) {
			this.r=r;
			this.c=c;
			this.time=time;
			this.ch=ch;
		}
		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", time=" + time + ", ch=" + ch + "]";
		}
		@Override
		public int compareTo(Node o) {
			if(this.time < o.time) {
				return -1;
			} else if (this.time == o.time) {
				return this.ch <= o.ch ? -1 : 1;
			} else 
				return 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		Node now = null;

		PriorityQueue<Node> queue = new PriorityQueue<>();
		for(int r=0;r<R;r++) {
			String tmp = br.readLine();
			for(int c=0;c<C;c++) {
				map[r][c] = tmp.charAt(c);
				if(map[r][c] == 'S') {
					visited[r][c] = true;
					queue.offer(new Node(r,c,0,2));
				}
				if(map[r][c] == '*') {
					queue.offer(new Node(r,c,0,1));
				}
			}
		}
		int res = BFS(queue);
		if(res == -1) System.out.println("KAKTUS");
		else System.out.println(res);
	}

	private static int BFS(PriorityQueue<Node> queue) {
		
		while(!queue.isEmpty()) {
			Node q = queue.poll();
			for(int d=0;d<4;d++) {
				int nr = q.r + dr[d];
				int nc = q.c + dc[d];
				if(nr<0 || nr>=R || nc<0 || nc>=C || visited[nr][nc]) continue;
				if(map[nr][nc] == 'X' || map[nr][nc] == '*') continue;
				if(q.ch == 1) {
					if(map[nr][nc]=='D') continue;
					map[nr][nc] = '*';
					queue.add(new Node(nr,nc,q.time+1,1));
				} else if(q.ch==2) {
					if(map[nr][nc] == 'D') {
						return q.time+1;
					}
					if(map[nr][nc] == '.') {
						visited[nr][nc] = true;
						queue.add(new Node(nr,nc,q.time+1,2));
					}
				}
			}
		}
		return -1;
	}

}
