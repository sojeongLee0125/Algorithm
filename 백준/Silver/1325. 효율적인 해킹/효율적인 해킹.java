import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 컴퓨터의 신뢰하는 관계가 주어졌을 때, 
      한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성하시오
 * - 회사는 N개의 컴퓨터로 이루어져 있다.
 * - 한 번의 해킹으로 여러 개의 컴퓨터를 해킹 할 수 있는 컴퓨터를 해킹하려고 한다.
 * - 이 회사의 컴퓨터는 신뢰하는 관계와, 신뢰하지 않는 관계로 이루어져 있는데,
 * - A가 B를 신뢰하는 경우에는 B를 해킹하면, A도 해킹할 수 있다.
 */
public class Main {
    static int N, M, max;
    static int[] chk;
    static int[] cnt;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에, N과 M이 들어온다. 
        // N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        tree = new ArrayList[N + 1];
        cnt = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 둘째 줄부터 M개의 줄에 신뢰하는 관계가 A B와 같은 형식으로 들어오며, 
        // "A가 B를 신뢰한다"를 의미한다. 컴퓨터는 1번부터 N번까지 번호가 하나씩 매겨져 있다.
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            tree[B].add(A);
        }
        
        // 1. 각 컴퓨터 별 해킹할 수 있는 컴퓨터 갯수 저장한다.
        for (int i = 1; i <= N; i++) {
            chk = new int[N + 1];
            cnt[i] = hack(i);
            max = Math.max(cnt[i], max);
        }

        // 2. 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순으로 출력한다.
        for (int i = 1; i <= N; i++) {
            if (cnt[i] == max) System.out.print(i + " ");
        }
    }

    private static int hack(int node) {
        chk[node] = 1;
        
        // 리프노드일 경우 1을 리턴하므로 기본값은 1
        int rs = 1;
        
        // 하위 컴퓨터들 탐색
        for (int child : tree[node]) {
            if (chk[child] == 1) continue;
            rs += hack(child);
        }
        
        return rs;
    }

}