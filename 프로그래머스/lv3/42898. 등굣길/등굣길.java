import java.util.*;

// 격자의 크기 m, n과 물이 잠긴 지역의 좌표를 담은 2차원 배열 puddles이 매개변수로 주어집니다. 
// 오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단경로의 개수를 
// 1,000,000,007로 나눈 나머지를 return 하도록 solution 함수를 작성해주세요.
class Solution {
    public int solution(int m, int n, int[][] puddles) { 
        // n : 행 m : 열 
        // 좌표는 (m, n)으로 나타냅니다. 
        
        int[][] map = new int[n + 1][m + 1];
        
        // 물 웅덩이는 -1로 저장
        for(int[] arr : puddles){
            int y = arr[1];
            int x = arr[0];
            map[y][x] = -1;
        }
        
        // 해당 좌표로 가려면 위에서 오거나 왼쪽에서 오는 2가지 경우밖에 없다.
        map[1][1] = 1;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(map[i][j] == -1) continue;
                // 위에서 오는 경우
                if(map[i-1][j] != -1) map[i][j] += (map[i-1][j] % 1_000_000_007);
                // 왼쪽에서 오는 경우
                if(map[i][j-1] != -1) map[i][j] += (map[i][j-1] % 1_000_000_007);
            }
        }
        
        return map[n][m] % 1_000_000_007;
    }
}