import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Q. 학생 X의 등수 범위를 찾아서 출력하기.
 * - KOI 본선 대회에 N명의 학생이 참가했다. 
 * - 이 학생들을 각각 1부터 N까지 정수로 표현하자.
 * - 대회가 끝나고 성적을 발표하는데, 
 * - 이 대회는 전체 학생의 등수를 발표 하는 대신,
 * - 두 학생 A, B가 대회 본부에 찾아가면 두 학생 중 어느 학생이 더 잘 했는지를 알려준다.
 * - 둘 이상의 학생이 동점인 경우는 없다.
 * - 자신의 전체에서 등수가 궁금한 학생들은 둘 씩 짝을 지어서 대회 본부에 총 M번 질문을 했다.
 * - 질문에 대한 답으로 알 수 있는 최대한 정확한 답을 출력한다.
 */
public class Main {
    static int N, M, X;
    static int[] chk;
    static ArrayList<Integer>[] prev;
    static ArrayList<Integer>[] after;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에 세 정수 N, M, X가 공백을 사이에 두고 주어진다.
        // (2 ≤ N ≤ 105, 1 ≤ M ≤ min(N(N-1)/2, 5×105), 1 ≤ X ≤ N)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        chk = new int[N + 1];
        prev = new ArrayList[N + 1];
        after = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            prev[i] = new ArrayList<>();
            after[i] = new ArrayList<>();
        }

        // 다음 M 줄에는 각각 두 정수 A, B가 주어지는데,
        // 이 뜻은 학생 A가 학생 B보다 더 잘했다는 뜻이다.
        // 같은 A, B가 둘 이상의 줄에 주어지는 경우는 없고,
        // 입력된 값이 정확함이 보장된다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            prev[a].add(b);
            after[b].add(a);
        }

        // 학생 X의 앞에 있는 학생들의 수
        int prevCnt = bfs(X, after);

        // 학생 X의 뒤에 있는 학생들의 수
        chk = new int[N + 1];
        int afterCnt = bfs(X, prev);

        // 두 정수 U, V (1 ≤ U ≤ V ≤ N)를 출력한다.
        // 이는 학생 X의 가능한 가장 높은 등수가 U,
        // 가능한 가장 낮은 등수가 V임을 나타낸다.
        // 만약 학생 X의 가능한 등수가 정확하게 하나라면, U = V이다.
        System.out.println((prevCnt + 1) + " " + (N - afterCnt));
    }

    private static int bfs(int now, ArrayList<Integer>[] list) {
        Queue<Integer> q = new LinkedList<>();
        int cnt = 0;

        chk[now] = 1;
        q.offer(now);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (Integer nxt : list[cur]) {
                if (chk[nxt] == 1) continue;
                chk[nxt] = 1;
                q.offer(nxt);
                cnt++;
            }
        }

        return cnt;
    }
}