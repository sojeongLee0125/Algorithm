import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Q. 수빈이와 동생의 위치가 주어졌을 때, 동생을 찾을 수 있는 가장 빠른 시간, 경로 구하기.
 * - 수빈이는 현재 점 N(0 ≤ N ≤ 500,000)에 있고, 동생은 점 K(0 ≤ K ≤ 500,000)에 있다.
 * - 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 
 * - 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 * 
 * - 동생은 항상 매 초마다 이동을 하며, 이동은 가속이 붙는다. 
 * - 동생이 이동하는 거리는 이전에 이동한 거리보다 1을 더한 만큼 이동한다.
 * - 동생의 처음 위치는 K, 1초가 지난 후 위치는 K+1, 2초가 지난 후 위치는 K+1+2, 
 * - 3초가 지난 후의 위치는 K+1+2+3이다.
 * - 동생을 찾는 위치는 정수 좌표이어야 하고, 
 * - 수빈이가 0보다 작은 좌표로, 50만보다 큰 좌표로 이동하는 것은 불가능하다.
 */
public class Main {
    static int N, K; 
    static int cnt, flag, MAX = 500000;
    static int[][] dp = new int[2][500005]; // [시간의 홀수, 짝수][좌표]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. 
        // N과 K는 정수이다.
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) System.out.println("0"); // 처음부터 같은 경우
        else {
            // 수빈이가 동생을 찾을 수 없거나, 
            // 찾는 위치가 500,000을 넘는 경우에는 -1을 출력한다.
            go(N);
            if (flag == 0) System.out.println(-1);
            else System.out.println(cnt);
        }
    }

    private static void go(int cur) {
        Queue<Integer> q = new LinkedList<>();
        dp[0][cur] = 1; // 수빈이의 현재 시간과 위치 체크
        q.offer(cur);
        
        cnt = 1; // 동생에게 누적해서 더하는 값 == 시간의 경과
        
        outer:
        while (!q.isEmpty()) {
        	// 동생 위치 갱신
            K += cnt;
            if (K > MAX) break;
            
            // 해당 좌표에 수빈이가 동생보다 먼저 도착한 경우
            // 같은 홀수 or 짝수 시간대 수빈이가 K위치에 방문한 적 있다면 방문 가능.
            // 해당 위치에서 멈춰있기 가능 (왔다갔다 +1 -1)
            if (dp[cnt % 2][K] != 0) {
                flag = 1;
                break outer;
            }
            
            // floodfill
            int curSize = q.size();
            for (int i = 0; i < curSize; i++) {
                int c = q.poll();
                int[] arr = {c + 1, c - 1, c * 2};
                // 수빈이가 현재 위치에서 갈 수 있는 3가지 경우
                for (int nx : arr) {
                    if (nx < 0 || nx > MAX || dp[cnt % 2][nx] != 0) continue;
                    dp[cnt % 2][nx] = dp[(cnt + 1) % 2][c] + 1;
                    if (nx == K) {
                        flag = 1;
                        break outer;
                    }
                    q.offer(nx);
                }
            }
            cnt += 1;
        }
    }
}