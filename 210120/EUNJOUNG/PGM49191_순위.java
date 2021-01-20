import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int max = 987654321;
        int[][] game = new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                game[i][j] = max;
            }
        }
        
        for(int i=0;i<results.length;i++){
            game[results[i][0]][results[i][1]] = 1;
        }
        
        for(int m=1;m <= n;m++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    game[i][j] = Integer.min(game[i][j], game[i][m] + game[m][j]);
                }
            }
        }
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(game[i][j] + " ");
            }System.out.println("");
        }
        
        boolean[] check = new boolean[n+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i==j) continue;
                if(game[i][j] == max && game[j][i] == max){
                    check[i] = true;
                    System.out.println(i+","+j);
                    break;
                }
            } 
        }
        
        for(int i=0;i<=n;i++){
            if(check[i]) answer++;
        }
        
        return n-answer;
    }
}