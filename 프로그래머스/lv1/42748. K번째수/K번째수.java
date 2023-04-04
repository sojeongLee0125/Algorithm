import java.util.*;

class Solution {
    ArrayList<Integer> ans = new ArrayList<>();
    
    public int[] solution(int[] array, int[][] commands) {
        
        for(int[] arr : commands){
            ArrayList<Integer> tmp = new ArrayList<>();
            int i = arr[0] - 1;
            int j = arr[1] - 1;
            int k = arr[2] -1;
            for(int s = i; s <= j; s++){
                tmp.add(array[s]);
            }
            tmp.sort(Integer::compareTo);
            ans.add(tmp.get(k));
        }
        
        int[] answer = new int[ans.size()];
        
        for(int i=0; i<answer.length; i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}