import java.util.*;

// 각 던전마다 탐험을 시작하기 위해 필요한 "최소 필요 피로도"와 던전 탐험을 마쳤을 때 소모되는 "소모 피로도"가 있습니다.
// 하루에 한 번씩 탐험할 수 있는 던전이 여러개 있는데, 한 유저가 오늘 이 던전들을 최대한 많이 탐험하려 합니다. 
// 유저의 현재 피로도 k와 각 던전별 "최소 필요 피로도", "소모 피로도"가 담긴 2차원 배열 dungeons 가 매개변수로 주어질 때, 
// 유저가 탐험할수 있는 최대 던전 수를 return 하도록 solution 함수를 완성해주세요.
// 서로 다른 던전의 ["최소 필요 피로도", "소모 피로도"]가 서로 같을 수 있습니다.

class Solution {
    int total = 0;
    int ans = 0;
    int[] chk = new int[10];
    
    public void go(int cnt, int k, int[][] dungeons){ 
        for(int i=0; i<total; i++){
            if(chk[i] != 0) continue;
            if(dungeons[i][0] > k) continue;
            chk[i] = 1;
            ans = Math.max(ans, cnt + 1);
            go(cnt + 1, k - dungeons[i][1], dungeons);
            chk[i] = 0;
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        total = dungeons.length;
        // 순열문제
        // 모든 경우의 수의 순서대로 던전을 돌면서 진행이 가능할 경우 던전 갯수를 갱신한다.
        go(0, k, dungeons);
        return ans;
    }
}