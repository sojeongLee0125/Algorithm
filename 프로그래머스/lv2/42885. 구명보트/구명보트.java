import java.util.*;

// 구명보트는 작아서 한 번에 최대 2명씩 밖에 탈 수 없고, 무게 제한도 있습니다.
// 구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.
// 사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때, 
// 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값을 return 하도록 solution 함수를 작성해주세요.

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        // 1. 사람들을 오름차순으로 정렬한다.
        // 2. 포인터를 왼쪽 끝 (최하위 몸무게) , 오른쪽 끝 (최상위 몸무게)로 놔둔다.
        // 3. 포인터 왼쪽을 오른쪽으로 이동한다.
        //  - 오른쪽 포인터를 왼쪽으로 이동하면서 비교하여 limit 안에 들어오면 멈춘다. 
        //  - cnt를 올리고 해당 오른쪽 값은 250으로 만든다.
        
        Arrays.sort(people);
        
        int rt = people.length - 1;
        
        for(int lt=0; lt < people.length; lt++){
            if(people[lt] == 250) break;
            
            int cur = people[lt];
            
            while(lt < rt){
                if(cur + people[rt] <= limit){
                    answer++;
                    people[rt] = 250;
                    people[lt] = 250;
                    break;
                }
                rt--;
            }
        }
        
        for(int i=0; i < people.length; i++){
            if(people[i] != 250) answer++;
        }
        
        return answer;
    }
}