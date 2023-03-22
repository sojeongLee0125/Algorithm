import java.util.*;

// 고속도로를 이동하는 차량의 경로 routes가 매개변수로 주어질 때, 
// 모든 차량이 한 번은 단속용 카메라를 만나도록 하려면 
// 최소 몇 대의 카메라를 설치해야 하는지를 return 하도록 solution 함수를 완성하세요.

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        // 루트를 종점기준으로 오름차순 정렬한다.
        // 처음 카메라 위치는 - 30001
        // 현재 진입점 기준으로 카메라 위치보다 앞에 있으면 answer++; 하고 카메라 위치는 현재 종점으로 잡는다.
        
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] r1, int[] r2) {
                return r1[1] - r2[1];
            }
        });
        
        int p = -30001;
        for(int[] cur : routes){
            int st = cur[0];
            int ed = cur[1];
            if(st <= p) continue;
            else{
                answer++;
                p = ed; 
            }
        }
        
        return answer;
    }
}