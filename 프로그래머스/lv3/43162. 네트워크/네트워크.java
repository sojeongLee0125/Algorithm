import java.util.*;

// 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 
// 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

class Solution {
    ArrayList<Integer>[] tree;
    int[] chk;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        // tree를 구성하고 dfs를 도는 횟수를 구한다.
        
        tree = new ArrayList[n];
        chk = new int[n];
        
        for(int i=0; i<n; i++) tree[i] = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(computers[i][j] == 0 || i == j) continue;
                tree[i].add(j);
            }
        }
            
        for(int i=0; i<n; i++){
            if(chk[i] == 0){
                answer++;
                dfs(i);
            }
        }
            
        return answer;
    }
    
    public void dfs(int cur){
        chk[cur] = 1;
        
        for(int nx : tree[cur]){
            if(chk[nx] == 0) dfs(nx);
        }
        
    }
    
}