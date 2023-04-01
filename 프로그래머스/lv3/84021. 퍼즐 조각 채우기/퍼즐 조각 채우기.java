import java.util.*;

// 테이블 위에 놓인 퍼즐 조각을 게임 보드의 빈 공간에 적절히 올려놓으려 합니다. 
// 게임 보드와 테이블은 모두 각 칸이 1x1 크기인 정사각 격자 모양입니다. 
// 조각을 회전시킬 수 있습니다. 조각을 뒤집을 수는 없습니다.
// 규칙에 맞게 최대한 많은 퍼즐 조각을 채워 넣을 경우, 총 몇 칸을 채울 수 있는지 return 하도록 solution 함수를 완성해주세요.

class Solution {
    
    ArrayList<String> empty = new ArrayList<>(); // 빈칸 리스트 저장
    int N; // 행의 수
    int[] dy = {-1, 0, 1, 0}; // 위 오 아 왼
    int[] dx = {0, 1, 0, -1};
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        N = game_board.length;
        
        // 빈 공간들 -> NXN 좌표 문자열로 변환하여 저장하기
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(game_board[i][j] == 0){
                    String str = bfs(game_board, i, j, 0);
                    empty.add(str);
                }
            }
        }
             
        // 퍼즐 조각들 -> NXN 좌표 문자열로 변환하여 빈 공간 문자열과 비교하기
        // 비교하여 빈 공간에 맞으면 해당 퍼즐 조각만큼 정답에 더해주기
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(table[i][j] == 1){
                    int cnt = find(bfs(table, i, j, 1));
                    answer += cnt;
                }
            }
        }
        
        return answer;
    }
    
    public int find(String str){
        int cnt = 0; // 해당 블록의 갯수
        
        for(int i=0; i< str.length(); i++){
            if(str.charAt(i) == '1') cnt++; // 블록의 갯수 먼저 세기
        }
        
        for(int i=0; i< empty.size(); i++){
            String tmp = empty.get(i); // 빈 공간 문자열 하나씩 가져와서 비교
            
            // 4방향 회전 체크
            for(int k=0; k<4; k++){
                tmp = rotate(tmp); // 공간을 4방향으로 회전하기
                
                // 빈공간과 블록이 일치할 경우
                if(str.equals(tmp)){
                    empty.remove(i); // 해당 빈 칸을 리스트에서 제거
                    return cnt;
                }
            }
        }
        
        return 0; // 4방향 하나도 맞지 않는 경우
    }
    
    public String rotate(String str){
        StringBuilder sb = new StringBuilder();
        String[] arr = str.split(" ");
        
        StringTokenizer st = new StringTokenizer(str);
        
        for(int j=0; j<arr[0].length(); j++){
            for(int i = arr.length-1; i >= 0; i--){
                sb.append(arr[i].charAt(j)); // 거꾸로 붙이기
            }
            sb.append(" ");
        }
        
        return sb.toString();
    }
    
    public String bfs(int[][] board, int y, int x, int bit){
        StringBuilder sb = new StringBuilder();
        
        Queue<int[]> q = new LinkedList<>();
        int[][] chk = new int[N][N]; // 방문체크
        
        // 직사각형의 모양으로 블록 정보를 저장한다.
        int max_x = x; // x좌표 최소값
        int min_x = x; // x좌표 최댓값
        int max_y = y; // y좌표 최소값
        int min_y = y; // y좌표 최댓값
        
        chk[y][x] = 1;
        board[y][x] = (bit + 1) % 2; // 0 <> 1 전환해서 저장
        q.add(new int[]{y, x});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            int cy = cur[0];
            int cx = cur[1];
            
            max_y = Math.max(max_y, cy);
            min_y = Math.min(min_y, cy);
            max_x = Math.max(max_x, cx);
            min_x = Math.min(min_x, cx);
            
            // 상하좌우 탐색
            for(int k=0; k<4; k++){
                int ny = cy + dy[k];
                int nx = cx + dx[k];
                if(ny < 0 || nx < 0 || ny >= N || nx >= N || chk[ny][nx] != 0) continue;
                
                // 같은 블록
                if(board[ny][nx] == bit){
                    chk[ny][nx] = 1;
                    board[ny][nx] = (bit + 1) % 2; // 0 <> 1 전환해서 저장
                    q.add(new int[]{ny, nx});
                }
            }
        }
        
        for(int i = min_y; i <= max_y; i++){
            for(int j = min_x; j <= max_x; j++){
                if(chk[i][j] == 1) sb.append("1");
                else sb.append("0");
            }
            sb.append(" ");
        }
        
        return sb.toString();
    }
}
