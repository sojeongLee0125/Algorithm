import java.util.*;

class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        for(String n : participant){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        for(String n : completion){
            map.put(n, map.get(n) - 1);
        }
        
        for(String key : map.keySet()){
            if(map.get(key) != 0) answer = key;
        }
    
        return answer;
    }
}