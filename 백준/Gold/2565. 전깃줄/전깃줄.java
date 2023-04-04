import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄의 최소 개수를 구하는 프로그램을 작성하시오.
 */
public class Main {

    static int[] arr = new int[505];
    static ArrayList<Integer> lis = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에는 두 전봇대 사이의 전깃줄의 개수가 주어진다.
        // 전깃줄의 개수는 100 이하의 자연수이다.
        N = Integer.parseInt(br.readLine());
        lis.add(Integer.MIN_VALUE);

        // 둘째 줄부터 한 줄에 하나씩 전깃줄이 A전봇대와 연결되는 위치의 번호와 B전봇대와 연결되는 위치의 번호가 차례로 주어진다.
        // 치의 번호는 500 이하의 자연수이고, 같은 위치에 두 개 이상의 전깃줄이 연결될 수 없다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[idx] = v;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) continue;
            if (lis.get(lis.size() - 1) < arr[i]) {
                lis.add(arr[i]);
            } else {
                int lt = 1;
                int rt = lis.size() - 1;
                while (lt < rt) {
                    int mid = (lt + rt) / 2;
                    if (lis.get(mid) < arr[i]) lt = mid + 1;
                    else rt = mid;
                }
                lis.set(rt, arr[i]);
            }
        }

        // 살아남는 전깃줄
        int alive = lis.size() - 1;
        System.out.println(N - alive);
    }

}