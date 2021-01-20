class Solution {
    static int height;
    static int[][] memo;
    public int solution(int[][] triangle) {
        int answer = 0;
        
        height = triangle.length;
        memo = new int[height][height];
        answer = DP(0,0,triangle);
        
        return answer;
    }
    
    public int DP(int i, int j, int[][] triangle) {
        if(memo[i][j] != 0) return memo[i][j];
        else if(i+1 < height && j+1 < height)
            memo[i][j] = Integer.max(DP(i+1, j, triangle), DP(i+1, j+1, triangle)) + triangle[i][j];
        else memo[i][j] = triangle[i][j];
        return memo[i][j];
    }
}