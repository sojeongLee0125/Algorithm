import java.util.*;

// 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
// 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        // 입력받은 전화번호는 hashmap에 넣는다.
        for(String num : phone_book){
            map.put(num, 1);
        }
        
        outer : 
        for(String num : phone_book){
            map.remove(num);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i < num.length(); i++){
                sb.append(num.charAt(i));
                if(map.containsKey(sb.toString())){
                    answer = false;
                    break outer;
                }
            }
            map.put(num, 1);
        }        
        
        return answer;
    }
}