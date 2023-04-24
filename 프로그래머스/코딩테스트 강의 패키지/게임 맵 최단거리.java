import java.util.*;

// 두 팀으로 나누어서 진행하며, 상대 팀 진영을 먼저 파괴하면 이기는 게임
// 각 팀은 상대 팀 진영에 최대한 빨리 도착하는 것이 유리
// 검은색 부분은 벽으로 막혀서 갈 수 없는 길, 흰색 부분은 갈 수 있는 길
// 캐릭터는 동, 서, 남, 북 한 칸씩 이동, 게임 맵을 벗어난 길은 갈 수 없다.
// 상대 팀이 자신 팀 진영 주위에 벽을 세워두면 상대 팀 진영에 도착하지 못할 수도 있다.

// 캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 최솟값을 return
// 상대팀 진영에 도착할 수 없을 때는 -1을 리턴한다.

class Solution {
    int[][] chk;
    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};

    public int solution(int[][] maps) {
        // maps는 0과 1로 이루어져 있으며 0은 벽, 1은 벽이 없는 자리
        // 처음에 캐릭터 위치 (1, 1) 상대방 진영은 (n, m)
        
        int n = maps.length;
        int m = maps[0].length;
        chk = new int[n][m];

        // 1. BFS - (0, 0)에서 출발
        // 2. (n-1, m-1) 위치의 chk값을 구한다.
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        chk[0][0] = 1;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if(ny < 0 || ny >= n || nx < 0 || nx >= m || maps[ny][nx] == 0 || chk[ny][nx] != 0) continue;
                chk[ny][nx] = chk[cy][cx] + 1;
                q.add(new int[]{ny, nx});
            }
        }

        if(chk[n-1][m-1] == 0) return -1;
        else return chk[n-1][m-1];
    }
}
