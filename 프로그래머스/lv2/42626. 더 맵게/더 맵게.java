import java.util.*;

class Solution {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        for(int n: scoville) pq.add(n);
        
        while(!pq.isEmpty()){
            if(pq.size() <= 1 && pq.peek() < K){
                answer = -1;
                break;
            }
            
            // 제일 안매운 요소가 >= K 인지 검사
            int c1 = pq.poll();
            if(c1 >= K) break;
            else{
                int c2 = pq.poll();
                int n = c1 + (c2 * 2);
                pq.add(n);
                answer++;
            }
        }
        
        return answer;
    }
}