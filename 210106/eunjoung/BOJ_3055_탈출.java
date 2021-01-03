package boj1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_3055_탈출 {
	
	static int N,M;
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Point start,end;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] sarr = s.split(" ");
		N = Integer.parseInt(sarr[0]);
		M = Integer.parseInt(sarr[1]);
		int[][] board = new int[N][M];
		boolean[][] wtVisit = new boolean[N][M];
		boolean[][] visit = new boolean[N][M];
		
		Queue<Point> water = new LinkedList<>();
		Queue<Point> me = new LinkedList<>();
		for(int i=0;i<N;i++) {
			s = br.readLine();
			for(int j=0;j<M;j++) {
				if(s.charAt(j) == 'D')	{board[i][j] = 9; end = new Point(i,j);}
				else if(s.charAt(j) == '.') board[i][j] = 0;
				else if(s.charAt(j) == '*') {board[i][j] = 2; water.add(new Point(i,j)); wtVisit[i][j] = true;}
				else if(s.charAt(j) == 'X') board[i][j] = 1;
				else if(s.charAt(j) == 'S') {board[i][j] = 0; start = new Point(i,j);}
			}
		}

		int time = 0;
		me.add(start);
		visit[start.x][start.y] = true;
		while(!water.isEmpty()||!me.isEmpty()) {
			time++;
			int W = water.size();
			int P = me.size();
			for(int i=0;i<W;i++) {
				Point tmp = water.poll();
				for(int dir =0;dir<4;dir++) {
					int nx = tmp.x+dx[dir];
					int ny = tmp.y +dy[dir];
					if(nx<0||nx>=N||ny<0||ny>=M) continue;
					//if(board[nx][ny] !=0)
					if(board[nx][ny] == 1 || board[nx][ny] == 2|| board[nx][ny] ==9 || wtVisit[nx][ny]) continue;
					water.add(new Point(nx,ny));
					wtVisit[nx][ny] = true;
					board[nx][ny] =2;
				}
			}
			
			for(int i=0;i<P;i++) {
				Point tmp = me.poll();
				for(int dir=0;dir<4;dir++) {
					int nx = tmp.x+dx[dir];
					int ny = tmp.y +dy[dir];
					if(nx<0||nx>=N||ny<0||ny>=M) continue;
					if(board[nx][ny] == 1 || board[nx][ny] == 2|| visit[nx][ny] || wtVisit[nx][ny]) continue;
					if(nx == end.x && ny == end.y) {
						System.out.println(time);
						return;
					}
					me.add(new Point(nx,ny));
					visit[nx][ny] = true;
				}
			}
			
			
		}
		System.out.println("KAKTUS");
		return;
		
		
	}

}
