
public class PGM43105_정수삼각형 {

	class Solution {
	    public int solution(int[][] triangle) {
	        int answer = 0;
	        int N = triangle.length;
	        
	        for(int i=N-1;i>0;i--){
	            for(int j=1;j<triangle[i].length;j++){
	                triangle[i-1][j-1] += Integer.max(triangle[i][j-1],triangle[i][j]);
	            }
	        }
	        return triangle[0][0];
	    }
	}
	
}
