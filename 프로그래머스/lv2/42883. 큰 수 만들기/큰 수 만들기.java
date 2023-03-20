import java.util.*;

// 어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
// number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 
// 문자열 형태로 return 하도록 solution 함수를 완성하세요.

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        
        // 만들어야 할 문자열 길이인 number 길이 - k 까지 진행한다.
        for(int i=0; i < number.length() - k; i++){
            int max = 0;
            int lt = k - 1;
            // 현재 위치 ~ 뒤에서부터 만들어야 할 문자 갯수만큼 범위를 검색한다.
            // 첫번째 0 -> 10 - 5 : 0번째부터 뒤에서 5번째까지 중 가장 큰수 7(2)
            // 두번째 3 -> 10 - 5 : 3번째부터 뒤에서 4번째까지 중 가장 큰수 7(3)
            // 세번째 4 -> 10 - 4 : 4번째부터 뒤에서 3번째까지 중 가장 큰수 ...
            for(int j = idx; j <= i + k; j++){
                int num = number.charAt(j) - '0';
                if(max < num){
                    max = num;
                    idx = j + 1;
                }   
            }
            sb.append(max);
        }
            
        return sb.toString();
    }
    
}