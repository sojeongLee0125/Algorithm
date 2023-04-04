import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int col = 0;
        int row = 0;
        
        for(int[] arr : sizes){
            int max = Math.max(arr[0], arr[1]);
            int min = Math.min(arr[0], arr[1]);
            col = Math.max(max, col);
            row = Math.max(min, row);
        }
        
        return col * row;
    }
}