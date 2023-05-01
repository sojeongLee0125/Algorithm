import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
                
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
}