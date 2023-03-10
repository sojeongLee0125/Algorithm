import java.util.*;

// 하드디스크는 한 번에 하나의 작업만 수행
// 각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원 배열 jobs가 매개변수로 주어질 때, 
// 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면 평균이 얼마가 되는지 return 하도록 
// solution 함수를 작성해주세요. (단, 소수점 이하의 수는 버립니다)
// 하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.

class Solution {
    
    public int solution(int[][] jobs) {
        int sum = 0;
        int len = jobs.length;
        int cnt = 0; // 처리한 작업의 수
        int idx = 0; // jobs 배열의 진행 인덱스 수
        int time = 0;
        
        // 요청 시간 순 오름차순 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        // 처리 시간 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        // 전체 작업 처리하기 전까지
        while(cnt < len){
            
            // 아직 배열의 끝까지 도달 안했고 시작 값이 현재 시간보다 작거나 같을 경우(작업이 가능할 경우)
            while(idx < len && jobs[idx][0] <= time){
                pq.add(jobs[idx++]);
            }
            
            if(pq.isEmpty()){
                // 작업 가능한 수가 없을 경우 다음 값의 최초 작업의 시작시간까지 시간을 업데이트
                time = jobs[idx][0];
            }else{
                // 작업 가능한 수가 있을 경우 그 중 소요시간이 가장 적은 것을 꺼내서 갱신한다.
                int[] cur = pq.poll();
                int s = cur[0];
                int t = cur[1];
                time += t;
                sum += (time - s);
                cnt++;
            }
        }
        
        return sum / len;
    }
}