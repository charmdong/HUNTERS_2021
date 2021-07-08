/**
 * @author : donggun.chung
 * @date : 2021. 01. 20
 * @site : Programmers
 * @probInfo : 42898
 * @time : 
 * @memory : 
 */ 

#include <vector>
#define DIV 1000000007
using namespace std;

vector<vector<int> > board, dp;
int solution(int m, int n, vector<vector<int> > puddles);

int solution(int m, int n, vector<vector<int> > puddles) {
    board.assign(n + 1, vector<int>(m + 1, 1));
    dp.assign(n + 1, vector<int>(m + 1, 0));

    for(vector<int> puddle : puddles) {
        int col = puddle[0];
        int row = puddle[1];

        board[row][col] = 0;
    }

    dp[1][1] = 1;
    for(int row = 1; row <= n; row++) {
        for(int col = 1; col <= m; col++) {
            if(row == 1 && col == 1) {
                continue;
            }
            
            if(board[row][col] == 0) {
                dp[row][col] = 0;
            } 
            else {
                dp[row][col] = (dp[row][col - 1] + dp[row - 1][col]) % DIV;    
            }
        }
    }

    return dp[n][m];
}