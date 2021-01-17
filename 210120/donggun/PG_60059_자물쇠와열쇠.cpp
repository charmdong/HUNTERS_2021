/**
 * @author : donggun.chung
 * @date : 2021. 01. 17
 * @site : Programmers
 * @probInfo : 60059 자물쇠와 열쇠
 * @time : 
 * @memory : 
 */ 

#include <string>
#include <vector>
#include <iostream>

using namespace std;

int N, M, len;

void turnKey(vector<vector<int> >& key);
bool checkKey(int sy, int sx, vector<vector<int> > key, vector<vector<int> > board);
bool isUnlock();

bool solution(vector<vector<int> > key, vector<vector<int> > lock) {
    vector<vector<int> > board;

    M = key.size();
    N = lock.size();
    len = N + 2 * M - 2;
    
    board.assign(len, vector<int>(len, 0));
    
    // 키 위치 등록
    for(int row = M - 1; row < N + M - 1; row++) {
        for(int col = M - 1; col < N + M - 1; col++) {
            board[row][col] = lock[row - M + 1][col - M + 1];
        }
    }
    
    for(int dir = 0; dir < 4; dir++) {
        for(int row = 0; row < N + M - 1; row++) {
            for(int col = 0; col < N + M - 1; col++) {
                if(checkKey(row, col, key, board)) {
                    return true;
                }
            }
        }

        turnKey(key);
    }
    
    return false;
}

bool checkKey(int sy, int sx, vector<vector<int> > key, vector<vector<int> > board) {
    for(int row = sy; row < sy + M; row++) {
        for(int col = sx; col < sx + M; col++) {
            if(board[row][col] == 1 && key[row - sy][col - sx] == 1) return false;
            board[row][col] += key[row - sy][col - sx];
        }
    }

    for(int row = M - 1; row < N + M - 1; row++) {
        for(int col = M - 1; col < N + M - 1; col++) {
            if(board[row][col] != 1) return false;
        }
    }

    return true;
}

void turnKey(vector<vector<int> >& key) {    
    vector<vector<int> > tmp(M, vector<int>(M ,0));
    
    for(int row = 0; row < M; row++) {
        for(int col = 0; col < M; col++) {
            tmp[row][col] = key[M - 1 - col][row];
        }
    }
    
    key = tmp;
}

