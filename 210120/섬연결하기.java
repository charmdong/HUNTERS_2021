import java.util.*;
class Solution {
    static int V,E,parent[];
	static ArrayList<Dot> line;
	
	static class Dot implements Comparable<Dot> {
		int a;
		int b;
		int value;
		public Dot(int a, int b, int value) {
			this.a=a;
			this.b=b;
			this.value=value;
		}
		@Override
		public String toString() {
			return "Dot [a=" + a + ", b=" + b + ", value=" + value + "]";
		}
		
		@Override
		public int compareTo(Dot o) {
			if(this.value<o.value) return -1;
			else if(this.value==o.value) return 0;
			return 1;
		}
		
	}
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        line = new ArrayList<>();
        for(int i=0;i<costs.length;i++){
            line.add(new Dot(costs[i][0], costs[i][1], costs[i][2]));
        }
        
        Collections.sort(line);
        
        parent = new int[n];
        Arrays.fill(parent, -1);
        
        int N=0;
        for(int i=0;i<costs.length;i++){
            Dot dot = line.get(i);
            int p1 = union_find(dot.a);
            int p2 = union_find(dot.b);
            if(p1 != p2) {
				// 간선 선택
				answer+=dot.value;
				parent[p2] = p1;
				n++;
			}
			if(N==n-1) break;
        }
        
        return answer;
    }
    
    	private static int union_find(int node) {
		if(parent[node]==-1) return node;
		return parent[node] = union_find(parent[node]);
	}

}