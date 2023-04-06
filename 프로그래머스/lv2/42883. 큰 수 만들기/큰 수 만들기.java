import java.util.*;

class Solution {
    public String solution(String number, int k) {
        int len = number.length() - k; // 만들어야 할 문자열 길이
        int idx = 0; // 현재까지 만들어진 문자수
        StringBuilder sb = new StringBuilder();
        
        // 만들어야 할 문자열 길이까지 반복한다.
        for(int i=0; i<len; i++){
            int max = 0;
            for(int j=idx; j <= i+k; j++){
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