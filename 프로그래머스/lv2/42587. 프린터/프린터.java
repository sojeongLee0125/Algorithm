import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
         Deque<int[]> q1 = new ArrayDeque<>();
         Deque<int[]> q2 = new ArrayDeque<>();
        
        for(int i=0; i<priorities.length; i++){
            q1.addLast(new int[]{priorities[i], i}); // 중요도 + 순서 순으로 저장
        }
        
        // q1에서 하나를 빼서 q2로 차근차근 옮기면서 q1에서 자기보다 큰 중요도가 있는지 비교한다.
        // q1이 빌때까지 없으면 인쇄한다.
        int cnt = 0;
        
        outer: while(true){
            int[] cur = q1.pollFirst();
            int p = cur[0]; // 현재 꺼낸 문서의 중요도
            
            // 일단 현재 문서를 q2 제일 앞단에 넣는다.
            q2.addFirst(cur);
            
            while(!q1.isEmpty()){
                int[] nx = q1.pollFirst();
                if(nx[0] > p){
                    // 뒤에 더 중요한 문서가 있을 경우
                    q1.addFirst(nx);
                    // q2에 있는 것들 전부 q1에 다시 넣고 break;
                    while(!q2.isEmpty()) q1.addLast(q2.pollFirst());
                    break;
                }else{
                    // 더 중요하지 않은 경우
                    q2.addLast(nx);
                }
            }
            
            if(q1.isEmpty()){
                //q2의 제일 앞단 문서가 가장 중요도가 놓은 경우
                cnt++;
                int[] out = q2.pollFirst();
                if(out[1] == location) break outer;
            }
            
            // q2 -> 다시 q1으로 집어넣는다.
            while(!q2.isEmpty()) q1.addLast(q2.pollFirst()); 
        }
        
        return cnt;
    }
}