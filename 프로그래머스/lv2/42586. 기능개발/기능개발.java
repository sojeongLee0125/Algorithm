import java.util.*;

class Solution {
    Stack<Integer> s = new Stack<>();
    
    public int[] solution(int[] progresses, int[] speeds) {
        
        
        for(int i=speeds.length - 1; i>=0; i--){
            int n = (100-progresses[i]) / speeds[i];
            if(((100-progresses[i]) % speeds[i]) != 0) n++;
            s.push(n);
        }
        
        ArrayList<Integer> a = new ArrayList<>();
        while(!s.isEmpty()){
            int cur = s.pop();
            int cnt = 1;
            while(!s.isEmpty() && s.peek() <= cur){
                s.pop();
                cnt++;
            }
            a.add(cnt);
        }
        
        int[] answer = new int[a.size()];
        for(int i=0; i<a.size(); i++) answer[i] = a.get(i); 
        
        return answer;
    }
}