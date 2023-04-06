import java.util.*;

class Solution {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[1] - a2[1]);
    
    public int solution(int[][] routes) {
        int answer = 0;
        
        // 1. pq에 모든 루트를 담는다.
        // 2. 꺼낸 루트의 마지막 점을 저장한다.
        // 3. 그 다음 루트의 출발점이 마지막 점 이내이면 패스한다. 아니면 answer++ 하고 마지막 점으로 새로운 점으로 갱신한다.
        
        for(int[] arr : routes){
            pq.add(arr);
        }
        
        
        int last = -300001;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int st = cur[0];
            int ed = cur[1];
            if(st <= last) continue;
            else{
                answer++;
                last = ed;
            }
        }
        
        return answer;
    }
}