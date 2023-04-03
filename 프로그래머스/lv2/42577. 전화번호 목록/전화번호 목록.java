import java.util.*;

class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        for(String n : phone_book) map.put(n, 0);
        
        outer: for(String p : phone_book){
            for(int i=1; i< p.length(); i++){
                String tmp = p.substring(0, i);
                if(map.containsKey(tmp)){
                    answer = false;
                    break outer;
                }
            }
        }
        
        return answer;
    }
}