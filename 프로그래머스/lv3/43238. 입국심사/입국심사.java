import java.util.*;

// n명이 입국심사를 위해 줄을 서서 기다리고 있습니다. 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다.
// 모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶습니다.
// 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하도록 solution 함수를 작성해주세요.

class Solution {
    public long solution(int n, int[] times) {
        // 1. 최소 시간 ~ 최대 시간 범위를 설정하여 이분 탐색을 진행한다.
        // 2. mid 값을 구한다.
        // 3. 타임 배열을 순회하며 mid 값을 해당 타임으로 나눈 몫을 sum에 더해준다.
        // 4. 만약 sum 이 n보다 크거나 같다면 최소값을 갱신하고, 범위를 mid-1로 바꿔준다.
        // 5. 반대의 경우 범위를 mid+1 ~ ed로 범위를 바꿔준다.
        
        Arrays.sort(times);
        
        long st = times[0];
        long ed = (long) times[times.length-1] * n; // n명이 모두 최대 시간 검사대에서 검사한 경우
        long answer = ed;
        
        while(st <= ed){
            long sum = 0;
            long mid = (st + ed) / 2;
            
            for(int t : times){
                sum += mid / t; // 전체 시간에서 입국심사 시간들을 다 나눠준다.
            }
            
            if(sum >= n){
                //가능한 경우
                ed = mid-1;
                answer = Math.min(mid, answer);
            }else{
                st = mid+1;
            }
        }
        
        return answer;
    }
}