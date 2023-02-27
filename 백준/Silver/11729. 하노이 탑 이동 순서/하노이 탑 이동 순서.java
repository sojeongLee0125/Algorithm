import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 전체 갯수 : 2^N - 1
        sb.append((int) Math.pow(2, N) - 1).append("\n");

        cal(1, 2, 3, N);
        System.out.println(sb);
    }

    private static void cal(int st, int mid, int ed, int cnt) {
        if (cnt == 1) {
            sb.append(st).append(" ").append(ed).append("\n");
        } else {
            // 1기둥에서 3기둥으로 가는 경우

            // 1. N-1개 1기둥 -> 2기둥으로 이동
            cal(st, ed, mid, cnt - 1);

            // 2. 1기둥에 남은 1개는 3기둥으로 이동
            sb.append(st).append(" ").append(ed).append("\n");

            // 3. N-1개 2기둥 -> 3기둥으로 이동
            cal(mid, st, ed, cnt - 1);
        }

    }

}