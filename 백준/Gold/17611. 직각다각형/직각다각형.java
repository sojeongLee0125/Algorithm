import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 수평선 H의 위치를 잘 정해서 주어진 단순직각다각형의 수직선분과
 * 가장 많이 교차하는 지점을 찾을 때, 그 때의 교차 횟수를 h라 하고,
 * 유사하게 수직선 V와 주어진 단순직각다각형의 수평선분과 가장 많이
 * 교차하는 횟수를 v라 할 때, max(h, v)를 출력하는 프로그램을 작성하시오.
 * <p>
 * - 다각형의 두 선분이 연속하는 선분의 꼭짓점을 제외하고는
 * - 만나지 않는 다각형을 단순다각형이라고 부른다.
 * - 다각형의 각 변이 x축과 y축에 평행한 다각형을 직각다각형이라 부른다.
 * - 단순다각형이면서 직각다각형을 단순직각다각형이라 부른다.
 * <p>
 * - 단순직각다각형이 주어질 때, 수평선 H가 다각형의 수직선분과 몇 번 교차하는지
 * - 또는 수직선 V가 다각형의 수평선분과 몇 번 교차하는지 알고자 한다.
 * - 이때, 단순직각다각형과 가장 많이 교차하는 수평선 H와 수직선 V의 위치를 찾아
 * - 그때의 교차 횟수를 구하고자 한다.
 * - 단, 수평선 H는 다각형의 어떤 수평선분과도 겹처 놓여서는 안 되고,
 * - 유사하게 수직선 V는 다각형의 어떤 수직선분과도 겹쳐 놓여서는 안 된다.
 */
public class Main {
    static int n, x1, x2, y1, y2;
    static int[] x = new int[1000005];
    static int[] y = new int[1000005];
    static Pair[] arr;
    static ArrayList<Integer> xList = new ArrayList<>();
    static ArrayList<Integer> yList = new ArrayList<>();

    public static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 단순직각다각형의 꼭지점의 개수를 나타내는 정수 n(4 ≤ n ≤ 100,000)이 주어지고,
        // 이어지는 n개줄 각각에 단순직각다각형 꼭지점의 좌표 (xi, yi)가 차례대로 주어진다.
        // 주어지는 꼭지점들의 순서는 시계방향이다.
        // 다각형의 꼭지점을 나타내는 각 좌표값은 정수이며, -500,000 ≤ xi, yi ≤ 500,000이다.
        n = Integer.parseInt(br.readLine());
        arr = new Pair[n + 5];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 좌표 음수 -> 양수 변환
            x += 500000;
            y += 500000;
            arr[i] = new Pair(x, y);

            xList.add(x);
            yList.add(y);
        }

        arr[n] = new Pair(arr[0].x, arr[0].y);

        for (int i = 0; i < n; i++) {
            x1 = arr[i].x;
            y1 = arr[i].y;

            x2 = arr[i + 1].x;
            y2 = arr[i + 1].y;

            if (x1 != x2) {
                if (x1 > x2) swapX();
                for (int j = x1 + 1; j <= x2; j++) {
                    x[j]++;
                }
            }

            if (y1 != y2) {
                if (y1 > y2) swapY();
                for (int j = y1 + 1; j <= y2; j++) {
                    y[j]++;
                }
            }
        }

        // max(h, v)를 출력한다.
        int answer = 0;
        for (Integer num : yList) {
            answer = Math.max(answer, y[num]);
        }

        for (Integer num : xList) {
            answer = Math.max(answer, x[num]);
        }

        System.out.println(answer);
    }

    private static void swapX() {
        int tmp = x1;
        x1 = x2;
        x2 = tmp;
    }

    private static void swapY() {
        int tmp = y1;
        y1 = y2;
        y2 = tmp;
    }
}