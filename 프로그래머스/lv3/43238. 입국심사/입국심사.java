import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        // 1. 최소 시간 ~ 최대 시간 범위를 설정하여 이분 탐색을 진행한다.
        // 2. mid 값을 구한다.
        // 3. 타임 배열을 순회하며 mid 값을 해당 타임으로 나눈 몫을 sum에 더해준다.
        // 4. 만약 sum 이 n보다 크거나 같다면 최소값을 갱신하고, 범위를 mid-1로 바꿔준다.
        // 5. 반대의 경우 범위를 mid+1 ~ ed로 범위를 바꿔준다.
        
        Arrays.sort(times);
        
        long st = times[0];
        long ed = (long) times[times.length-1] * n; // n명이 모두 최대 시간 검사대 검사
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