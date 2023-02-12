import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 바구니의 이동 거리의 최솟값을 구하는 프로그램을 작성하시오.
 * - 스크린은 N칸으로 나누어져 있다. 스크린의 아래쪽에는 M칸을 차지하는 바구니가 있다. (M<N)
 * - 처음에 바구니는 왼쪽 M칸을 차지하고 있다.
 */
public class Main {
    static int N, M, J, move, lt, rt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 N과 M이 주어진다. (1 ≤ M < N ≤ 10)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 떨어지는 사과의 개수 J가 주어진다. (1 ≤ J ≤ 20)
        J = Integer.parseInt(br.readLine());

        // 바구니의 끝점
        lt = 1;
        rt = M;

        // J개 줄에는 사과가 떨어지는 위치가 순서대로 주어진다.
        while (J-- > 0) {
            int pos = Integer.parseInt(br.readLine());
            // 범위내로 떨어질 경우
            if (lt <= pos && pos <= rt) {
                continue;
            } else if (rt < pos) {
                // 바구니가 왼쪽, 사과가 오른쪽에 떨어질 경우
                move += pos - rt;
                rt = pos;
                lt = rt - M + 1;
            } else {
                // 바구니가 오른쪽, 사과가 왼쪽에 떨어질 경우
                move += lt - pos;
                lt = pos;
                rt = lt + M - 1;
            }
        }

        //모든 사과를 담기 위해서 바구니가 이동해야 하는 거리의 최솟값을 출력
        System.out.println(move);
    }

}