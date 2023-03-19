import java.util.*;

// 조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
// 만들고자 하는 이름 name이 매개변수로 주어질 때, 
// 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.
// 위 - 다음 알파벳 & 아래 - 이전 알파벳 & 오/왼 - 커서 이동

class Solution {
    public int solution(String name) { 
        // 케이스 1 - JEROEN - 오른쪽으로만 쭉 가는 경우
        // 케이스 2 - JBAAAAAAACCC 오른쪽으로 가다가 다시 왼쪽으로 꺾는 경우
        // 케이스 3 - CCCAAAAAAB 처음 왼쪽으로 갔다가 다시 오른쪽으로 꺾는 경우
        
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
