#include <string>
#include <vector>
#include <math.h>

using namespace std;

pair<int, int> pos[10] = {{4, 2}
                     ,{1, 1}, {1, 2}, {1, 3}
                     ,{2, 1}, {2, 2}, {2, 3}
                     ,{3, 1}, {3, 2}, {3, 3}};

string solution(vector<int> numbers, string hand) {
    string answer = "";
    pair<int, int> left = make_pair(4, 1);
    pair<int, int> right = make_pair(4, 3);
    
    for(int number: numbers) {
        // 1, 4, 7
        if(number % 3 == 1) {
            answer += "L";
            left = pos[number];
        }
        // 3, 6, 9
        else if(number != 0 && number % 3 == 0) {
            answer += "R";
            right = pos[number];
        }
        // 2, 5, 8, 0
        else {
            pair<int, int> cur = pos[number];
            
            int leftDiff = abs(left.first - cur.first) + abs(left.second - cur.second);
            int rightDiff = abs(right.first - cur.first) + abs(right.second - cur.second);

            if(leftDiff < rightDiff) {
                answer += "L";
                left = cur;
            }
            else if(rightDiff < leftDiff) {
                answer += "R";
                right = cur;
            }
            else {
                if(hand == "left") {
                    answer += "L";
                    left = cur;
                }
                else {
                    answer += "R";
                    right = cur;
                }
            }
        }
    }
    
    return answer;
}