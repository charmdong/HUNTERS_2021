/**
 * @author : donggun.chung
 * @date : 2021. 01. 02
 * @site : BOJ
 * @probInfo : 12762 롤러코스터 
 * @time : 388ms
 * @memory : 16812KB
 */ 

#include <iostream>
#include <queue>
#include <string>
#include <map>

using namespace std;

int dy[4] = {-1, 0, 1, 0};
int dx[4] = {0, 1, 0, -1};
string board;
map<string, int> dist;

int solution();
bool isInBoard(int y, int x);

int main()
{
    ios_base ::sync_with_stdio(false);
    
    cin.tie(NULL);
    cout.tie(NULL);

    char ch;
    for(int row = 0; row < 3; row++) {
        for(int col = 0; col < 3; col++) {
            cin >> ch;
            board += ch;
        }
    }

    cout << solution() << endl;

    return 0;
}

int solution() {
    queue<string> q;
    
    q.push(board);
    dist[board] = 0;

    while(!q.empty()) {
        string curStr = q.front();
        q.pop();

        // 문자열을 3 * 3 배열의 인덱스로 매핑
        int zIndex = curStr.find('0');
        int y = zIndex / 3;
        int x = zIndex % 3;

        // 0과 인접한 얘들과 swap
        for(int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if(isInBoard(ny, nx)) {
                string nextStr = curStr;
                swap(nextStr[y * 3 + x], nextStr[ny * 3 + nx]);

                if(dist.count(nextStr) == 0) { // 방문한 적이 없는 경우. 
                    dist[nextStr] = dist[curStr] + 1;
                    q.push(nextStr);
                }
            }
        }
    }

    if(dist.count("123456780") == 0) {
        return -1;
    } else {
        return dist["123456780"];
    }
}

bool isInBoard(int y, int x) {
    return (y > -1 && y < 3 && x > -1 && x < 3);
}