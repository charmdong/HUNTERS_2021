/**
 * @author : donggun.chung
 * @date : 2021. 01. 24
 * @site : Programmers
 * @probInfo : 60057 문자열 압축
 * @time : 
 * @memory : 
 */ 

#include <string>
#include <vector>

using namespace std;

int solution(string str) {
    int answer = str.length();
    int strLen = str.length();

    if(strLen == 1) return 1;

    for(int split = 1; split <= strLen / 2; split++) {
        int cnt = 1;
        string res = "";
        string prev = str.substr(0, split);

        for(int index = split; index < strLen; index += split) {
            string current = str.substr(index, split);

            if(prev == current) {
                cnt++;
            }
            else {
                if(cnt > 1) {
                    res += to_string(cnt);
                }
                res += prev;
                prev = current;
                cnt = 1;
            }

            if(index + split >= strLen) {
                res += (cnt > 1) ? (to_string(cnt) + prev) : current;
            }
        }

        if(answer > res.length()) {
            answer = res.length();
        }
    }

    return answer;
}