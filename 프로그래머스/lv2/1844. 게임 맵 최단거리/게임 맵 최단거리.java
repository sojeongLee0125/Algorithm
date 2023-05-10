import java.util.*;

// 게임 맵의 상태 maps가 매개변수로 주어질 때, 캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 
// 최솟값을 return 하도록 solution 함수를 완성해주세요. 단, 상대 팀 진영에 도착할 수 없을 때는 -1을 return 해주세요.

class Solution {
    int[][] chk;
    int[][] map;
    int n,m;
    
    public int solution(int[][] maps) {
        int answer = 0;     
        map = maps;
        
        n = maps.length;
        m = maps[0].length;
        
        // 1. 맵을 구성한다.
        // 2. 거리용 맵을 구성한다.
        // 3. bfs를 돌리면 거리용 맵을 갱신한다.
        // 4. chk[n][m] 의 값을 출력한다. 만약 도착할 수 없으면 -1을 리턴한다.
        
        chk = new int[n][m];
        for(int i=0; i<n; i++) Arrays.fill(chk[i], Integer.MAX_VALUE);
        
        chk[0][0] = 1;
        bfs(0,0);
        
        if(chk[n-1][m-1] == Integer.MAX_VALUE) answer = -1;
        else answer = chk[n-1][m-1];
        
        return answer;
    }
    
    public void bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        
        while(!q.isEmpty()){
            int[] c = q.poll();
            int cy = c[0];
            int cx = c[1];
            
            for(int k=0; k<4; k++){
                int ny = cy + dy[k];
                int nx = cx + dx[k];
                if(ny < 0 || nx < 0 || ny >= n || nx >= m || chk[ny][nx] != Integer.MAX_VALUE) continue;
                if(map[ny][nx] == 0) continue;
                chk[ny][nx] = chk[cy][cx] + 1;
                q.add(new int[]{ny, nx});
            }
        }
    }
}
