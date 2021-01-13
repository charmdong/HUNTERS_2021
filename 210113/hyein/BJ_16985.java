package hunters210107_to_210113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * maaaaaaaaaaaaaaaaaaaze
 */
public class BJ_16985 {
	static int[][][] maze, Cmaze;
	static int order[];
	static int dx[] = { 0,0,1,-1,0,0 }; 
	static int dy[] = { 1,-1,0,0,0,0 };
	static int dz[] = { 0,0,0,0,1,-1 };
	static int min;
	
	static class Pos {
		int x,y,z;
		int cnt;

		public Pos(int x, int y, int z, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.cnt = cnt;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		maze = new int[5][5][5];
		Cmaze = new int[5][5][5];
		for(int i=0;i<5*5;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<5;j++) {
				maze[i/5][i%5][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 순열 이용해서 미로 순서 정하기
		order = new int[5];
		min = 987654321;
		perm(new boolean[5], 0);
		
		if(min == 987654321)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	private static void perm(boolean[] visited, int depth) {
		if(depth == 5) {
			// order의 순서대로 미로 쌓기
			for(int i=0;i<5;i++) {
				System.arraycopy(maze[order[i]], 0, Cmaze[i], 0, Cmaze[i].length);
			}
			
			// 미로 회전
			for(int a=0;a<4;a++) {
				rotate(0);
				for(int b=0;b<4;b++) {
					rotate(1);
					for(int c=0;c<4;c++) {
						rotate(2);
						for(int d=0;d<4;d++) {
							rotate(3);
							for(int e=0;e<4;e++) {
								rotate(4);
								// 입구와 출구를 지나갈 수 있다면
								if(Cmaze[0][0][0] == 1 && Cmaze[4][4][4] == 1)
									BFS();
							}
						}
					}
				}
			}
			
			return;
		}
		for(int i=0;i<5;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			order[depth] = i;
			perm(visited,depth+1);
			order[depth] = 0;
			visited[i] = false;
		}
		
	}

	private static void BFS() {
		boolean[][][] visited = new boolean[5][5][5];
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(0,0,0,0));
		visited[0][0][0] = true;
		while(!queue.isEmpty()) {
			Pos q = queue.poll();
			if(q.x==4 && q.y==4 && q.z==4) {
				min = Math.min(min, q.cnt);
				return;
			}
			
			for(int d=0;d<6;d++) {
				int nz = q.z + dz[d];
				int nx = q.x + dx[d];
				int ny = q.y + dy[d];
				if(nz<0 || nz>=5 || nx<0 || nx>=5 || ny<0 || ny>=5) continue;
				if(visited[nz][nx][ny] || Cmaze[nz][nx][ny] == 0) continue;
				visited[nz][nx][ny] = true;
				queue.add(new Pos(nx,ny,nz,q.cnt+1));
			}
		}
	}

	private static void rotate(int layer) {
		int[][] tmp = new int[5][5];
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				tmp[i][j] = Cmaze[layer][i][j];
			}
		}
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				Cmaze[layer][i][j] = tmp[j][4-i];
			}
		}
	}
}
