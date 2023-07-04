import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 세준이가 알고 있는 일부 사건들의 전후 관계들이 주어질 때,
 * 주어진 사건들의 전후 관계도 알 수 있을까?
 * 이를 해결하는 프로그램을 작성해 보도록 하자.
 */
public class Main {
    static int N, K, S;
    static int[][] orders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 첫 줄에 사건의 개수 n(400 이하의 자연수)과
        // 알고 있는 사건의 전후 관계의 개수 k(50,000 이하의 자연수)가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        orders = new int[N + 1][N + 1];

        // 다음 k 줄에는 전후 관계를 알고 있는 두 사건의 번호가 주어진다.
        // 이는 앞에 있는 번호의 사건이 뒤에 있는 번호의 사건보다 먼저 일어났음을 의미한다.
        // 물론 사건의 전후 관계가 모순인 경우는 없다.
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int prev = Integer.parseInt(st.nextToken());
            int nxt = Integer.parseInt(st.nextToken());
            orders[prev][nxt] = -1;
            orders[nxt][prev] = 1;
        }

        // 플로이드 워셜 알고리즘 적용
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (orders[i][k] == 1 && orders[k][j] == 1) orders[i][j] = 1;
                    else if (orders[i][k] == -1 && orders[k][j] == -1) orders[i][j] = -1;
                }
            }
        }

        // 다음에는 사건의 전후 관계를 알고 싶은 사건 쌍의 수 s(50,000 이하의 자연수)이 주어진다.
        // 다음 s줄에는 각각 서로 다른 두 사건의 번호가 주어진다.
        // 사건의 번호는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.
        S = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int case1 = Integer.parseInt(st.nextToken());
            int case2 = Integer.parseInt(st.nextToken());

            // s줄에 걸쳐 물음에 답한다.
            // 각 줄에 만일 앞에 있는 번호의 사건이 먼저 일어났으면 -1,
            // 뒤에 있는 번호의 사건이 먼저 일어났으면 1,
            // 어떤지 모르면(유추할 수 없으면) 0을 출력한다.
            sb.append(orders[case1][case2]).append("\n");
        }

        System.out.println(sb);
    }
}