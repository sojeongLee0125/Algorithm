import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. N개의 물건을 가방에 넣는 방법의 수를 구하는 프로그램을 작성하시오.
 * - 세준이는 N개의 물건을 가지고 있고,
 * - 최대 C 만큼의 무게를 넣을 수 있는 가방을 하나 가지고 있다.
 */
public class Main {
    static int N;
    static long C;
    static long[] weighs;
    static ArrayList<Long> head = new ArrayList<>();
    static ArrayList<Long> tail = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 N과 C가 주어진다.
        // N은 30보다 작거나 같은 자연수, C는 10^9보다 작거나 같은 음이 아닌 정수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());
        weighs = new long[N];

        // 둘째 줄에 물건의 무게가 주어진다. 무게도 10^9보다 작거나 같은 자연수이다.
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            weighs[i] = Long.parseLong(st.nextToken());
        }

        // Meet in the middle 알고리즘 적용
        // 앞쪽 범위 탐색
        find(0, (N / 2) - 1, head, 0);

        // 뒷쪽 범위 탐색
        find((N / 2), N - 1, tail, 0);

        // 정답 정렬
        head.sort(Long::compareTo);
        tail.sort(Long::compareTo);

        long answer = 0;
        for (long num : head) {
            if (C - num >= 0) { // 가방에 넣을 수 있는 경우
                // C - num 보다 작은 tail 경우의 수들은 넣을 수 있다.
                answer += (upperBound(C - num));
            }
        }

        // 첫째 줄에 가방에 넣는 방법의 수를 출력한다.
        System.out.println(answer);
    }

    private static int upperBound(long target) {
        int lt = 0;
        int rt = tail.size();

        while (lt < rt) {
            int mid = (lt + rt) / 2;

            if (tail.get(mid) <= target) {
                lt = mid + 1;
            } else rt = mid;
        }

        return rt;
    }

    private static void find(int cur, int limit, ArrayList<Long> list, long sum) {
        // 최대 무게를 초과할 경우
        if (sum > C) return;

        // 갯수의 범위를 초과할 경우
        if (cur > limit) {
            list.add(sum);
            return;
        }

        // 가방에 현재 짐을 넣는 경우
        find(cur + 1, limit, list, sum + weighs[cur]);

        // 가방에 현재 짐을 넣지 않는 경우
        find(cur + 1, limit, list, sum);
    }

}