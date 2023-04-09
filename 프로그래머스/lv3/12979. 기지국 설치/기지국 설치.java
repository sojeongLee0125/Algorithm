import java.util.*;

class Solution {

    public int calc(int st, int ed, int w){
        int ret = (ed - st + 1) / (2 * w + 1);
        int res = (ed - st + 1) % (2 * w + 1) == 0 ? 0 : 1;
        return ret + res;
    }

    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1;

        for(int a : stations){
            if(start < a - w) answer += calc(start, a - w - 1, w);
            start = a + w + 1;
        }

        // 마지막 처리
        if(stations[stations.length - 1] + w < n){
            answer += calc(stations[stations.length - 1] + w + 1, n, w);
        }

        return answer;
    }
}