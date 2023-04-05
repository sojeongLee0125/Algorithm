import java.util.*;

class Solution {
    int[][] map;
    int[] chk;
    int ret = 105;
    int N;
    
    public int solution(int n, int[][] wires) {
        N = n;
        
        // 1.전선 형성
        map = new int[n + 1][n + 1];
        
        for(int[] arr : wires){
            map[arr[0]][arr[1]] = 1;
            map[arr[1]][arr[0]] = 1;
        }
        
        // 2. 전선 차례대로 하나씩 끊으면서 체크
        for(int i=0; i<wires.length; i++){
            // 끊기
            map[wires[i][0]][wires[i][1]] = 0;
            map[wires[i][1]][wires[i][0]] = 0;
            
            // 현 bfs로 체크
            int cnt = 0;
            int node = 0;
            chk = new int[n+1];
            
            // 1번부터 n번 노드까지 bfs
            for(int j=1; j<=n; j++){
                if(chk[j] == 1) continue;
                cnt++;
                node = bfs(j);
            }
            
            if(cnt != 2) continue;
            ret = Math.min(ret, Math.abs((N - node) - node));

            // 원복
            map[wires[i][0]][wires[i][1]] = 1;
            map[wires[i][1]][wires[i][0]] = 1;    
        }
                
        return ret;
    }
    
    public int bfs(int n){
        int rs = 1;
        
        chk[n] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        
        while(!q.isEmpty()){
            int c = q.poll();
            
            for(int i=1; i<=N; i++){
                if(i == c) continue;
                if((chk[i] == 0) && (map[c][i] == 1)){
                    chk[i] = 1;
                    q.add(i);
                    rs++;
                }
            }
        }
        
        return rs;
    }

}