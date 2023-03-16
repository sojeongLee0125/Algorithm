import java.util.*;

// n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다. 
// 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 
// 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.

class Solution {
    int[][] map;
    int[][] chk;
    int answer = 105;
    
    public int bfs(int i){
        int cnt = 1;     
        int check[] = new int[map.length];
        
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        check[i] = 1;
        
        while(!q.isEmpty()){
            int num = q.poll();
            for(int x=1; x < map[num].length; x++){
                if(num == x) continue;
                if(map[num][x] == 1 && check[x] == 0){
                    check[x] = 1;
                    cnt++;
                    q.add(x);
                }
            }
        }
    
        return cnt;    
    }
    
    public int solution(int n, int[][] wires) {
        map = new int[n + 1][n + 1];
        chk = new int[n + 1][n + 1];
        
        // map[][] 배열을 받아서 연결된 경우 ij 와 ji를 모두 1로 저장한다.
        // map을 완전탐색하면서 ij ji를 0으로 만들고 각각 dfs를 통해 한 찾을 수 있는 노드의 갯수와 
        // 전체 노드 - 찾은 노드 갯수 = 나머지 노드
        // 차이의 값을 갱신한다.
        
        for(int[] arr : wires){
            int i = arr[0];
            int j = arr[1];
            map[i][j] = 1;
            map[j][i] = 1;
        }
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i == j) continue;
                if(map[i][j] == 1 && chk[i][j] == 0){
                    chk[i][j] = 1;
                    chk[j][i] = 1;
                    // 선을 끊기
                    map[i][j] = 0;
                    map[j][i] = 0;
                    int count = bfs(i);
                    int diff = Math.abs((n - count) - count);
                    answer = Math.min(answer, diff);
                    // 선을 다시 복구
                    map[i][j] = 1;
                    map[j][i] = 1;
                }
            }
        }
        
        return answer;
    }
}