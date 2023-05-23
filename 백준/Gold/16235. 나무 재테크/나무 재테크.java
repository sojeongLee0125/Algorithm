import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하는 프로그램을 작성하시오.
 * - 상도는 최근 N×N 크기의 땅을 구매했다. 땅을 1×1 크기의 칸으로 나누어 놓았다.
 * - 각각의 칸은 (r, c)로 나타내며, r과 c는 1부터 시작한다.
 * - 가장 처음에 양분은 모든 칸에 5만큼 들어있다.
 * - 상도는 나무 재테크로 더 큰 돈을 벌기 위해 M개의 나무를 구매해 땅에 심었다.
 * - 같은 1×1 크기의 칸에 여러 개의 나무가 심어져 있을 수도 있다.
 * - 이 나무는 사계절을 보내며, 아래와 같은 과정을 반복한다.
 * <p>
 * - 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
 * - 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
 * - 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
 * - 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
 * <p>
 * - 여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
 * - 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
 * <p>
 * - 가을에는 나무가 번식한다.
 * - 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
 * - 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
 * <p>
 * - 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
 * - 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
 */
public class Main {
    static int dx[] = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int dy[] = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int N, M, K;
    static int[][] A = new int[15][15];
    static int[][] nutrition = new int[15][15];
    static ArrayList<Integer>[][] tree = new ArrayList[15][15];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 N, M, K가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 양분 배열 초기화
        for (int i = 0; i < nutrition.length; i++) {
            Arrays.fill(nutrition[i], 5);
        }

        // 둘째 줄부터 N개의 줄에 A배열의 값이 주어진다. r번째 줄의 c번째 값은 A[r][c]이다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                tree[i][j] = new ArrayList<>();
            }
        }

        // 다음 M개의 줄에는 상도가 심은 나무의 정보를 나타내는 세 정수 x, y, z가 주어진다.
        // 처음 두 개의 정수는 나무의 위치 (x, y)를 의미하고, 마지막 정수는 그 나무의 나이를 의미한다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int old = Integer.parseInt(st.nextToken());
            tree[y][x].add(old);
        }

        while (K-- > 0) {
            springSummer();
            fall();
            winter();
        }

        int answer = 0;
        // 첫째 줄에 K년이 지난 후 살아남은 나무의 수를 출력한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += tree[i][j].size();
            }
        }

        System.out.println(answer);
    }

    private static void springSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 해당 위치에 나무가 없는 경우
                if (tree[i][j].size() == 0) continue;

                // 죽은 나무들의 나이
                int die = 0;

                // 새로운 나무로 교체할 배열
                ArrayList<Integer> tmp = new ArrayList<>();

                // 나이 순 정렬
                tree[i][j].sort(Integer::compareTo);

                // 자신의 나이만큼 양분 추가
                for (int tr : tree[i][j]) {
                    if (nutrition[i][j] >= tr) {
                        nutrition[i][j] -= tr;
                        tmp.add(tr + 1);
                    } else {
                        // 죽은 나무 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
                        die += tr / 2;
                    }
                }

                // 교체
                tree[i][j] = tmp;
                nutrition[i][j] += die;
            }
        }
    }

    private static void fall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 해당 위치에 나무가 없는 경우
                if (tree[i][j].size() == 0) continue;

                for (int tr : tree[i][j]) {
                    // 번식하는 나무는 나이가 5의 배수, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
                    if (tr % 5 == 0) {
                        for (int k = 0; k < 8; k++) {
                            int ny = i + dy[k];
                            int nx = j + dx[k];
                            if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                            tree[ny][nx].add(1);
                        }
                    }
                }
            }
        }
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
                nutrition[i][j] += A[i][j];
            }
        }
    }

}