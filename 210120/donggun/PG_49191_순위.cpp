/**
 * @author : donggun.chung
 * @date : 2021. 01. 13
 * @site : Programmers
 * @probInfo : 49191 순위
 * @time : 
 * @memory : 
 */ 

#include <iostream>
#include <vector>
#define WIN 0
#define LOSE 1
using namespace std;

bool match[101][101];

int solution(int n, vector<vector<int> > result);

int solution(int n, vector<vector<int> > results) {
    int answer = 0;

    for(vector<int> result: results) {
        int winner = result[WIN];
        int loser = result[LOSE];

        match[winner][loser] = true;
    }

    // 승패 관계 정리 match[승자][패자] second가 가운데 낀 애임.
    for(int second = 1; second <= n; second++) {
        for(int first = 1; first <= n; first++) {
            for(int third = 1; third <= n; third++) {
                if(match[first][second] && match[second][third]) {
                    match[first][third] = true;
                }
            }
        }
    }

    for(int player1 = 1; player1 <= n; player1++) {
        int winCnt = 0;

        for(int player2 = 1; player2 <= n; player2++) {
            // player1의 승패 조사 : player1이 이긴 경우 || player1이 진 경우
            if(match[player1][player2] || match[player2][player1]) {
                winCnt++;
            }
        }

        if(winCnt == n - 1) {
            answer++;
        }
    }


    return answer;
}