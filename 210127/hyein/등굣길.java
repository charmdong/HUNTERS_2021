class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] S = new int[m+1][n+1];
        boolean[][] puddle = new boolean[m+1][n+1];
        S[1][1] = 1;
        
        for(int[] p : puddles) {
            puddle[p[0]][p[1]] = true;
        }
        
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(i==1 && j==1) continue; 
                if(puddle[i][j]) continue; // 물에 잠겨서 갈 수 없으므로 패스
                if(i==1) S[i][j] = S[i][j-1]; // 왼쪽은 범위 밖이므로 위의 값만 가져옴
                else if(j==1) S[i][j] = S[i-1][j]; // 위쪽은 범위 밖이므로 왼쪽 값만 가져옴
                else 
                    S[i][j] = (S[i-1][j] + S[i][j-1]) % 1000000007; 
                // 왼쪽 + 위쪽에서 온 경우가 집에서부터 i,j로 올 수 있는 경우의 수
            }
        }
        
        return S[m][n];
    }
}