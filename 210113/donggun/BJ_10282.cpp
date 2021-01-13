/**
 * @author : donggun.chung
 * @date : 2021. 01. 06
 * @site : BOJ
 * @probInfo : 10282 해킹
 * @time : 128ms
 * @memory : 5776KB
 */ 

#include <iostream>
#include <vector>
#include <queue>
#define MAX 10000001

using namespace std;

struct compare {
    bool operator()(const pair<int, int>& p1, const pair<int, int>& p2) {
        return p1.second > p2.second;
    }
};

int T, n, d, start;
vector<vector<pair<int, int> > > graph;
vector<bool> visited;
vector<int> dist;

void solution();

int main()
{
    ios_base ::sync_with_stdio(false);
    
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> T;
    for(int test = 1; test <= T; test++) {
        cin >> n >> d >> start;

        graph.assign(n + 1, vector<pair<int, int> >());
        visited.assign(n + 1, false);
        dist.assign(n + 1, MAX);

        int a, b, s;
        for(int index = 0; index < d; index++) {
            cin >> a >> b >> s;
            graph[b].push_back(make_pair(a, s));
        }

        solution();
    }

    return 0;
}

void solution() {
    priority_queue<pair<int, int>, vector<pair<int, int> >, compare> q;
    int totalCnt = 0;
    int totalDist = 0;

    dist[start] = 0;
    q.push(make_pair(start, dist[start]));

    while(!q.empty()) {
        int curIndex = q.top().first;
        int curDist = q.top().second;
        q.pop();

        if(visited[curIndex]) continue;

        for(int index = 0; index < graph[curIndex].size(); index++) {
            int nextIndex = graph[curIndex][index].first;
            int nextDist = graph[curIndex][index].second;

            if(!visited[nextIndex] && dist[nextIndex] > dist[curIndex] + nextDist) {
                dist[nextIndex] = dist[curIndex] + nextDist;
                q.push(make_pair(nextIndex, dist[nextIndex]));
            }
        }

        visited[curIndex] = true;
        totalCnt++;
        // 마지막 컴퓨터가 감염될 때까지의 시간을 구하는 것이기 때문에 현재 컴퓨터가 감염된 시간을 갱신해줘야 한다.
        totalDist = curDist;
    }

    cout << totalCnt << " " << totalDist << endl;
}