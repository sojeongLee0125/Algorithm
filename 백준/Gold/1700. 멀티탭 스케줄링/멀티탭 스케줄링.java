import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 자기가 사용하고 있는 전기용품의 사용순서를 알아내었고, 이를 기반으로 플러그를 빼는 횟수를 최소화하는 방법을 고안.
 * <p>
 * 1. 아이디어
 * - CPU 스케줄링과 비슷
 */
public class Main {

    static int N, K, cnt;
    static int[] arr;
    static int[] chk = new int[105];
    static ArrayList<Integer> consent = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄에는 멀티탭 구멍의 개수 N (1 ≤ N ≤ 100)과 전기 용품의 총 사용횟수 K (1 ≤ K ≤ 100)가 정수로 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[K];

        // 두 번째 줄에는 전기용품의 이름이 K 이하의 자연수로 사용 순서대로 주어진다.
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1. order 순서대로 반복문을 돌린다
        for (int i = 0; i < arr.length; i++) {
            // 현재 콘센트가 안 꽂힌 경우
            if (chk[arr[i]] == 0) {
                // 콘센트가 이미 다 꽂혀 있는 경우
                if (consent.size() == N) {
                    int last = 0;
                    int pos = 0;
                    for (int c : consent) {
                        int cur_idx = Integer.MAX_VALUE;
                        // 현재 뒷번호들을 조회한다.
                        for (int j = i + 1; j < arr.length; j++) {
                            if (c == arr[j]) {
                                cur_idx = j;
                                break;
                            }
                        }
                        if (last < cur_idx) {
                            last = cur_idx;
                            pos = c;
                        }
                    }
                    chk[pos] = 0;
                    cnt++;
                    for (int k = 0; k < consent.size(); k++) {
                        if (consent.get(k) == pos) {
                            consent.remove(k);
                            break;
                        }
                    }
                }
                // 신규 콘센트 삽입
                chk[arr[i]] = 1;
                consent.add(arr[i]);
            }
        }

        // 하나씩 플러그를 빼는 최소의 횟수를 출력하시오.
        System.out.println(cnt);
    }
}