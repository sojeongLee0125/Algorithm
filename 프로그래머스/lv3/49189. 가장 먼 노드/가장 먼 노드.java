import java.util.*;

// 노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, 
// 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

class Solution {
    ArrayList<Integer>[] tree;
    int N;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        N = n;
        // 그래프 구조를 만든다.
        tree = new ArrayList[n + 1];
        for(int i=0; i<=n; i++) tree[i] = new ArrayList<>();
        
        for(int[] arr : edge){
            int a = arr[0];
            int b = arr[1];
            tree[a].add(b);
            tree[b].add(a);
        }
        
        // Q를 활용해서 FLOOD FILL
        // Q마다 해당 거리 노드의 카운트를 기록한다.
        // Q를 탈출 할 때 마지막 카운트를 리턴한다.
        
        return bfs(1);
    }
    
    public int bfs(int v){
        int[] chk = new int[N + 1]; // 노트 방문 체크
        
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        chk[v] = 1;
        
        int cnt = 0;
        int max = 0;
        while(!q.isEmpty()){
            int s = q.size();
            for(int i=0; i<s; i++){
                int num = q.poll();
                for(int nx : tree[num]){
                    if(chk[nx] != 0) continue;
                    chk[nx] = chk[num] + 1;
                    max = Math.max(chk[nx], max);
                    q.add(nx);
                }
            }
        }
        
        int ans = 0;
        for(int i=1; i<=N; i++){
            if(chk[i] == max) ans++;
        }
    
        return ans;    
    }
}