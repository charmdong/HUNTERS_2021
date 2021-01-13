/**
 * @author : donggun.chung
 * @date : 2021. 01. 11
 * @site : BOJ 
 * @probInfo : 1094 막대기
 * @time : 0ms
 * @memory : 2016KB
 */ 

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int solution(int n);

int main()
{
    ios_base ::sync_with_stdio(false);
    
    cin.tie(NULL);
    cout.tie(NULL);

    int n;
    cin >> n;
    
    cout << solution(n) << endl;

    return 0;
}

int solution(int n) {
    vector<int> stick;
    stick.push_back(64);

    while(true) {
        int total = 0;
        for(int t: stick) {
            total += t;
        }

        if(total <= n) break;

        sort(stick.begin(), stick.end());

        stick[0] /= 2;
        if((total - stick[0]) < n) {
            stick.push_back(stick[0]);
        }
    }

    return stick.size();
}