import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int total = jobs.length; // 총 작업량
        int comp = 0; // 현재까지 작업 완료량
        
        // 시작시간 순 오름차순
        Arrays.sort(jobs, (a1, a2) -> a1[0] - a2[0]);
        
        // 작업시간 순 오름차순 큐 생성
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[1] - a2[1]);
        
        int time = 0;
        int idx = 0;
        
        while(comp < total){
            while(idx < total && jobs[idx][0] <= time){
                pq.add(jobs[idx++]);
            }
            
            if(!pq.isEmpty()){
                int[] cur = pq.poll();
                time += cur[1];
                answer += (time - cur[0]);
                comp++;
            }else{
                // 가능한 작업이 없는 경우
                // 다음 값의 최초작업으로 미룬다.
                time = jobs[idx][0];
            }
        }
        
        return answer / total;
    }
}