import java.util.Arrays;
import java.util.Comparator;

class PGM42861_섬연결하기 {
    static int[] P;

    public int connect(int child){
        if(P[child] == child)
            return child;
        else
            return P[child] = connect(P[child]);
    }

    public int solution(int n, int[][] costs) {
        //int[] land = new int[n];
        boolean[] visit = new boolean[n+1];

        Arrays.sort(costs, 0, costs.length, new  Comparator<int[]>(){

            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[2]-arr2[2];
            }

        } );

        int total =0;
        P = new int[n];
        for(int i=0;i<n;i++)
            P[i] =i;

        for(int i=0;i<costs.length;i++){
            int x = connect(costs[i][0]);
            int y = connect(costs[i][1]);
            if(x != y){
                P[y] = x;
                total += costs[i][2];
            }
        }




        return total;
    }
}