import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = -1;
        
        for(int i=1; i<triangle.length; i++){
            for(int j=0; j < triangle[i].length; j++){
                
                int tmp = 0; 
                
                // 첫번째 원소일 경우
                if(j == 0){
                    tmp = triangle[i-1][j] + triangle[i][j];
                }else if(j == triangle[i].length - 1){
                    // 마지막 원소일 경우
                    tmp = triangle[i-1][triangle[i-1].length - 1] + triangle[i][j];
                }else{
                    tmp = Math.max(triangle[i-1][j-1] + triangle[i][j], triangle[i-1][j] + triangle[i][j]);
                }
                
                answer = Math.max(tmp, answer);
                triangle[i][j] = tmp;
            }
        }
        
        return answer;
    }
}