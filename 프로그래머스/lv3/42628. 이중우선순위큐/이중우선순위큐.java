import java.util.*;

// 이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 
// 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 
// 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.
// 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.
// 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        // pq를 2개만든다. 하나는 최대값 큐, 하나는 최솟값 큐
        // op순서대로 받으면서 두군데 모두 넣는다.
        // 만약 제거하는 연산이 나올경우 최대값은 최대큐 최솟값은 최소큐에서 뺀다.
        // 해당 뺀 값을 모든 큐에서 제거해준다.
        // 마지막에 큐가 비지 않았으면 각각 최대큐와 최소큐에서 한개씩 값을 뺀다.
        
        for(String str : operations){
            StringTokenizer st = new StringTokenizer(str, " ");
            char op = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());
            
            if(op == 'I'){
                pq1.add(num);
                pq2.add(num);
            }else if(op == 'D'){
                if(num == 1 && !pq2.isEmpty()){
                    int cur = pq2.poll(); // 현재 최대값 제거
                    pq1.remove(cur);
                }else if(num == -1 && !pq1.isEmpty()){
                    int cur = pq1.poll(); // 현재 최솟값 제거
                    pq2.remove(cur);
                }
            }
       }
        
        if(pq1.isEmpty() && pq2.isEmpty()) return answer;
        else{
            int max = pq2.poll();
            int min = pq1.poll();
            return new int[]{max, min};
        }
    }
}