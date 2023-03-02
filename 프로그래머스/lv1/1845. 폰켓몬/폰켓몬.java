import java.util.*;

class Solution {
    
    HashMap<Integer, Integer> map = new HashMap<>();
    
    public int solution(int[] nums) {
        int answer = 0;
        for(int i=0; i < nums.length ; i++){
            if(map.containsKey(nums[i])) continue;
            else{
                map.put(nums[i], 1);
                answer++;
            }
        }
        return Math.min(answer, nums.length / 2);
    }
}