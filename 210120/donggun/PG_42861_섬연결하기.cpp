/**
 * @author : donggun.chung
 * @date : 2021. 01. 14
 * @site : Programmers
 * @probInfo : 42861 섬 연결하기
 * @time : 
 * @memory : 
 */ 

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> parent;

int solution(int n, vector<vector<int> > costs);
int findParent(int index);
bool unionSet(int a, int b);
bool compare(vector<int>& v1, vector<int>& v2) {
    return v1[2] < v2[2];
}

int main()
{
    ios_base ::sync_with_stdio(false);
    
    cin.tie(NULL);
    cout.tie(NULL);

    

    return 0;
}

int solution(int n, vector<vector<int> > costs) {
    int answer = 0;

    parent.assign(n, 0);
    for(int index = 0; index < n; index++) {
        parent[index] = index;
    }

    sort(costs.begin(), costs.end(), compare);
    int edgeCnt = 0;

    for(vector<int> cost: costs) {
        if(unionSet(cost[0], cost[1])) {
            answer += cost[2];
            if(++edgeCnt == n - 1) break;
        }
    }
    cout << answer << endl;
    return answer;
}

int findParent(int index) {
    if(parent[index] == index) return index;
    else return parent[index] = findParent(parent[index]);
}

bool unionSet(int a, int b) {
    int pa = findParent(a);
    int pb = findParent(b);

    if(pa != pb) {
        parent[pa] = pb;
        return true;
    }
    
    return false;
}