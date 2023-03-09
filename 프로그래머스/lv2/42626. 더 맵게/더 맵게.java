import java.util.*;


//  모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다.
// 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.
// 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
// 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.

// 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 
// 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        if(K == 0) return 0;
        if(scoville.length < 2) return -1;
        
        int answer = 0;
        
        // 1. PriorityQueue에 scoville 지수들을 넣는다.
        for(int n : scoville){
            pq.add(n);
        }
        
        // 2. 첫번째와 두번째 요소를 꺼내는데 이 때 첫번째 요소가 K 이상일 경우 break 한다.
        boolean flag = false;
        
        while(pq.size() > 1){
            int first = pq.poll();
            int second = pq.poll();
            
            if(first >= K) {
                flag = true;
                break;
            }
            
            int sum = first + (second * 2);
            pq.add(sum);
            answer++;
        }
        
        // pq에 1개만 남았을 경우 [1, 1] 이면서 K = 3
        if(pq.size() == 1 && pq.peek() >= K){
            flag = true;
        }
        
        if(flag) return answer;
        else return -1;
    }
}