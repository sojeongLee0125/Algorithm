import java.util.*;

// 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
// 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 
// 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.

class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> map = new HashMap<>();
        
        boolean answer = true;
        // 전화번호 리스트를 전부 Hash key로 저장한다.
        for(String num : phone_book){
            map.put(num, 1);
        }
        
        // 전화번호를 1개씩 돌면서 인덱스 0 부터 글자끝까지 map.containsKey()로 검사한다.
        outer : for(String num : phone_book){
            for(int i=0; i<num.length(); i++){
                String sub = num.substring(0, i);
                if(map.containsKey(sub)) {
                    answer = false;
                    break outer;
                }
            }
        }
        
        return answer;
    }
}