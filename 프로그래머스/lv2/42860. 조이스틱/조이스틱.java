import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int move = name.length() - 1; // 오른쪽으로만 직진
        
        for(int i=0; i<name.length(); i++){
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            // A가 등장할 경우
            if(i < name.length() - 1 && name.charAt(i + 1) == 'A'){
                int lastA = i + 1;
                while(lastA < name.length() && name.charAt(lastA) == 'A') lastA++;
                move = Math.min(move, i * 2 + (name.length() - lastA));
                move = Math.min(move, i + (name.length() - lastA) * 2);
            }
        }
        
        return answer + move;
    }
}