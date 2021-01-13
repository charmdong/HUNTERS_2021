/**
 * @author : donggun.chung
 * @date : 2021. 01. 08
 * @site : BOJ
 * @probInfo : 2056 작업 
 * @time : 72ms
 * @memory : 5980KB
 */ 

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N;
vector<int> degree, work, res;
vector<vector<int> > graph;

int solution();

int main()
{
    ios_base ::sync_with_stdio(false);
    
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    graph.assign(N + 1, vector<int>());
    degree.assign(N + 1, 0);
    work.assign(N + 1, 0);
    res.assign(N + 1, 0);

    int adCnt;
    for(int index = 1; index <= N; index++) {
        cin >> work[index] >> adCnt;

        int node;
        for(int iter = 0; iter < adCnt; iter++) {
            cin >> node;
            degree[index]++;
            graph[node].push_back(index);
        } 
    }

    cout << solution() << endl;

    return 0;
}

int solution() {
    int answer = 0;
    queue<int> q;

    for(int index = 1; index <= N; index++) {
        if(degree[index] <= 0) {
            q.push(index);
            res[index] = work[index];
        }
    }

    while(!q.empty()) {
        int cur = q.front();
        q.pop();

        for(int next: graph[cur]) {
            res[next] = max(res[next], res[cur] + work[next]);
            if(--degree[next] <= 0) {
                q.push(next);
            }
        }
    }

    for(int t: res) {
        answer = max(answer, t);
    }

    return answer;
}