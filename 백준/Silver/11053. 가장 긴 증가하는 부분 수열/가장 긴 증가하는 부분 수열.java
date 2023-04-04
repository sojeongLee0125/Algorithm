import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 */
public class Main {

    static int N;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        list.add(Integer.MIN_VALUE);

        // 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (list.get(list.size() - 1) < arr[i]) {
                list.add(arr[i]);
            } else {
                int lt = 0;
                int rt = list.size() - 1;

                while (lt < rt) {
                    int mid = (lt + rt) / 2;
                    if (list.get(mid) < arr[i]) {
                        lt = mid + 1;
                    } else rt = mid;
                }

                list.set(rt, arr[i]);
            }
        }

        System.out.println(list.size() - 1);


    }

}