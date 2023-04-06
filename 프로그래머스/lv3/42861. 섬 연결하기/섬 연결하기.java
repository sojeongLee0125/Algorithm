import java.util.*;

class Solution {
    
    ArrayList<int[]>[] tree;
    
    PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[0] - a2[0]);
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int[] chk = new int[n];
        
        tree = new ArrayList[n];        
        for(int i=0; i<n; i++) tree[i] = new ArrayList<>();
        
        // 트리 구조를 만든다.
        for(int[] arr : costs){
            int v1 = arr[0];
            int v2 = arr[1];
            int w = arr[2];
            tree[v1].add(new int[]{w, v2});
            tree[v2].add(new int[]{w, v1});
        }
        
        // 0번부터 넣는다.
        chk[0] = 1;
        for(int[] a : tree[0]){
            pq.add(a);
        }
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            
            int cw = cur[0];
            int nx = cur[1];
            
            if(chk[nx] != 0) continue;
            chk[nx] = 1;
            answer += cw;
            
            for(int[] a : tree[nx]){
                if(chk[a[1]] == 0) pq.add(a);
            }
        }
        
        return answer;
    }
}