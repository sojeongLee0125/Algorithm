import java.util.*;

class Solution {
    int[] chk;
    int[][] map;
    int cnt = 0;
    
    public int solution(int k, int[][] dungeons) {
        chk = new int[dungeons.length]; // 던전 순열 방문 체크
        map = dungeons;
        
        go(0, k);
        
        return cnt;
    }
    
    public void go(int idx, int hp){
        cnt = Math.max(idx, cnt);
        
        for(int i=0; i<map.length; i++){
            if(chk[i] == 1) continue;
            if(map[i][0] > hp) continue;
            chk[i] = 1; //방문체크
            go(idx+1, hp - map[i][1]);
            chk[i] = 0;
        }
    }
}