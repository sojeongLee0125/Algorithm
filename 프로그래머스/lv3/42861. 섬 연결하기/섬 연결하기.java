import java.util.*;

// n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때, 
// 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.

class Solution {
    
    class Node implements Comparable<Node> {
        int cost;
        int num;
        
        public Node(int cost, int num){
            this.cost = cost;
            this.num = num;
        }
        
        // 비용 오름차순
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
    
    public int solution(int n, int[][] costs) {    
        ArrayList<Node>[] tree = new ArrayList[n];
        int[] chk = new int[n];
        
        int answer = 0;
        // Node 자료구조를 만든다.
        // 그래프는 ArrayList<Node>[] 배열로 만든다.
        // PriorityQueue 자료구조를 만들어서 (비용, 노드번호)를 넣는다.
        // MST로 푼다.
        
        for(int i=0; i<n; i++){
            tree[i] = new ArrayList<>();
        }
        
        for(int i=0; i<costs.length; i++){
            int n1 = costs[i][0];
            int n2 = costs[i][1];
            int cost = costs[i][2];
            tree[n1].add(new Node(cost, n2));
            tree[n2].add(new Node(cost, n1));
        }
        
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        // 0번 노드부터 시작
        pq.add(new Node(0, 0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(chk[cur.num] == 1) continue;
            
            chk[cur.num] = 1;
            answer += cur.cost;
            
            for(Node node : tree[cur.num]){
                pq.add(node);
            }
        }
        
        return answer;
    }
}