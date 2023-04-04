import java.util.*;

class Solution {
    
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int idx = 0;
        
        for(int[] arr : commands){
            int i = arr[0]; 
            int j = arr[1];
            int k = arr[2];
            
            int[] tmp = Arrays.copyOfRange(array, i-1, j);
            Arrays.sort(tmp);
            answer[idx++] = tmp[k-1];
        }
        
        return answer;
    }
}