import java.util.*;

// 삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 
// 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
  
        // 삼각형 배열을 받아 누적합 배열을 만든다.
        // 첫번째는 그대로 넣는다.
        // 두번째부터 처음은 앞배열의 0번과 현배열의 0번을 더한값, 마지막 배열은 앞배열의 last와 현배열의 last를 더한값
        // 처음과 끝이 아닌 가운데 값들은 이전 배열중 자신 인덱스-1 & 자신인덱스의 누적값을 더한다.
        
        for(int i=1; i< triangle.length; i++){
            for(int j=0; j<triangle[i].length; j++){
                int tmp = 0;
                if(j == 0) {
                    tmp = triangle[i][0] + triangle[i-1][0];
                }
                else if(j == triangle[i].length - 1) {
                    tmp = triangle[i][j] + triangle[i - 1][triangle[i - 1].length - 1];
                }
                else{
                    tmp = Math.max(triangle[i][j]+triangle[i-1][j-1], triangle[i][j]+triangle[i-1][j]);
                }
                
                triangle[i][j] = tmp;
                answer = Math.max(tmp, answer);
            }
        }
        
        return answer;
    }
}