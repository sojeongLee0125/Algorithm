import java.util.*;

// 사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 
// 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.


class Solution {
    ArrayList<Integer> nums = new ArrayList<>();
    int T;
    int cnt;
    
    public int solution(int[] numbers, int target) {
        
        for(int n : numbers) nums.add(n);
        T = target;
        
        dfs(0, 0);
        
        return cnt;
    }
    
    public void dfs(int idx, int sum){
        if(idx == nums.size()){
            if(sum == T) cnt++;
            return;
        }
        
        dfs(idx+1, sum + nums.get(idx));
        dfs(idx+1, sum - nums.get(idx));
    }
}