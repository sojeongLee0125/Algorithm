import java.util.*;

// n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다. 
// 권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다.
// 심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다. 
// 하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.
// 선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때 
// 정확하게 순위를 매길 수 있는 선수의 수를 return 하도록 solution 함수를 작성해주세요.

class Solution {
    int[][] arr;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        arr = new int[n][n];
        
        for(int[] ar :results){
            int a = ar[0] - 1;
            int b = ar[1] - 1;
            arr[a][b] = 1;
        }
        
        // 해당 선수는 n-1개의 선수와의 경기 결과를 알면 정확한 순위를 알 수 있다.
        // 플로이드-와샬 : 순회하면서 i번 선수를 통해 이어지는 결과를 업데이트 한다.
        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    // i와 j에 대한 정보가 없을 때 i가 k를 이기고 k가 j를 이기면 i는 j를 이긴다.
                    if(arr[i][j] == 0 && arr[i][k] == 1 && arr[k][j] == 1) arr[i][j] = 1;
                }
            }
        }
        
        // 해당 선수의 행과 열을 검토하여 n-1개의 1이 발견되면 순위를 알 수 있다.
        int[] col = new int[n];
        int[] row = new int[n];
        
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                col[c] += arr[r][c];
                row[r] += arr[r][c];
            }
        }
        
        int cnt = 0;
        for(int i=0; i<n; i++) {
            if(col[i] + row[i] == n-1) cnt++;
        }
        
        return cnt;
    }
}