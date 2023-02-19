import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Q. 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 * - 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
 * - 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 */
public class Main {
    static int N, K;
    static int[] chk = new int[200005];
    static int[] map = new int[200005];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chk[N] = 1;
        map[N] = -1;
        go(N);

        // 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
        System.out.println(chk[K] - 1);
        // 둘째 줄에 어떻게 이동해야 하는지 공백으로 구분해 출력한다.
        StringBuilder sb = new StringBuilder();
        for (int i = K; i != N; i = map[i]) {
            sb.insert(0, i + " ");
        }
        sb.insert(0, N + " ");
        System.out.println(sb);
    }

    private static void go(int cur) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(cur);
        while (!q.isEmpty()) {
            int c = q.poll();
            if (c == K) break;
            int[] arr = {c + 1, c - 1, c * 2};
            for (int nx : arr) {
                if (nx < 0 || nx > 200000) continue;
                if (chk[nx] != 0) continue;
                chk[nx] = chk[c] + 1;
                map[nx] = c;
                q.offer(nx);
            }
        }
    }

}