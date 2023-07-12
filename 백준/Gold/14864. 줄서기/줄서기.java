import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 순서쌍을 가지고 학생들이 가지고 있는 카드 번호를 알아내는 프로그램을 작성하라.
 * - N명의 학생들이 앞뒤로 일렬로 서 있다.
 * - 각 학생은 1부터 N까지 서로 다른 번호가 적힌 카드들 중 하나를 가지고 있다.
 * - 학생들에게서 자신보다 뒤에 서있으면서 더 작은 번호의 카드를 가진 학생들의
 * - 명단을 하나도 빠짐없이 모두 받았다.
 * - 이 명단을 통해 학생들이 가지고 있는 카드의 번호를 알아내려고 한다.
 * <p>
 * - 순서쌍 (X,Y)
 * - Y가 X보다 뒤에 있으면서 더 작은 번호를 가지고 있다는 것을 의미한다.
 */
public class Main {

    static int N, M;
    static int[] arr, chk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 첫 번째 줄에는 학생 수 N (1 ≤ N ≤ 100,000)과
        // 순서쌍의 수 M (0 ≤ M ≤ 1,000,000)이 공백으로 분리되어 주어진다.
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        chk = new int[N + 1];

        // 다음 M개의 각 줄에는 두 개의 자연수 X와 Y가 공백으로 분리되어 주어진다.
        // Y가 X보다 더 작은 번호가 적힌 카드를 가지고 있다는 것을 의미하는 순서쌍이다
        // (1 ≤ X < Y ≤ N).
        // 입력에 중복된 순서쌍은 없다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x]++; // 더 큰 번호 가중치 증가
            arr[y]--; // 더 작은 번호 가중치 감소
        }

        for (int i = 1; i <= N; i++) {
            arr[i] += i; // 각 학생의 번호 추가
            chk[arr[i]]++; // 중복 체크
        }

        boolean isDuplicate = false;

        for (int i = 1; i <= N; i++) {
            if (chk[arr[i]] >= 2) {
                isDuplicate = true;
                break;
            }
        }

        // 주어진 순서쌍을 통해 학생들이 가지고 있는 카드 번호를 알 수 있으면
        // 학생들이 서 있는 순서대로 카드번호를 공백으로 분리하여 출력한다.
        // 그렇지 않으면 -1을 출력한다.

        StringBuilder sb = new StringBuilder();
        if (isDuplicate) System.out.println(-1);
        else {
            for (int i = 1; i <= N; i++) sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);

    }
}