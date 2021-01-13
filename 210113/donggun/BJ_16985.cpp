/**
 * @author : donggun.chung
 * @date : 2021. 01. 09
 * @site : BOJ
 * @probInfo : 16985 Maaaaaaaaze 
 * @time : 16ms
 * @memory : 2024KB
 */ 

#include <iostream>
#include <vector>
#include <queue>
#include <string.h>
#include <algorithm>

using namespace std;

struct position {
    int z;
    int y;
    int x;
    int dist;

    position(int z, int y, int x, int dist = 0): z(z), y(y), x(x), dist(dist) {}
};

const int MAX = 987654321;
const int MIN = 12;
int answer = MAX;
vector<vector<vector<int> > > board(5, vector<vector<int> >(5, vector<int>(5, 0)));
vector<vector<vector<int> > > origin(5, vector<vector<int> >(5, vector<int>(5, 0)));
bool visited[5][5][5];
int dz[6] = {0, 0, 0, 0, -1, 1};
int dy[6] = {0, -1, 0, 1, 0, 0};
int dx[6] = {1, 0, -1, 0, 0, 0};

void solution(vector<int>& seq);
void solution();
void rotateBoard(int layer, int rotate);
void rotateBoard(int layer);
int getMoveCnt();
bool isInBoard(int z, int y, int x);

int main()
{
    ios_base ::sync_with_stdio(false);
    
    cin.tie(NULL);
    cout.tie(NULL);

    // 판 상태 정보 입력
    for(int layer = 0; layer < 5; layer++) {
        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 5; col++) {
                cin >> origin[layer][row][col];
            }
        }
    }

    vector<int> seq;
    vector<int> permutation; // 순열
    for(int index = 0; index < 5; index++) {
        permutation.push_back(index);
    }

    // 판의 위치 
    while(true) {
        for(int index = 0; index < 5; index++) {
            // 판 복사
            board[index] = origin[permutation[index]];
        }
        // solution(seq);
        solution();
        if(answer == MIN) break;

        if(!next_permutation(permutation.begin(), permutation.end())) {
            break;
        }
    }

    if(answer == MAX) {
        cout << -1 << endl;
    } else {
        cout << answer << endl;
    }

    return 0;
}

void solution() {
    for(int dir1 = 0; dir1 < 4; dir1++) {
        rotateBoard(0);
        if(!board[0][0][0]) continue;

        for(int dir2 = 0; dir2 < 4; dir2++) {
            rotateBoard(1);
            for(int dir3 = 0; dir3 < 4; dir3++) {
                rotateBoard(2);
                for(int dir4 = 0; dir4 < 4; dir4++) {
                    rotateBoard(3);
                    for(int dir5 = 0; dir5 < 4; dir5++) {
                        rotateBoard(4);
                        
                        if(board[4][4][4]) {
                            int res = getMoveCnt();
                            answer = min(answer, res);
                            if(answer == MIN) return;
                        }
                    }
                }
            }
        }
    }
}

void rotateBoard(int layer) {
    int tmp[5][5];
    
    for(int row = 0; row < 5; row++) {
        for(int col = 0; col < 5; col++) {
            tmp[row][col] = board[layer][4 - col][row];
        }
    }

    for(int row = 0; row < 5; row++) {
        for(int col = 0; col < 5; col++) {
            board[layer][row][col] = tmp[row][col];
        }
    }
}

int getMoveCnt() {
    // 시작점 혹은 도착점이 갈 수 없는 곳인 경우
    if(board[0][0][0] == 0 || board[4][4][4] == 0) return MAX;

    queue<position> q;
    memset(visited, false, sizeof(visited));

    visited[0][0][0] = true;
    q.push(position(0, 0, 0, 0));

    while(!q.empty()) {
        position cur = q.front();
        q.pop();
        // 현재 위치가 도착지점인 경우
        if(cur.z == 4 && cur.y == 4 && cur.x == 4) {
            return cur.dist;
        }

        for(int dir = 0; dir < 6; dir++) {
            int nz = cur.z + dz[dir];
            int ny = cur.y + dy[dir];
            int nx = cur.x + dx[dir];
            // 다음 위치가 갈 수 있는 공간인 경우
            if(isInBoard(nz, ny, nx) && !visited[nz][ny][nx] && board[nz][ny][nx] == 1) {
                visited[nz][ny][nx] = true;
                q.push(position(nz, ny, nx, cur.dist + 1));
            }
        }
    }
    // 도착 지점에 도달하지 못한 경우
    return MAX;
}

bool isInBoard(int z, int y, int x) {
    return (z > -1 && z < 5 && y > -1 && y < 5 && x > -1 && x < 5);
}
