package BaekJoon_210107_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @packageName : BaekJoon_210107_13
 * @fileName : BJ_10282_해킹.java
 * @author : Mingeon
 * @date : 2021. 1. 13.
 * @language : JAVA
 * @classification : Dijkstra
 * @time_limit : 2sec
 * @required_time : 00:00 ~ 03:50
 * @submissions : 1
 * @description
 */

public class BJ_10282_해킹 {

	static int cnt; // 해킹 당하는 컴퓨터 수
	static int[] dist;
	static ArrayList<Edge>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		while (testcase-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 컴퓨터 갯수
			int E = Integer.parseInt(st.nextToken()); // 의존성 갯수
			int start = Integer.parseInt(st.nextToken()); // 처음 해킹된 컴퓨터

			// 의존 정보를 입력받을 배열
			edges = new ArrayList[V + 1];
			for (int i = 0; i < V + 1; i++) {
				edges[i] = new ArrayList<Edge>();
			}

			// dist값 초기화
			dist = new int[V + 1];
			Arrays.fill(dist, Integer.MAX_VALUE);

			// 시작노드값 초기화
			dist[start] = 0;

			// 감염된 컴퓨터 수(시작 컴퓨터 포함)
			cnt = 1;

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				edges[from].add(new Edge(to, cost));
			} // End of input

			int time = hacking(start);

			System.out.println(cnt + " " + time);
		}
		br.close();

	}

	private static int hacking(int start) {
		int ret = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

		// 시작점 삽입
		pq.offer(new Edge(start, dist[start]));

		while (!pq.isEmpty()) {
			Edge from = pq.poll();

			// 현재의 가중치가 기존의 가중치보다 더 큰경우 제외
			if (from.cost > dist[from.idx]) {
				continue;
			}

			// dist 최댓값
			ret = Math.max(ret, from.cost);

			// 연결된 간선에 대한 탐색
			for (Edge to : edges[from.idx]) {

				// 연결된 곳의 기존 가중치 > 현재의 가중치 + 연결된곳까지의 가중치
				if (dist[to.idx] > dist[from.idx] + to.cost) {

					// 처음 해킹당하는 컴퓨터 체크
					if (dist[to.idx] == Integer.MAX_VALUE) {
						cnt++;
					}
					dist[to.idx] = dist[from.idx] + to.cost;
					pq.offer(new Edge(to.idx, dist[to.idx]));
				}
			}
		}
		return ret;

	} // End of hacking

}

class Edge implements Comparable<Edge> {
	int idx;
	int cost;

	public Edge(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge e) {
		return this.cost - e.cost; // 오름차순 정렬
	}

}
