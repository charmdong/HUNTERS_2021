#include <iostream>
#include <vector>

using namespace std;

int N, M, direction, answer;
int dy[4] = {-1, 0, 1, 0};
int dx[4] = {0, 1, 0, -1};
pair<int, int> robot;
vector<vector<int> > board;
vector<vector<bool> > visited;

void solution(int y, int x, int d, int originDir);
void stepTwo(int y, int x, int d, int originDir);
bool isInBoard(int y, int x);

int main()
{
    ios_base ::sync_with_stdio(false);
    
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;

    board.assign(N, vector<int>(M, 0));
    visited.assign(N, vector<bool>(M, false));

    cin >> robot.first >> robot.second >> direction;
    
    for(int row = 0; row < N; row++) {
        for(int col = 0; col < M; col++) {
            cin >> board[row][col];
        }
    }
    
    solution(robot.first, robot.second, direction, direction);

    cout << answer << endl;

    return 0;
}

void solution(int y, int x, int d, int originDir) {
    // 1. 현재 위치를 청소한다.
    visited[y][x] = true;
    answer++;

    // 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
    stepTwo(y, x, d, originDir);
}

void stepTwo(int y, int x, int d, int originDir) {
    // 왼쪽 방향
    int leftDir = (d + 3) % 4;

    // 왼쪽 방향으로 한칸 간 위치
    int ny = y + dy[leftDir];
    int nx = x + dx[leftDir];

    // a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
    if(isInBoard(ny, nx) && board[ny][nx] == 0 && !visited[ny][nx]) {
        solution(ny, nx, leftDir, leftDir);
    }

    else if(!isInBoard(ny, nx) || board[ny][nx] == 1 || visited[ny][nx]) {
        if(leftDir == originDir) {
            // c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
            int backDir = (originDir + 2) % 4;
            int backY = y + dy[backDir];
            int backX = x + dx[backDir];

            if(isInBoard(backY, backX) && board[backY][backX] == 0) {
                stepTwo(backY, backX, originDir, originDir);
            }
            // d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
            else {
                return;
            }
        }  
        else {
            // b. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
            stepTwo(y, x, leftDir, originDir);
        } 
    }
}

bool isInBoard(int y, int x) {
    return (y >= 0 && y < N && x >= 0 && x < M);
}