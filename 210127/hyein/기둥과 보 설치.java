import java.util.*;
class Solution {
    static int N;
    static class Ans implements Comparable<Ans> {
        int x,y,a;
        public Ans (int x, int y, int a) {
            this.x = x;
            this.y = y;
            this.a = a;
        }
        @Override
        public boolean equals(Object o) {
            Ans ans = (Ans)o;
            if(x==ans.x && y==ans.y && a==ans.a) return true;
            else return false;
        }
        @Override
        public int compareTo(Ans o){
            if(this.x > o.x) return 1;
            else if(this.x<o.x) return -1;
            else {
                if(this.y > o.y) return 1;
                else if (this.y < o.y) return -1;
                else {
                    if(this.a == 0) return -1;
                    else return 1;
                }
            }
        }
        @Override
        public String toString() {
			return x+" "+y+" "+a;
		}
    }
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer;
        N = n;
        ArrayList<Ans> list = new ArrayList<>();

        for(int[] build : build_frame) {
            if(build[3] == 1) { // 설치하는 경우
                if(checkVaildAdd(list, build))
                    list.add(new Ans(build[0], build[1], build[2]));
            } else { // 삭제하는 경우
                checkVaildDelete(list, build);
            }
        }
        // 구조물의 상태를 정렬
        Collections.sort(list);
        
        // 출력 형식에 맞춤
        answer = new int[list.size()][3];
        int idx = 0;
        for(Ans ansList : list) {
            answer[idx][0] = ansList.x;
            answer[idx][1] = ansList.y;
            answer[idx++][2] = ansList.a;
        }
        return answer;
    }
    
    public boolean checkVaildAdd(ArrayList<Ans> list, int[] build) {
        boolean res = false;
        int x = build[0];
        int y = build[1];
        boolean[] flag = new boolean[2];
        
        // x,y에 기둥을 설치하는 경우는 
        // arraylist에 있는 것 중 
        // 1) x,y-1에 기둥이 있거나, 2) x,y나 x-1,y에 보가 있거나, 3) y==0일때 가능
        if(build[2] == 0 && build[3] == 1) {
            if(y==0) res = true;
            for(Ans ansList : list) {
                if((ansList.equals(new Ans(x,y,1))) || (x-1>=0 && ansList.equals(new Ans(x-1,y,1))))
                    res = true;
                if(y-1 >= 0 && ansList.equals(new Ans(x,y-1,0)))
                    res = true;
            }
        }
        
        // 보를 설치하는 경우
        // 기둥: x,y-1 || x+1,y-1
        // 보: x-1,y && x+1,y 
        else if(build[2] == 1 && build[3] == 1) {
            for(Ans ansList : list) {
                if((y-1>=0 && ansList.equals(new Ans(x,y-1,0))) 
                   || (x+1<=N && y-1>=0 && ansList.equals(new Ans(x+1,y-1,0))))
                    res = true;
                if(x-1 >= 0 && ansList.equals(new Ans(x-1,y,1))){
                    flag[0] = true;
                }
                if(x+1 <= N && ansList.equals(new Ans(x+1,y,1))){
                    flag[1] = true;
                }
            }
            if(flag[0] && flag[1]) res = true;
        }
        return res;
    }
    
    
    public void checkVaildDelete(ArrayList<Ans> list, int[] build) {
        int x = build[0];
        int y = build[1];
        // 삭제할 인덱스 찾은 후 삭제
        int idx = 0;
        for(int i=0;i<list.size();i++) {
            if(list.get(i).equals(new Ans(x,y,build[2]))) idx = i;
        }
        list.remove(idx);
        
        // 기둥을 삭제하는 경우
        if(build[2] == 0 && build[3] == 0) {
            for(Ans ansList : list){
                if(y+1 <= N && ansList.equals(new Ans(x,y+1,1))) { // 기둥 오른쪽에 보가 있는지
                    if(!checkVaildAdd(list, new int[] {x,y+1,1,1})) { // 삭제 후 보가 조건을 만족하지 않으면 삭제 취소
                        list.add(new Ans(x,y,build[2]));
                        return;
                    }
                }
                if(y+1 <= N && x-1>=0 && ansList.equals(new Ans(x-1,y+1,1))){ // 기둥 왼쪽에 보가 있는지
                    if(!checkVaildAdd(list, new int[] {x-1,y+1,1,1})) { // 삭제 후 보가 조건을 만족하지 않으면 삭제 취소
                        list.add(new Ans(x,y,build[2]));
                        return;
                    }
                }
                if(y+1<=N && ansList.equals(new Ans(x,y+1,0))){ // 기둥 위에 기둥이 있는지
                    if(!checkVaildAdd(list, new int[] {x,y+1,0,1})) { // 삭제 후 기둥이 조건을 만족하지 않으면 삭제 취소
                        list.add(new Ans(x,y,build[2]));
                        return;
                    }
                }
            }
        }

        // 보를 삭제하는 경우
        // 1,1보 삭제시 1,1기둥과 2,1기둥 체크
        // 3,2보 삭제시 2,2보와 4,2보 체크
        else if(build[2] == 1 && build[3] == 0) {
            for(Ans ansList : list){
                if(ansList.equals(new Ans(x,y,0))){ 
                    if(!checkVaildAdd(list,new int[] {x,y,0,1})){
                        list.add(new Ans(x,y,build[2]));
                        return;
                    }
                }
                if(x+1 <= N && ansList.equals(new Ans(x+1,y,0))){
                    if(!checkVaildAdd(list,new int[] {x+1,y,0,1})){
                        list.add(new Ans(x,y,build[2]));
                        return;
                    }
                }
                if(x-1>=0 && ansList.equals(new Ans(x-1,y,1))){
                    if(!checkVaildAdd(list,new int[] {x-1,y,1,1})){
                        list.add(new Ans(x,y,build[2]));
                        return;
                    }
                }
                if(x+1<=N && ansList.equals(new Ans(x+1,y,1))){
                    if(!checkVaildAdd(list,new int[] {x+1,y,1,1})){
                        list.add(new Ans(x,y,build[2]));
                        return;
                    }
                }
            }
        }
    }
}