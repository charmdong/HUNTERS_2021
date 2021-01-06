/**
 * @author : donggun.chung
 * @date : 2021. 01. 02
 * @site : BOJ
 * @probInfo : 1759 암호 만들기
 * @time : 4ms
 * @memory : 2016KB
 */ 

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int L, C;
vector<char> pw;

void solution(int cur = 0, string candidate = "");
bool checkVal(string candidate);

int main()
{
    ios_base ::sync_with_stdio(false);
    
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> L >> C;
    pw.assign(C, '\0');

    for(int index = 0; index < C; index++) {
        cin >> pw[index];
    }

    sort(pw.begin(), pw.end()); // 알파벳이 암호에서 증가하는 순서로 배열되었을 것이라고 추측된다.
    solution();

    return 0;
}

void solution(int cur, string candidate) {
    if(candidate.length() == L) {
        if(checkVal(candidate)) {
            cout << candidate << endl;
            return;
        }
    }

    for(int index = cur; index < C; index++) {
        solution(index + 1, candidate + pw[index]);
    }
}

bool checkVal(string candidate) {
    int vowel = 0;
    int cons = 0;

    for(int index = 0; index < L; index++) {
        if (candidate[index] == 'a' ||
                candidate[index] == 'e' ||
                candidate[index] == 'i' ||
                candidate[index] == 'o' ||
                candidate[index] == 'u')
        {
            vowel++;
        } else {
            cons++;
        }

        if(vowel >= 1 && cons >= 2) {
            return true;
        }
    }

    return false;
}