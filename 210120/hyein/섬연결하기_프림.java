import java.util.*;
class Solution {

    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        int cost[][] = new int[n][n];
        for(int i=0;i<costs.length;i++){
            cost[costs[i][0]][costs[i][1]] = costs[i][2];
            cost[costs[i][1]][costs[i][0]] = costs[i][2];
        }
        
        // MST 이용해서 모든 점 방문해서 최소 비용 구하기
        // 0번 점에서 시작해서 최소 비용 배열 갱신해 나가기
        int idx = 0;
        int[] min = new int[n];
        Arrays.fill(min,987654321);
        boolean visited[] = new boolean[n];
        visited[idx] = true;
        min[idx] = 0;
        
        while(true) {
            System.out.println("IDX : "+idx);
            // 모든 섬 방문했으면 종료
            boolean fin = true;
            for(int i=0;i<n;i++){
                if(!visited[i]) fin = false;
            }
            if(fin) break;
            
            // min배열 갱신
            for(int i=0;i<n;i++){
                if(cost[i][idx] == 0) continue;
                if(min[i] > cost[i][idx]) min[i] = cost[i][idx];
            }
            
            // 아직 선택되지 않은 것 중 최소값인 애가 다음 idx
            int next = 987654321;
            int nextIdx = idx;
            for(int i=0;i<n;i++){
                if(visited[i]) continue;
                if(min[i] < next) {
                    next = min[i];
                    nextIdx = i;
                }
            }
            idx = nextIdx;
            visited[idx] = true;
	// 이슈사항 : 여기서 answer를 바로 더해야함
	// while문이 끝난 후 min배열을 돌면서 더하면 틀림
            answer += min[idx];
            System.out.println(Arrays.toString(min));
        }
        
        
        return answer;
    }
}