import java.util.*;

class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();
    
    public int solution(int[] nums) {
        int answer = 0;
        for(int n: nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
                
        return Math.min(nums.length/2, map.keySet().size());
    }
}