/**
 * @author : donggun.chung
 * @date : 2021. 01. 02
 * @site : BOJ
 * @probInfo : 3055 탈출 
 * @time : 0ms
 * @memory : 2156KB
 */ 

#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int dy[4] = {-1, 0, 1, 0};
int dx[4] = {0, 1, 0, -1};

int R, C;
char board[50][50];
bool visited[50][50];
queue<pair<int, int> > water, src;

string solution();
bool isInBoard(int y, int x);

int main()
{
    ios_base ::sync_with_stdio(false);
    
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> R >> C;
    for(int row = 0; row < R; row++) {
        for(int col = 0; col < C; col++) {
            cin >> board[row][col];
            
            if(board[row][col] == '*') {
                water.push(make_pair(row, col));
            }

            if(board[row][col] == 'S') {
                src.push(make_pair(row, col));
            }
        }
    }

    cout << solution() << endl;

    return 0;
}

string solution() {
    int answer = 0;
    bool isDes = false;

    while(true) {
        // 물 이동
        int qSize = water.size();
        for(int iter = 0; iter < qSize; iter++) {
            pair<int, int> curWater = water.front();
            water.pop();

            visited[curWater.first][curWater.second] = true;

            for(int dir = 0; dir < 4; dir++) {
                int ny = curWater.first + dy[dir];
                int nx = curWater.second + dx[dir];
                
                if(isInBoard(ny, nx) && !visited[ny][nx]) {
                    if(board[ny][nx] == '.' || board[ny][nx] == 'S') {
                        water.push(make_pair(ny, nx));
                        visited[ny][nx] = true;
                        board[ny][nx] = '*';
                    }
                }
            }
        }

        qSize = src.size();
        for(int iter = 0; iter < qSize; iter++) {
            pair<int, int> curSrc = src.front();
            src.pop();

            if(board[curSrc.first][curSrc.second] == 'D') {
                isDes = true;
                break;
            }

            visited[curSrc.first][curSrc.second] = true;

            for(int dir = 0; dir < 4; dir++) {
                int ny = curSrc.first + dy[dir];
                int nx = curSrc.second + dx[dir];

                if(isInBoard(ny, nx) && !visited[ny][nx]) {
                    if(board[ny][nx] == '.' || board[ny][nx] == 'D') {
                        src.push(make_pair(ny, nx));
                        visited[ny][nx] = true;
                    }
                }
            }
        }

        if(isDes) {
            break;
        }
        answer++;
        if(src.size() <= 0) break;
    }

    return isDes ? to_string(answer) : "KAKTUS";
}

bool isInBoard(int y, int x) {
    return (y >= 0 && y < R && x >= 0 && x < C);
}