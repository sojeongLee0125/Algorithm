import java.util.*;

// 지형을 나타내는 직사각형이 담긴 2차원 배열 rectangle, 
// 초기 캐릭터의 위치 characterX, characterY, 
// 아이템의 위치 itemX, itemY가 solution 함수의 매개변수로 주어질 때, 
// 캐릭터가 아이템을 줍기 위해 이동해야 하는 가장 짧은 거리를 return 하도록 solution 함수를 완성해주세요.

class Solution {
    
    int[][] map = new int[101][101];
    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0 ,-1};
    int sy, sx, ey, ex;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        // 좌표를 2배로 확장한다.
        sy = characterY * 2;
        sx = characterX * 2;
        ey = itemY * 2;
        ex = itemX * 2;
        
        // 사각형 테두리를 표시한다.
        for(int i=0; i<rectangle.length; i++){
            int[] cur = rectangle[i];
            
            int ly = (2 * cur[1]); 
            int lx = (2 * cur[0]); 
            
            int ry = (2 * cur[3]);
            int rx = (2 * cur[2]); 
            
            // 그래프 색칠하기
            for(int y=ly; y<=ry; y++){
                for(int x=lx; x<=rx; x++){
                    if(map[y][x] == 1) continue;
                    map[y][x] = 1;
                    if(y == ly || y == ry || x == lx || x == rx) map[y][x] = 2;
                }
            }
        }  
        
        return bfs();
    }
    
    public int bfs(){
        int ans = 0;
        int[][] chk = new int[101][101];
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sy, sx, 0});
        chk[sy][sx] = 1;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            int cnt = cur[2];
            
            if(cy == ey && cx == ex){
                return (cnt / 2);
            }
            
            for(int k=0; k<4; k++){
                int ny = cy + dy[k];
                int nx = cx + dx[k];
               
                if(ny < 0 || nx < 0 || ny >= 101 || nx >= 101) continue;
                if(chk[ny][nx] == 1 || map[ny][nx] != 2) continue;
                chk[ny][nx] = 1;
                q.add(new int[]{ny, nx, cnt + 1});
            }
        }
        
        return ans;
    }
}