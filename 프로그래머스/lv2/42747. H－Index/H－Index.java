import java.util.*;

// 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index
// 어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 
// 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        // 1. citations를 오름차순 정렬한다.
        // 2. h를 citations.length 부터 작아지면서 진행한다.
        // 3. 만족하는 h가 있다면 break;
        
        Arrays.sort(citations);
        
        for(int h = citations.length; h >= 0; h--){
            int cnt = 0;
            for(int x : citations){
                if(x >= h) cnt++;
            }
            if(cnt >= h) {
                answer = h;
                break;
            }
        }
        
        return answer;
    }
}