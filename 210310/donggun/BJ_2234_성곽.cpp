/**
 * @author : donggun.chung
 * @date : 2021. 03. 08
 * @site : BOJ
 * @probInfo : 2234 성곽 
 * @time : 128ms
 * @memory : 2156KB
 */ 

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<vector<int> > board;
vector<vector<bool> > visited;
int n, m;
int dy[4] = {0, -1, 0, 1};
int dx[4] = {-1, 0, 1, 0};

int bfs(int y, int x);
bool isInBoard(int y, int x);
int bfs(vector<vector<int> > board, int y, int x);
int removeWall(int y, int x);

int main()
{
    ios_base ::sync_with_stdio(false);
    
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> m >> n;

    board.assign(n, vector<int>(m, 0));
    visited.assign(n, vector<bool>(m, false));

    for(int row = 0; row < n; row++) {
        for(int col = 0; col < m; col++) {
            cin >> board[row][col];
        }
    }

    // 1. 성에 있는 방의 개수, 2. 가장 넓은 방의 넓이
    int roomCnt = 0;
    int maxArea = 0;
    int maxRoom = 0;
    for(int row = 0; row < n; row++) {
        for(int col = 0; col < m; col++) {
            if(!visited[row][col]) {
                roomCnt++;

                int res = bfs(row, col);
                if(res > maxRoom) {
                    maxRoom = res;
                }
            }
        }
    }

    // 3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
    for(int row = 0; row < n; row++) {
        for(int col = 0; col < m; col++) {
            int res = removeWall(row, col); // 이 위치에서 벽을 부쉈을 때 최대값

            if(maxArea < res) {
                maxArea = res;
            }
        }
    }

    cout << roomCnt << endl;
    cout << maxRoom << endl;
    cout << maxArea << endl;

    return 0;
}

int bfs(int y, int x) {
    queue<pair<int, int> > q;
    int area = 0;
    visited[y][x] = true;
    q.push(make_pair(y, x));

    while(!q.empty()) {
        pair<int, int> now = q.front();
        q.pop();

        area++;

        for(int dir = 0; dir < 4; dir++) {
            // 갈 수 있는 방향
            int tmp = board[now.first][now.second] & (1 << dir);
            if(tmp == 0) {
                int ny = now.first + dy[dir];
                int nx = now.second + dx[dir];

                if(isInBoard(ny, nx) && !visited[ny][nx]) {
                    q.push(make_pair(ny, nx));
                    visited[ny][nx] = true;
                }
            }
        }
    }

    return area;
}

int bfs(vector<vector<int> > board, int y, int x) {
    int answer = 0;
    visited.assign(n, vector<bool>(m, false));

    queue<pair<int, int> > q;

    visited[y][x] = true;
    q.push(make_pair(y, x));

    while(!q.empty()) {
        pair<int, int> now = q.front();
        q.pop();

        answer++;

        for(int dir = 0; dir < 4; dir++) {
            if((board[now.first][now.second] & (1 << dir)) == 0) {
                int ny = now.first + dy[dir];
                int nx = now.second + dx[dir];

                if(isInBoard(ny, nx) && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.push(make_pair(ny, nx));
                }
            }
        }
    }

    return answer;
}

int removeWall(int row, int col) {
	int answer = 0;
	vector<vector<int> > tmp = board;
	
	for (int dir = 0; dir < 4; dir++) {
		if ((board[row][col] & (1 << dir)) != 0) {
			int ny = row + dy[dir];
			int nx = col + dx[dir];

			if (ny > -1 && ny < n && nx > -1 && nx < m) {
				int before = tmp[row][col];
				tmp[row][col] ^= (1 << dir); // 벽 부수기

				int res = bfs(tmp, row, col);
				if (answer < res) {
					answer = res;
				}
				tmp[row][col] = before;
			}
		}
	}

	return answer;
}

bool isInBoard(int y, int x) {
    return (y > -1 && y < n && x > -1 && x < m);
}
