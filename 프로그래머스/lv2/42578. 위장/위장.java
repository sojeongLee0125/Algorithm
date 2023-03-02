import java.util.*;

// 스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 
// 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.
class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    
    public int solution(String[][] clothes) {
        int answer = 1;
        
        // 종류(key) - 구체적인 옷 갯수 (value)
        // 부분집합의 수를 구한다.
        for(int i=0; i < clothes.length; i++){
            String name = clothes[i][0];
            String key = clothes[i][1];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        // (n+1) * (m+1) - 1 = 조합의 수
        for(String key : map.keySet()){
            answer *= (map.get(key) + 1);
        }
        
        return answer -1;
    }
}