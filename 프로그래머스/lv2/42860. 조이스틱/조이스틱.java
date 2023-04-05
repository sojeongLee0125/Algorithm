import java.util.*;

class Solution {
    public int solution(String name) {
        int len = name.length();
        int move = len - 1;
        int answer = 0;
        
        
        // 조이스틱을 움직이는 3가지 경우
        // 1. ABDCERT 직진만 하는 경우
        // 2. CDAAAAAAAD 직진하다가 다시 돌아와서 역으로 가는 경우
        // 3. BADFAAAAAFD 역행하다가 다시 돌아와서 직진으로 가는 경우
        // 각 알파벳의 그 자리 움직임 + 총 MOVE 수
        
        for(int i=0; i<len; i++){
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);
            
            if(i < len-1 && name.charAt(i+1) == 'A'){
                int endA = i+1;
                
                while(endA < len && name.charAt(endA) == 'A'){
                    endA++;
                }
                
                move = Math.min(move, (i*2) + len - endA); // 직진하다가 돌아와서 역행하는 경우
                move = Math.min(move, (i + (len - endA) * 2) ); // 역행 후 돌아와서 다시 직진하는 경우
            }
        }
        
        
        return answer + move;
    }
}