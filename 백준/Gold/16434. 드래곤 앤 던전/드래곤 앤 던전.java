import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 용사는 N번 방에 있는 용을 쓰러트리기 위한 최소의 HMaxHP를 여러분이 계산해주면 좋겠다고 합니다.
 */
public class Main {
    static int N, initA;
    static long lt, rt, max = Long.MAX_VALUE;
    static ArrayList<long[]> rooms = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 첫 번째 줄에 방의 개수 N (1 ≤ N  ≤ 123,456) 과 용사의 초기 공격력 HATK (1 ≤ HATK  ≤ 1,000,000) 가 주어집니다.
        N = Integer.parseInt(st.nextToken());
        initA = Integer.parseInt(st.nextToken());

        // i+1번째 줄엔 i번째 방의 정보를 나타내는 세개의 정수 ti, ai, hi (ti ∈ {1, 2}, 1 ≤ ai, hi  ≤ 1,000,000) 가 주어집니다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            long t = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());
            rooms.add(new long[]{t, a, h});
        }

        // 이분탐색으로 용사의 최대 HP를 찾아 나간다.
        lt = 1L;
        rt = 1_000_000_000_000_000_000L;

        while (lt <= rt) {
            long mid = (lt + rt) / 2;
            if (isOk(mid)) {
                rt = mid - 1;
                max = Math.min(max, mid);
            } else {
                lt = mid + 1;
            }
        }

        // 용사가 N번째 방에 있는 용을 쓰러트리기 위한 최소의 HMaxHP를 출력합니다.
        System.out.println(max);
    }

    private static boolean isOk(long mid) {
        long hp = mid;
        long atk = initA;

        // 방을 처음부터 N까지 순회한다.
        // 1이 나오면 몬스터고 2가 나오면 포션이다.
        // 몬스터가 나오면 해당 몬스터의 생명력 / 공격력 만큼의 공격을 수행하고 그 -1번만큼 공격을 받는다.
        // 포션이 나오면 생명력과 공격력이 올라가는데 이때 최대 올라갈 수 있는 생명력은 hp만큼만이다.
        for (int i = 0; i < rooms.size(); i++) {
            long[] room = rooms.get(i);

            long t = room[0]; // 1 - 몬스터, 2 - 포션
            long a = room[1]; // 공격력
            long h = room[2]; // 생명력

            if (t == 1L) {
                long times = (h / atk) + (h % atk == 0 ? 0 : 1); // 총 공격 횟수
                long att = times - 1; // 공격 받은 횟수
                hp -= (att * a);
            } else {
                hp = Math.min(mid, hp + h);
                atk += a;
            }
            if (hp <= 0) return false;
        }

        return true;
    }

}