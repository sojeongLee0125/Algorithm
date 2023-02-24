import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 비어있는 공집합 S가 주어졌을 때, 아래 연산을 수행하는 프로그램을 작성하시오.
 * add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
 * remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
 * check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
 * toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
 * all: S를 {1, 2, ..., 20} 으로 바꾼다.
 * empty: S를 공집합으로 바꾼다.
 */
public class Main {
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 첫째 줄에 수행해야 하는 연산의 수 M (1 ≤ M ≤ 3,000,000)이 주어진다.
        M = Integer.parseInt(br.readLine());
        int S = (1 << 21);

        // 둘째 줄부터 M개의 줄에 수행해야 하는 연산이 한 줄에 하나씩 주어진다.
        // check 연산이 주어질때마다, 결과를 출력한다.
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String ops = st.nextToken();
            int num = 0;
            if (ops.equals("add")) {
                num = Integer.parseInt(st.nextToken());
                S |= (1 << num);
            } else if (ops.equals("remove")) {
                num = Integer.parseInt(st.nextToken());
                S &= ~(1 << num);
            } else if (ops.equals("check")) {
                num = Integer.parseInt(st.nextToken());
                sb.append((S & (1 << num)) != 0 ? 1 : 0).append("\n");
            } else if (ops.equals("toggle")) {
                num = Integer.parseInt(st.nextToken());
                S ^= (1 << num);
            } else if (ops.equals("all")) {
                S = (1 << 21) - 1;
            } else {
                S = 0;
            }
        }
        System.out.println(sb);
    }

}