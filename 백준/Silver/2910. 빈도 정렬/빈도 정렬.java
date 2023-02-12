import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Q. 수열이 주어졌을 때, 빈도 정렬을 하는 프로그램을 작성하시오.
 * - 메시지는 숫자 N개로 이루어진 수열이고, 숫자는 모두 C보다 작거나 같다.
 * - 수열의 두 수 X와 Y가 있을 때, X가 Y보다 수열에서 많이 등장하는 경우에는 X가 Y보다 앞에 있어야 한다.
 * - 만약, 등장하는 횟수가 같다면, 먼저 나온 것이 앞에 있어야 한다.
 */
public class Main {
    static int N, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 메시지의 길이 N과 C가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ C ≤ 1,000,000,000)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> order = new HashMap<>();
        ArrayList<int[]> list = new ArrayList<>();

        // 둘째 줄에 메시지 수열이 주어진다.
        // 해당 수의 빈도수와 처음 등장 순서를 map으로 저장한다.
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (!map.containsKey(number)) {
                map.put(number, 1);
                order.put(number, i);
            } else {
                map.put(number, map.get(number) + 1);
            }
        }

        for (Integer key : map.keySet()) {
            list.add(new int[]{map.get(key), key});
        }

        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return order.get(o1[1]) - order.get(o2[1]);
                } else {
                    return o2[0] - o1[0];
                }
            }
        });

        for (int[] arr : list) {
            while (arr[0]-- > 0) {
                System.out.print(arr[1] + " ");
            }
        }
        
    }
}