package Hunters_210113_20;

import java.util.PriorityQueue;

public class PG_섬연결하기 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
		int n = 4;
		System.out.println(s.solution(n, costs));
	}

}

class Solution {

	static PriorityQueue<Edge> pq;
	static int[] parent;
	static int n;

	public int solution(int n, int[][] costs) {
		int answer = 0;

		pq = new PriorityQueue<Edge>();
		int len = costs.length;
		this.n = n;

		// map 정보
		for (int i = 0; i < len; i++) {
			pq.offer(new Edge(costs[i][0], costs[i][1], costs[i][2]));
		}

		makeParent(n);
		answer = kruscal();

		return answer;
	}

	private int kruscal() {

		int minCost = 0;

		while (!pq.isEmpty()) {
			Edge temp = pq.poll();

			int a = find(temp.from);
			int b = find(temp.to);

			// 싸이클이 생기는 경우
			if (a == b) {
				continue;
			}

			union(a, b);
			minCost += temp.cost;

		}

		return minCost;
	}

	// 부모 노드 삽입
	private void makeParent(int n) {
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}

	// 최상위 노드 탐색
	private int find(int a) {
		if (parent[a] == a) {
			return a;
		}

		return parent[a] = find(parent[a]);
	}

	// 최상위 노드 병합
	private void union(int a, int b) {
		// 최상위 노드 탐색
		int aRoot = find(a);
		int bRoot = find(b);

		// 최상위가 같지 않은 경우
		if (aRoot != bRoot) {
			parent[aRoot] = bRoot;
		} else {
			return;
		}
	}
}

class Edge implements Comparable<Edge> {
	int from;
	int to;
	int cost;

	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}

}