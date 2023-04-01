import java.util.*;

// 출발지점부터 distance만큼 떨어진 곳에 도착지점이 있습니다. 그리고 그사이에는 바위들이 놓여있습니다. 
// 바위 중 몇 개를 제거하려고 합니다.
// 바위를 n개 제거한 뒤 각 지점 사이의 거리의 최솟값 중에 가장 큰 값을 return 하도록 solution 함수를 작성해주세요.

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        // 범위 : 0 ~ distance;
        // 이분탐색 방법 : mid 값을 가지고 해당 값을 최소값이라고 정했을 때 지워야 하는 바위수를 n과 비교한다.
        
        // 바위를 위치 순서대로 정렬한다.
        Arrays.sort(rocks);
        int lt = 0;
        int rt = distance;
        
        // mid 값이 최소값이라고 했을 때 mid보다 작은 거리를 가진 바위는 삭제해야한다.
        while(lt <= rt){
            int cnt = 0;
            int cur = 0; // 제거한 바위수
            int mid = (lt + rt) / 2;
            
            for(int i=0; i<rocks.length; i++){
                if(rocks[i] - cur < mid){
                    // 제거해야한다.
                    cnt++;
                }else{
                    // 그대로 놔두는 바위이므로 cur를 갱신한다.
                    cur = rocks[i];
                }
            }
            
            // 지워야 하는 바위수가 지울 수 있는 바위수보다 큰 경우
            if(cnt > n){
                // 최소 거리를 줄인다.
                rt = mid - 1;
            }else{
                answer = Math.max(mid, answer);
                lt = mid + 1;
            }
        } // while 종료
                         
        return answer;
    } // sol 메서드
} //class