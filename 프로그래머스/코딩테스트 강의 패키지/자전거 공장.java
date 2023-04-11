import java.util.*;

class Solution {    
    public int solution(int[][] cost, int[][] order) {
        int answer = 0;
        int due = 0;
        for(int[] arr : order) due = Math.max(due, arr[0]); // 마지막 날짜 구하기
        
        int[] orr = new int[due]; // 날짜별 공급량
        int totalNeed = 0; // 총 필요한 전기
        int made = 0; // 현재까지 만들어진 전기
        
        // 각 월별 납품량 업데이트
        for(int[] arr : order){
            orr[arr[0] - 1] += arr[1]; // 같은 월이 있을 수도 있다.
            totalNeed += arr[1];
        }
        
        // 가장 낮은 비용부터 차례대로 전기를 만든다.    
        for(int i=0; i < cost.length - 1; i++){ 
            
            // 총 필요한 전기보다 만들어진 전기가 많을 경우, 모든 전기 공급 완료된 경우 break;
            if(totalNeed == 0 || totalNeed <= made) break; 
            
            int pay = cost[i][1]; // 현재 구간의 가격
            int range = cost[i+1][0] - cost[i][0]; // 현재 구간의 범위값
            int rest = 0; // 현재 구간에서 공급하고 남은 값
            
            for(int j=0; j<due; j++){
                if(totalNeed == 0 || totalNeed <= made) break;
                
                // 현재 생산해야 하는 양 : 현재 범위의 최대값과 남은 값중 작은 값
                int cur = Math.min(range, totalNeed - made);
                
                // 현재 생산량 정답에 추가
                answer += cur * pay;
                made += cur;
                
                // 이번달 납품량 없으면 패스
                if(orr[j] == 0) continue;
                
                // 전체 필요량에서 현재 납품양 제거
                totalNeed -= orr[j];
                
                // 현재 날짜에서 납품할 수 있는 양 납품
                int del = Math.min(made, orr[j]); // 납품량
                
                made -= del;
                orr[j] -= del;
                rest += orr[j]; // 현재 회차에서 납품하고 남은 양은 다음 구간에 만들어야 한다.
            }
            
            totalNeed = rest;
            made = 0;
        }
        
        // 최종 구간에 대한 값이 존재할 경우
        answer += totalNeed * cost[cost.length-1][1];
        return answer;
    }
}
