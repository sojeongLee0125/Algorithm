import java.util.*;

// 세균이 증식할 수 있는 rows x columns 격자
// 각 칸은 최대 max_virus마리의 세균이 있을 수 있으며, 초기에는 아무 세균이 없다.
// 하나의 쿼리는 r,c두 정수 / r행 c열에서 세균증식을 하라는 것을 의미

// 세균 증식
// 만약 r,c 칸의 세균이 최댓값 미만이라면 세균을 1마리 늘린다.
// 만약 r,c 칸의 세균이 최댓값이라면 인접한 상하좌우 모든 칸에 세균증식을 시킨다.
// 세균 증식이 여러 칸에 걸쳐 연쇄적으로 일어날 수 있다.
// 한 쿼리에서 동일한 칸에는 최대 한 번의 세균 증식만 일어난다.

// 쿼리들에 의한 세균 증식을 순서대로 실행시켰을 때 격자의 최종 상태를 2차원 정수 배열로 리턴

class Solution {
    int[][] map;
    int[][] chk;
    Queue<int[]> q;

    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};

    public int[][] solution(int rows, int columns, int max_virus, int[][] queries) {
        map = new int[rows][columns];

        // 1. 쿼리의 순서대로 반복문을 만든다.
        // 2. 해당 턴에서 해당 위치의 좌표값을 검사한다.
        // 3. 좌표값이 최댓값일 경우 상하좌우를 큐에 넣는다.
        // 4. 큐에서 값이 최댓값일 경우 그 상하좌우를 큐에 넣는다. 아닐 경우 값만 1 증가시킨다.
        // 5. 큐가 빌 때까지 반복한다.

        for(int[] arr : queries){
            int cy = arr[0] - 1;
            int cx = arr[1] - 1;

            if(map[cy][cx] < max_virus){
                map[cy][cx]++;
                continue;
            }

            q = new LinkedList<>();
            chk = new int[rows][columns];
            q.add(new int[]{cy, cx});
            chk[cy][cx] = 1;

            while(!q.isEmpty()){
                int[] cur = q.poll();
                int y = cur[0];
                int x = cur[1];

                if(map[y][x] < max_virus){
                    map[y][x]++;
                    continue;
                }
  
                for(int k=0; k<4; k++){
                    int ny = y + dy[k];
                    int nx = x + dx[k];
                    if(ny < 0 || ny >= rows || nx < 0 || nx >= columns || chk[ny][nx] == 1) continue;
                    q.add(new int[]{ny, nx});
                    chk[ny][nx] = 1;
                }
                
            }
        }

        return map;
    }
}
