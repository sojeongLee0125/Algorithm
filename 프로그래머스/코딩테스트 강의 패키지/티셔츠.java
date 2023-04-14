import java.util.*;

/*
- 한 사람당 티셔츠 최대 하나를 준다.
- 모든 참가자는 자신의 상의 크기와 같거나 큰 티셔츠를 받는다.
*/

class Solution {
    public int solution(int[] people, int[] tshirts) {
        int answer = 0;

        Arrays.sort(people);
        Arrays.sort(tshirts);

        int j = tshirts.length - 1;

        for(int i = people.length - 1; i >= 0 && j >= 0; i--){         
            if(people[i] <= tshirts[j]){
                answer++;
                j--;
            }
        }

        return answer;
    }
}
