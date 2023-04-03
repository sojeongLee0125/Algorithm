import java.util.*;

class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    
    public int solution(String[][] clothes) {
        int answer = 1;
        
        for(String[] c : clothes){
            String key = c[1];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        for(String k : map.keySet()){
            answer *= (map.get(k) + 1);
        }
        
        return answer - 1; // 공집합 제거
    }
}