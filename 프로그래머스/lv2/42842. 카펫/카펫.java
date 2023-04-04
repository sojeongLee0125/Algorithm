import java.util.*;

class Solution {
    int b,y;
    
    public boolean isOk(int n1, int n2){
        return y == ((n1-2) * (n2-2));
    }
    
    public int[] solution(int brown, int yellow) {
        b = brown;
        y = yellow;
        
        int sum = b + y;
        int row = 0;
        int col = 0;
        
        for(int i=3; i<=Math.sqrt(sum); i++){
            if(sum % i == 0){
                if(isOk(i, sum/i)){
                    row = Math.min(i, sum/i);
                    col = Math.max(i, sum/i);
                } 
            }
        }
        
        return new int[]{col, row};
    }
}