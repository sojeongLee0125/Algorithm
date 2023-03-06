import java.util.*;

// 스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.
// 스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 
// solution 함수를 작성해주세요. -> 부분집합의 갯수 (N+1) * (M+1) * ...
// 스파이는 하루에 최소 한 개의 의상은 입습니다.

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        
        int answer = 1;
        
        // 옷들의 종류(키), 갯수(값) HashMap에 저장한다.
        for(int i=0; i<clothes.length; i++){
            String key = clothes[i][1];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        // 해시맵의 모든 키의 값을 꺼내서 +1 해서 곱한다.
        for(String key : map.keySet()){
            answer *= (map.get(key) + 1); 
        }
        
        // 1을 뺀다.
        return answer - 1;
    }
}