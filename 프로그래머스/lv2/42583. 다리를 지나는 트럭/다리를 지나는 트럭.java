import java.util.*;

class Solution {
    Deque<Integer> tq = new ArrayDeque<>();
    Deque<Integer> bq = new ArrayDeque<>();
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        
        // 1. 트럭들을 트럭 큐에 넣는다.
        for(int i=0; i<truck_weights.length; i++) tq.addLast(truck_weights[i]);
        
        // 2. 다리 길이만큼 bq에 0을 넣어놓는다.
        for(int i=0; i<bridge_length; i++) bq.addLast(0);
        
        // 3. 트럭에서 하나씩 꺼내며 올린다.
        int w = 0;
        int cnt = 0;
        
        while(!tq.isEmpty()){
            // 다리에서 제일 앞의 것을 꺼낸다.
            int out = bq.poll();
            if(out != 0) {
                w -= out;
                cnt--;
            }
            
            // 트럭을 올리거나 0을 추가한다.
            int cur = tq.pollFirst();
            if(w + cur <= weight && cnt <= bridge_length){
                bq.add(cur);
                w += cur;
                cnt++;
            }else{
                bq.add(0);
                tq.addFirst(cur);
            }
            
            time++;
        }
        
        while(!bq.isEmpty()){
            bq.pollFirst();
            time++;
        }
        
        return time;
    }
}