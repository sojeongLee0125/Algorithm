import java.util.*;

class Solution {
    PriorityQueue<Integer> minq = new PriorityQueue<>();
    PriorityQueue<Integer> maxq = new PriorityQueue<>(Collections.reverseOrder());
    
    public int[] solution(String[] operations) {
        for(String str : operations){
            String[] arr = str.split(" ");
            
            String op = arr[0];
            Integer num = Integer.parseInt(arr[1]);
            
            if(op.equals("I")){
                maxq.add(num);
                minq.add(num);
            }else if(op.equals("D")){
                if(num == -1){
                    if(minq.isEmpty()) continue;
                    int n1 = minq.poll();
                    maxq.remove(n1);
                }else{
                    if(maxq.isEmpty()) continue;
                    int n2 = maxq.poll();
                    minq.remove(n2);
                }
            }
        }
        
        int max = 0;
        int min = 0;
        
        while(!maxq.isEmpty()){
            max = maxq.poll();
        }
        
        while(!minq.isEmpty()){
            min = minq.poll();
        }
        
        int[] answer = {min, max};
        
        return answer;
    }
}