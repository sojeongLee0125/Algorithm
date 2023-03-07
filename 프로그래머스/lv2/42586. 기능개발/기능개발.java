import java.util.*;

// 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.
// 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 
// 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.
// 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses
// 각 작업의 개발 속도가 적힌 정수 배열 speeds
// 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.
// 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 
// 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.

class Solution {
    
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        // 각각의 프로그래스 별로 서비스가 완성되기까지의 날짜 리스트를 구한다.
        // (100 - (pro[i] % sp[i] != 0 ? pro[i] / sp[i] + 1 : pro[i] / sp[i])
        
        for(int i=0; i < progresses.length ; i++){
            int left = 100 - progresses[i];
            int complete = left % speeds[i] == 0 ? left / speeds[i] : left / speeds[i] + 1;
            ans.add(complete);
        }
        
        // 큐에 해당 날짜 리스트를 넣는다.
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 0; i < ans.size(); i++){
            q.add(ans.get(i));
        }
        
        // 큐에서 차례대로 꺼내면서 다음 날짜가 현재 날짜보다 클 경우 배포 카운트에 추가한다.
        // 다음 날짜가 현재 날짜보다 작은 경우 cnt를 올리고 그다음을 꺼낸다.
        ArrayList<Integer> list = new ArrayList<>();
        
        while(!q.isEmpty()){
            int cur = q.poll();
            int cnt = 1;
            while(!q.isEmpty() && q.peek() <= cur){
                cnt++;
                q.poll();
            }
            list.add(cnt);
        }

        int[] answer = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}