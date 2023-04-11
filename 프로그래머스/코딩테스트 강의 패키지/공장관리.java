import java.util.*;

class Solution {
    public long solution(int goal, int[] durations) {
        long answer = 0;
        long time = 0; // 전체 걸리는 시간
        int maxDate = 0; // 가장 오래 걸리는 시간

        for(int a : durations){
            maxDate = Math.max(maxDate, a);
        }

        long lt = 0;
        long rt = maxDate * (goal / durations.length + 1L); 

        while(lt < rt){
            long mid = (lt + rt) / 2;
            
            if(isOk(mid, durations, goal)){
                // 시간을 더 줄인다.
                 rt = mid;
                 time = mid;
            }else lt = mid + 1;
        }

        // 1. 최소 생산량의 생산 갯수 
        long minNum = time / maxDate;

        for(int a : durations){
            long cur = time / a;
            answer += (cur - minNum);
        }
    
        return answer * 10000;
    }

    public boolean isOk(long mid, int[] durations, int goal){
        int total = 0;

        for(int a : durations){
            total += (mid / a);
        }

        return total >= goal;
    }

}
