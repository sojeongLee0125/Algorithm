import java.util.*;

// 트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 
// 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 
// 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하까지의 무게를 견딜 수 있습니다.
// 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.
// 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> truck = new LinkedList<>();
        Queue<Integer> bridge = new LinkedList<>();
        int answer = 0;
        
        // 트럭을 트럭 큐에 넣는다.
        // 다리를 다리 Queue로 만든다. 그리고 0으로 가득 채운다.
        
        for(int i=0; i<bridge_length; i++){
            bridge.add(0);
        }
        
        for(int t : truck_weights){
            truck.add(t);
        }
        
        // 반복문 트럭 큐가 모두 빌 때 && 다리큐가 모두 빌 때까지 반복한다.
        // 시간을 올린다.
        // 다리큐에서 첫번째 것을 꺼내고 용량에서 해당 용량만큼을 더해준다.
        // 트럭큐에서 하나 꺼내서 다리큐에 올라갈 수 있으면 용량을 빼고 큐에 넣는다.
        // 다리큐에 올라갈 수 없으면 다리큐에 0을 넣는다.
        
        int time = 0;
        int cnt = 0;
        while(!bridge.isEmpty()){
            while(!truck.isEmpty()){
                time++;
                // 앞에 차가 빠질 경우
                int head = bridge.poll();
                if(head != 0) {
                    cnt--;
                    weight += head;
                }
                int cur = truck.peek();
                // 차가 다리에 올라갈 수 있는 경우
                if(cur <= weight && cnt < bridge_length){                   
                    weight -= cur;
                    cnt++;
                    bridge.add(truck.poll());
                }else{
                    // 차가 다리에 올라갈 수 없는 경우
                    bridge.add(0);
                }
            }
            // 트럭에서는 다 나오고 다리에서 진행되는 경우
           
            while(!bridge.isEmpty()) {
                bridge.poll();
                time++;
            }
        }
        
        return time;
    }
}