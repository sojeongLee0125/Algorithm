import java.util.*;

// 총 n명의 주민이 거주, 각 주민들은 1번부터 n번까지 서로 다른 번호를 가진다.
// 1번 ~ c번 주민 : 악동클럽 회원
// c+1번 ~ n번 주민 : 일반 주민
// 악동클럽 -> 자신의 연락처에 있는 모든 주민들에게 행운의 편지를 한 통씩 보낸다.
// 주민들은 악통 클럽 회원들의 연락처를 모르기 때문에 악동 클럽 회원들이 행운의 편지를 받을 일은 없다.
// 일반 주민들은 행운의 편지를 k통 받을 경우 자신의 연락처에 있는 모든 주민들에게 행운의 편지를 한 통씩 보낸다.
// 다른 사람들에게 한 번씩 보낸 주민은 이후 행운의 편지를 더 받아도 보내지 않는다.

// n : 행복마을 주민의 수
// c : 악동클럽 회원의 수
// k : 일반 주민들이 행운의 편지를 전파하게 되는 최소 편지 수
// 행운의 편지 전파가 더 이상 일어나지 않을 때 행운의 편지를 한 통도 받지 않은 일반 마을 주민의 수를 return

class Solution {
    public int solution(int n, int c, int k, int[][] contact) {
        // contact [a, b] : a번 주민이 b번 주민의 연락처를 가지고 있다
        
        // 1. ArrayList<ArrayList<Integer>> map 만들어서 각 번호 당 편지 보내는 주민을 저장한다.
        // 2. Q에 악통 클럽 회원들을 넣는다.
        // 3. BFS 를 돌리고 편지 받는 회원들을 chk에서 체크한다.
        // 4. q가 비면, 종료하고 chk에서 체크되지 않은 주민의 수를 구한다.

        ArrayList<ArrayList<Integer>> map = new ArrayList<>();

        for(int i=0; i<=n; i++){
            map.add(new ArrayList<>());
        }

        for(int[] arr : contact){
            int from = arr[0];
            int to = arr[1];
            map.get(from).add(to);
        }

        Queue<Integer> q = new LinkedList<>();
        int[] chk = new int[n + 1];

        for(int i=1; i<=c; i++){
            q.add(i);
            chk[i] = 100;
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int nx : map.get(cur)){
                if(chk[nx] == 100) continue;
                chk[nx]++;
                if(chk[nx] >= k){
                    chk[nx] = 100;
                    q.add(nx);
                }
            }
        }

        int answer = 0;
        for(int i=1; i<chk.length; i++) {
            if(chk[i] == 0) answer++;
        }
        
        return answer;
    }
}
