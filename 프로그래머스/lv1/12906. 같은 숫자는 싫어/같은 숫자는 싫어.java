import java.util.*;

public class Solution {
    Stack<Integer> s = new Stack<>();
    
    public int[] solution(int[] arr) {
       
        for(int a : arr){
            if(!s.isEmpty() && s.peek() == a) continue;
            else s.push(a);
        }
        
       int[] answer = new int[s.size()];
        
       for(int i=0; i<s.size(); i++){
           answer[i] = s.get(i);
       }

       return answer;
    }
}