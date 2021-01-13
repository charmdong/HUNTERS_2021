package hunters210107_to_210113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 해킹
 * 메모리 : 159904kb
 * 시간 : 1092ms
 */

public class BJ_10282 {
	static int N,D,C;
	static ArrayList<ArrayList<Node>> list;
	static class Node implements Comparable<Node> {
		int a;
		int v;
		public Node(int a, int v) {
			super();
			this.a = a;
			this.v = v;
		}
		
		@Override
		public String toString() {
			return "Node [a=" + a + ", v=" + v + "]";
		}

		@Override
		public int compareTo(Node o) {
			return this.v - o.v;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
			D = Integer.parseInt(st.nextToken()); // 의존성 개수
			C = Integer.parseInt(st.nextToken()); // 처음 노드

			list = new ArrayList<>();
			for(int n=0;n<=N;n++) {
				list.add(new ArrayList<Node>());
			}
			
			for(int d=0;d<D;d++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				list.get(B).add(new Node(A,V)); // B가 감염되면 A가 연쇄감염
			}
			
			// 시작 노드 C로 부터 각 노드의 최소 비용 저장
			boolean visited[] = new boolean[N+1];
			int value[] = new int[N+1];
			Arrays.fill(value, Integer.MAX_VALUE);
			value[C] = 0;
	
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(C,0));
			
			while(!pq.isEmpty()) {
				Node node = pq.poll(); // node = 1,0
//				System.out.println(node.toString());
				if(visited[node.a]) continue;
				visited[node.a] = true;
				
				for(Node n : list.get(node.a)) {
//					System.out.println("하위 노드는 : "+n.toString());
					if(!visited[n.a] && value[n.a] > value[node.a] + n.v) {
						value[n.a] = value[node.a] + n.v;
						pq.add(new Node(n.a, value[n.a]));
					}
				}
//				System.out.println(Arrays.toString(value));
			}
			
			int time = 0;
			int com = 0;
			
			for(int i=1;i<=N;i++) {
				if(value[i] == Integer.MAX_VALUE || !visited[i]) continue;
				com++;
				if(value[i] > time) time = value[i];
			}
			System.out.println(com+" "+time);
			
		}
	}

}
