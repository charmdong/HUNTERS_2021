import java.util.*;

// 이슈사항 : cnt관리할 배열을 크게 잡았더니 메모리 초과 발생
// 그래서 class로 string과 cnt를 관리하였더니 contains오류 발생
// override equals로 해결하였지만 cnt를 증가시키는 과정에서 오류 발생해서 
// arraylist 두개로 나누어 관리
class Solution {
    static ArrayList<String> cand;
    static ArrayList<Integer> cnt;
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer;
        
        ArrayList<String> result = new ArrayList<>();
        
        for(int cour : course) {
            cand = new ArrayList<>();
            cnt = new ArrayList<>();
            for(String order : orders) {
                // order의 cour개 조합 구하기
                char[] ch = order.toCharArray();
                boolean[] visited = new boolean[ch.length];
                comb(ch, visited, 0, ch.length, cour);
                
            }
            ArrayList<Integer> idx = new ArrayList<>();
            int max = 0;
            for(int i=0;i<cnt.size();i++){
                if(max<cnt.get(i)){
                    max = cnt.get(i);
                    idx = new ArrayList<>();
                    idx.add(i);
                } else if(max == cnt.get(i)){
                    idx.add(i);
                }
            }
            if(max < 2) continue;
            for(int i : idx){
                result.add(cand.get(i));
            }
        }
        
        Collections.sort(result);
        
        answer = new String[result.size()];
        for(int i=0;i<result.size();i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    public void comb(char[] ch, boolean[] visited, int depth, int n, int r) {
        if(r==0){
            String s = "";
            for(int i=0;i<n;i++){
                if(visited[i]){
                    s+=ch[i];
                }
            }
            // 사전 순 정렬
            char[] order = s.toCharArray();
            Arrays.sort(order);
            s = new String(order);
            
            // 아직 후보에 없는 메뉴이면 추가
            // 이미 후보에 있는 메뉴이면 cnt+1
            if(cand.contains(s)){
                int count = cnt.get(cand.indexOf(s));
                cnt.set(cand.indexOf(s),count+1);
            }
            else{
                cand.add(s);
                cnt.add(1);
            }
            return;
        }
        if(depth == n) {
            return;
        }
        
        visited[depth] = true;
        comb(ch, visited, depth + 1, n, r -1);
        visited[depth] = false;
        comb(ch, visited, depth + 1, n, r);
    }
}