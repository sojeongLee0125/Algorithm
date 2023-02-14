import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 컴퓨터의 신뢰하는 관계가 주어졌을 때, 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성
 * - 이 회사는 N개의 컴퓨터로 이루어져 있다.
 * - A가 B를 신뢰하는 경우에는 B를 해킹하면, A도 해킹할 수 있다.
 */
public class Main {
    static int N, M, max;
    static int[] chk;
    static int[] dp;
    static ArrayList<Integer>[] tree;
    static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에, N과 M이 들어온다. N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        dp = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 둘째 줄부터 M개의 줄에 신뢰하는 관계가 A B와 같은 형식으로 들어오며, "A가 B를 신뢰한다"를 의미한다.
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            tree[B].add(A);
        }

        for (int i = 1; i <= N; i++) {
            chk = new int[N + 1];
            dp[i] = hack(i);
            max = Math.max(dp[i], max);
        }

        // 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순으로 출력한다.
        for (int i = 1; i <= N; i++) {
            if (dp[i] == max) System.out.print(i + " ");
        }
    }

    private static int hack(int node) {
        chk[node] = 1;
        int rs = 1;
        for (int child : tree[node]) {
            if (chk[child] == 1) continue;
            rs += hack(child);
        }
        return rs;
    }

}