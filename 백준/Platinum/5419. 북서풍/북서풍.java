import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Q. 북서풍을 타고 항해할 수 있는 섬의 쌍의 수를 구하는 프로그램을 작성하시오.
 * - 강한 북서풍이 불고 있다.
 * - 이 뜻은 동쪽과 남쪽 사이의 모든 방향으로 항해할 수 있다는 뜻이다.
 * - 북쪽이나 서쪽으로 항해하는 것은 불가능하다.
 * - 작은 섬이 여러 개 있는 바다가 있다. 섬은 좌표 평면의 한 점으로 나타낼 수 있다.
 * - y 좌표가 증가하는 방향은 북쪽, x 좌표가 증가하는 방향은 동쪽이다.
 */
public class Main {
    static int T, n;
    static int[] fenwickTree;
    static ArrayList<Integer> symmetryY;
    static ArrayList<int[]> symmetryPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 테스트 케이스의 개수가 주어진다.
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // 각 테스트 케이스의 첫째 줄에는 섬의 수 n (1 ≤ n ≤ 75000)이 주어진다.
            n = Integer.parseInt(br.readLine());

            fenwickTree = new int[n + 1];
            symmetryPoint = new ArrayList<>();
            symmetryY = new ArrayList<>();

            // 다음 n개 줄에는 각 섬의 좌표 xi, yi가 주어진다.
            // 두 섬의 좌표가 같은 경우는 없다. (-109 ≤ xi, yi ≤ 109)
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                // y축 대칭 좌표 저장
                symmetryPoint.add(new int[]{x, (y * -1)});
                symmetryY.add((y * -1));
            }

            // x축 기준 오름차순 정렬
            Collections.sort(symmetryPoint, (o1, o2) -> {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            });

            // y축 기준 오름차순 정렬
            Collections.sort(symmetryY);

            // 펜윅트리 계산
            long answer = 0L;

            // 펜윅트리 초기값 생성
            updateTree(binarySearch(symmetryY, symmetryPoint.get(0)[1] + 1), 1);

            // 펜윅트리 계산
            for (int i = 1; i < n; i++) {
                int idx = binarySearch(symmetryY, symmetryPoint.get(i)[1] + 1);
                answer += 1L * getSum(idx);
                updateTree(idx, 1);
            }

            // 각 테스트 케이스에 대해서, 북서풍을 타고 항해할 수 있는 섬의 쌍의 수를 출력한다.
            System.out.println(answer);
        }

    }

    private static int getSum(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += fenwickTree[idx];
            idx -= (idx & -idx);
        }
        return sum;
    }

    private static void updateTree(int idx, int val) {
        while (idx <= n) {
            fenwickTree[idx] += val;
            idx += (idx & -idx);
        }
    }

    private static int binarySearch(ArrayList<Integer> symmetryY, int val) {
        int lt = 0;
        int rt = symmetryY.size() - 1;

        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            if (symmetryY.get(mid) == val) return mid;
            if (symmetryY.get(mid) < val) lt = mid + 1;
            else rt = mid - 1;
        }

        return lt;
    }
}