import java.util.*;

// 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다.
// 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
// 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
// 그렇지 않으면 J를 인쇄합니다.

// 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 
// 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 
// 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.

class Solution {
    
    static class Paper{
        int idx;
        int p;
        
        public Paper(int idx, int p){
            this.idx = idx;
            this.p = p;
        }
    }
    
    public int solution(int[] priorities, int location) {
        Deque<Paper> dq1 = new ArrayDeque<>();
        Deque<Paper> dq2 = new ArrayDeque<>();
        
        for(int i=0; i<priorities.length; i++){
            dq1.addLast(new Paper(i, priorities[i]));
        }
        
        // 1. paper 클래스를 정의한다. - idx, prior
        // 2. 큐 2개를 준비한다.
        // 3. priorities 를 paper 인스턴스로 변환하여 첫번째 큐에 순서대로 넣는다.
        
        // 4. 큐에서 첫번째 값을 꺼낸다. 값을 임시로 저장하고 두번째 큐에 넣는다.
        // 5. 다음 큐부터 차례대로 꺼내서 만약 임시값보다 중요도가 큰 값이 존재하면 두번째 큐 값들을 첫번째 큐에 넣는다.
        // 6. 만약 큐가 빌 때까지 큰 값이 없으면 두번째 큐에서 첫번째 값을 빼서 인쇄하는데 이때 location 값과 비교한다.
        
        int cnt = 0;
        
        while(true){
            Paper p = dq1.pollFirst();
            int cur = p.p;
            dq2.addFirst(p);
            
            while(!dq1.isEmpty()){
                Paper nxt = dq1.pollFirst();
                if(nxt.p > cur){
                    // 뒤에 더 큰 중요도가 있는 경우
                    dq1.addFirst(nxt);
                    while(!dq2.isEmpty()) dq1.addLast(dq2.pollFirst());
                    break;
                }else{
                    // cur 보다 중요도가 작은 경우
                    dq2.addLast(nxt);
                }
            }
            
            // p의 중요도가 가장 높을 경우.
            // 7. 인쇄를 할 때 마다 cnt++ 해주고 location 값과 일치할 경우 return
            if(dq1.isEmpty()){
              Paper out = dq2.pollFirst();
              cnt++;
              if(out.idx == location) break;
            }
            
            while(!dq2.isEmpty()) dq1.addLast(dq2.pollFirst());
        }
        
        return cnt;
    }
}