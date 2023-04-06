import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int rt = people.length - 1;
        
        // 1. 배열을 정렬한다.
        // 2. 반복문은 lt가 -> 끝까지 진행하는 식으로 한다.
        // 3. 각각의 lt마다 rt를 감소하며 짝을 찾는다. 이때 보트에 쌍으로 타는 사람들은 큰 수로 표시한다.
        
        Arrays.sort(people);
        
        for(int lt=0; lt < people.length; lt++){
            if(people[lt] == 300) continue;   
            while(lt < rt){
                if(people[lt] + people[rt] <= limit){
                    people[lt] = 300;
                    people[rt] = 300;
                    answer++;
                    break;
                }
                rt--;
            }
        }
        
        for(int i=0; i<people.length; i++){
            if(people[i] != 300) answer++;
        }
        
        return answer;
    }
}