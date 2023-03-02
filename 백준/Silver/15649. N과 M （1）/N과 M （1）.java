import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * - 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * <p>
 * 1. 아이디어
 * - 백트레킹 재귀함수 안에서, for 돌면서 숫자 선택 (방문여부 확인)
 * - 재귀함수에서 M개를 선택할 경우 print
 * <p>
 * 2. 시간복잡도
 * - N! = N=8
 * <p>
 * 3. 자료구조
 * - 결과값 저장 : int[]
 * - 방문여부 체크 : boolean[]
 */
public class Main {

    static int N, M;
    static int[] ans;
    static boolean[] chk;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chk = new boolean[N + 1];
        ans = new int[M];

        // 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다.
        // 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
        // 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
        recur(0);
        System.out.println(sb);
    }

    private static void recur(int dept) {
        if (dept == M) {
            for(int i : ans){
                sb.append(i).append(" ");
            }
            sb.append("\n");
        } else {
            for (int i = 1; i <= N; i++) {
                if (!chk[i]) {
                    ans[dept] = i;
                    chk[i] = true;
                    recur(dept + 1);
                    chk[i] = false;
                }
            }
        }
    }

}