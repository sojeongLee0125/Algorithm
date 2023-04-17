import java.util.*;

// 계속되는 폭우로 일부 지역이 물에 잠겼습니다. 물에 잠기지 않은 지역을 통해 학교를 가려고 합니다. 
// 집에서 학교까지 가는 길은 m x n 크기의 격자모양으로 나타낼 수 있습니다. (m : 열 / n : 행)
// 가장 왼쪽 위, 즉 집이 있는 곳의 좌표는 (1, 1)로 나타내고 가장 오른쪽 아래, 즉 학교가 있는 곳의 좌표는 (m, n)

// 오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단경로의 개수를 1,000,000,007로 나눈 나머지를 return 하도록 
// solution 함수를 작성해주세요.

class Solution {
    int[][] map;
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        map = new int[n][m];
        
        for(int[] arr : puddles){
            int x = arr[0] - 1;
            int y = arr[1] - 1;
            map[y][x] = -1;
        }
        
        map[0][0] = 1;
        
        // 왼쪽에서 오거나 위쪽에서 오는 경우만 존재한다.
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] != 0) continue;
                // 왼쪽에서 오는 경우
                if(i - 1 >= 0 && (map[i-1][j] != -1)) map[i][j] += map[i-1][j] % 1000000007;
                // 위쪽에서 오는 경우
                if(j - 1 >= 0 && (map[i][j-1] != -1)) map[i][j] += map[i][j-1] % 1000000007;
            }
        }
    
        return map[n-1][m-1] %= 1000000007;
    }
}