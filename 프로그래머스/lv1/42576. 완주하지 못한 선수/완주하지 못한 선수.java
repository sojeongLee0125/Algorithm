import java.util.*;

// 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
// 마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 
// 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        
        String answer = "";
        // 1. 참가자 명단을 차례대로 HashMap에 저장한다. 값은 갯수.
        for(String name : participant){
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        // 2. 완주자 명단을 차례대로 HashMap에서 제거한다. 갯수--
        for(String name : completion){
            map.put(name, map.getOrDefault(name, 0) - 1);
             // 3. 갯수가 0이 되면 해당 key를 삭제한다.
            if(map.get(name) == 0) map.remove(name);
        }
        
        for(String key : map.keySet()) answer = key;
      
        return answer;
    }
}