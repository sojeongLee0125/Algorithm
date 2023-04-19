import java.util.*;

// 빨간색 방울과 초록색 방울로 구성된 트리장식
// 한 줄로 연결되어 있는데 이중 일부를 잘라 트리를 장식하려 한다.
// 장식에 사용하는 빨간색 방우로가 초록색 방울의 개수를 동일하게 사용한다.
// 장식의 길이를 가장 길게 잘라 트리를 꾸미려고 한다.
// 1은 빨간색 장식을 2는 초록색 장식을 의미한다.
// 장식의 길이의 최댓값을 리턴하도록 함수를 작성한다.

class Solution {
    HashMap<Integer, Integer> map = new HashMap<>(); // 누적합 : 해당 누적합의 최초 등장 인덱스

    public int solution(int[] bell) {
        int answer = 0;

        for(int i=0; i<bell.length; i++){
            if(bell[i] == 2) bell[i] = -1;
        }

        map.put(0, -1);
        map.put(bell[0], 0);
        int preSum = bell[0];
        
        for(int i=1; i<bell.length; i++){
            preSum += bell[i];
            if(!map.containsKey(preSum)){
                map.put(preSum, i); // 해당 누적합이 최초 등장이라면 해당 누적합의 인덱스를 map에 저장
            }else{
                // 해당 누적합이 이전에 등장한 적 있다면 그 누적합 앞까지가 두 방울이 갯수가 같은 경우
                answer = Math.max(answer, i - map.get(preSum));
            }
        }
        
        return answer;
    }
}
