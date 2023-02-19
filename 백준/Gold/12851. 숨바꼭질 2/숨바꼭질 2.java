import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Q. 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 그리고,
 * 가장 빠른 시간으로 찾는 방법이 몇 가지 인지 구하는 프로그램을 작성하시오.
 * - 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
 * - 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
 * - 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 */
public class Main {
    static int N, K;
    static int[] cnt = new int[200005];
    static int[] chk = new int[200005];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
        // 둘째 줄에는 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력한다.

        // 현재 위치와 도착위치가 같은 경우 처리
        if (N == K) {
            System.out.println(0);
            System.out.println(1);
        } else {
            // 해결방안 : 큐를 사용한다.
            chk[N] = 1;
            cnt[N] = 1;
            go(N);
            System.out.println(chk[K] - 1);
            System.out.println(cnt[K]);
        }
    }

    private static void go(int cur) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(cur);
        while (!q.isEmpty()) {
            int c = q.poll();
            int[] dy = {c + 1, c - 1, c * 2};
            for (int nx : dy) {
                if (nx < 0 || nx > 200000) continue;
                if (chk[nx] == 0) {
                    q.offer(nx);
                    chk[nx] = chk[c] + 1;
                    cnt[nx] += cnt[c];
                    // 이미 방문한 경로지만 가장 빠른 시간이 같은 경우
                } else if (chk[nx] == chk[c] + 1) {
                    cnt[nx] += cnt[c];
                }
            }
        }
    }

}