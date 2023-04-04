import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Q. 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 */
public class Main {
    static int N;
    static int[] arr;
    static int[] idxArr; // 경로 추적
    static ArrayList<Integer> sizeList = new ArrayList<>(); // 사이즈 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 최솟값 먼저 저장
        sizeList.add(Integer.MIN_VALUE);

        // 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        idxArr = new int[N];

        //둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (-1,000,000,000 ≤ Ai ≤ 1,000,000,000)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            // 리스트 마지막보다 큰 경우 리스트 마지막에 삽입
            if (sizeList.get(sizeList.size() - 1) < arr[i]) {
                sizeList.add(arr[i]);
                idxArr[i] = sizeList.size() - 1;
            } else {
                // 그 외의 경우 이분탐색으로 lowerBound 위치 찾아서 해당 위치 숫자와 교체
                int lt = 1;
                int rt = sizeList.size() - 1;

                while (lt < rt) {
                    int mid = (lt + rt) / 2;
                    if (sizeList.get(mid) < arr[i]) {
                        lt = mid + 1;
                    } else {
                        rt = mid;
                    }
                }
                sizeList.set(rt, arr[i]);
                idxArr[i] = rt;
            }
        }

        // 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
        int idx = sizeList.size() - 1;
        System.out.println(idx);

        Stack<Integer> s = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            if (idxArr[i] == idx) {
                idx--;
                s.push(arr[i]);
            }
        }

        while (!s.isEmpty()) System.out.print(s.pop() + " ");
    }
}