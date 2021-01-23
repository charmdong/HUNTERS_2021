/**
 * @author : donggun.chung
 * @date : 2021. 01. 24
 * @site : Programmers
 * @probInfo : 12980
 * @time : 
 * @memory : 
 */ 

#include <iostream>
using namespace std;

int solution(int n)
{
    int answer = 0;
    
    while(n > 1) {
        if(n % 2 == 0) {
            n /= 2;
        } else {
            answer++;
            n -= 1;
        }
    }
    
    return answer + 1;
}