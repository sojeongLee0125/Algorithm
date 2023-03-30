import java.util.*;

// 주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.
// 항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 
// 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.

class Solution {
    ArrayList<String> answer = new ArrayList<>();
    int[] chk;
    int total;
    
    public String[] solution(String[][] tickets) {
        total = tickets.length;
        chk = new int[total];
        
        dfs(0, "ICN", "ICN ", tickets);
        
        Collections.sort(answer);
        String[] arr = answer.get(0).split(" ");
       
        return arr;
    }
    
    public void dfs(int cnt, String last, String route, String[][] tickets){
        if(cnt == total){
            answer.add(route);
            return;
        }
        for(int i=0; i<total; i++){
            if(chk[i] == 1) continue;
            if(!tickets[i][0].equals(last)) continue;
            chk[i] = 1;
            dfs(cnt + 1, tickets[i][1], route + tickets[i][1] + " ", tickets);
            chk[i] = 0;
        }
    }
}