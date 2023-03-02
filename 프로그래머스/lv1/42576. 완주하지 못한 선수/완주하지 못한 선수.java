import java.util.*;

// 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
// 참가자 중에는 동명이인이 있을 수 있습니다.
class Solution {
    
    HashMap<String, Integer> map = new HashMap<>();
    
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        for(int i=0; i<participant.length; i++){
            map.put(participant[i], map.getOrDefault(participant[i], 0) + 1);
        }
        
        for(int i=0; i<completion.length; i++){
            if(map.containsKey(completion[i])) {
                map.put(completion[i], map.get(completion[i]) - 1);
                if(map.get(completion[i]) == 0) map.remove(completion[i]);
            }
        }
        
        for(String name : map.keySet()){
            if(map.containsKey(name)) answer = name;
        }
        
        return answer;
    }
}