import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 놀이기구가 모두 비어 있는 상태에서 첫 번째 아이가 놀이기구에 탑승한다고 할 때,
 * 줄의 마지막 아이가 타게 되는 놀이기구의 번호를 구하는 프로그램을 작성하시오.
 */
public class Main {
    static long N;
    static int M, max;
    static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 N(1 ≤ N ≤ 2,000,000,000)과 M(1 ≤ M ≤ 10,000)이 빈칸을 사이에 두고 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Long.parseLong(st.nextToken()); // 아이의 수
        M = Integer.parseInt(st.nextToken()); // 놀이기구 수
        time = new int[M];

        // 둘째 줄에는 각 놀이기구의 운행 시간을 나타내는 M개의 자연수가 순서대로 주어진다.
        // 운행 시간은 1 이상 30 이하의 자연수이며, 단위는 분이다.
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            time[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, time[i]);
        }

        // 1. 아이들의 수보다 놀이기구의 수가 더 많으면 마지막 아이는 N번째 놀이기구를 타게 된다.
        if (N <= M) System.out.println(N);
        else {
            // 2. 이분 탐색 : 전체 아이들이 다 탄 시간이 mid 일 때 아이가 N명 이상 타는지 검사
            // 총 시간 : 최대시간 * 아이수 (N), 최대 600억
            long lt = 0L;
            long rt = (N / M) * max;
            long t = 0L;

            // 1차 : 대략적인 시간 범위 구하기
            while (lt <= rt) {
                long mid = (lt + rt) / 2;
                if (isOk(mid)) {
                    // 시간을 더 줄여 본다.
                    rt = mid - 1;
                    t = mid;
                } else {
                    lt = mid + 1;
                }
            }

            long tmp = M;
            // 이분 탐색 조건 체크 로직과 같은 로직
            // 구해진 time 1초 전까지의 놀이기구를 태운 수를 구한다.
            for (int i = 0; i < M; i++) {
                tmp += ((t - 1) / time[i]);
            }

            // 현재 타임에서 처음 놀이기구부터 하나씩 태워본다.
            for (int i = 0; i < M; i++) {
                if (t % time[i] == 0) tmp++; // 딱 떨어지는 경우 그 사이에 한명 더 태울 수 있으므로 추가
                if (tmp == N) {
                    System.out.println(i + 1);
                    break;
                }
            }
        }
    }

    private static boolean isOk(long t) {
        // 모든 놀이기구마다 1명씩 + 해줘야한다.
        // 해당 시간에 나누어 떨어지는 경우 새로운 친구가 타고, 나머지가 있는 경우 다른 친구가 타고있는 경우므로.
        long cnt = M;

        for (int i = 0; i < M; i++) {
            cnt += (t / time[i]); // 각 놀이기구 당 태우는 아이들 수
        }

        return cnt >= N;
    }
}