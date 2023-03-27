import java.util.*;

// 각 집에 있는 돈이 담긴 배열 money가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓값을 return 하도록 solution 함수를 작성하세요.

class Solution {
    int[] dp1; // i번째 집까지 도달했을때의 훔친돈
    int[] dp2; // i번째 집까지 도달했을때의 훔친돈
    
    int max; // 훔칠 수 있는 최댓값
    
    public int solution(int[] money) {
        dp1 = new int[money.length]; // 첫번째 집은 무조건 털고 마지막 집은 무조건 털지 않는 경우
        dp2 = new int[money.length];  // 첫번째 집을 무조건 털지 않는 경우
        
        // 첫번째 경우
        dp1[0] = money[0];
        dp1[1] = money[0];
        max = dp1[0];
        
        for(int i=2; i<money.length - 1; i++){
            dp1[i] = Math.max(money[i] + dp1[i-2], dp1[i-1]);
            max = Math.max(dp1[i], max);
        }
        
        // 두번째 경우
        dp2[0] = 0;
        dp2[1] = money[1];
        max = Math.max(dp2[1], max);
        
        for(int i=2; i<money.length; i++){
            dp2[i] = Math.max(money[i] + dp2[i-2], dp2[i-1]);
            max = Math.max(dp2[i], max);
        }
        
        return max;
    }
}