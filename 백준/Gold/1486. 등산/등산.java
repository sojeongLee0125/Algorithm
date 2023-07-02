import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Q. 산의 지도, T, 어두워지는 시간 D가 주어졌을 때, D보다 크지 않은 시간 동안
 * 올라갈 수 있는 최대 높이를 구하는 프로그램을 작성하시오.
 * (호텔에서 출발해서 호텔로 돌아와야 한다)
 * <p>
 * - 세준이가 가려고하는 산의 지도가 입력으로 주어진다.
 * - 산의 지도 M[i][j]는 (i,j)의 높이가 M[i][j]라는 것을 의미한다.
 * - 'A'-'Z'일 때는 0-25를 뜻하고, 'a'-'z'일 때는, 26-51을 뜻한다.
 * <p>
 * - 세준이의 호텔은 (0,0)에 있다.
 * - 세준이는 지금 위치에서 바로 인접한 정수 좌표 중
 * - 높이의 차이가 T보다 크지 않은 곳으로만 다닐 수 있다.
 * - 현재 위치에서 높이가 낮은 곳이나 같은 곳으로 이동한다면 시간은 1초가 걸린다.
 * - 높은 곳으로 이동한다면 두 위치의 높이의 차이의 제곱만큼 시간이 걸린다.
 */
public class Main {
    static final int INF = 999_999_999;
    static int N, M, T, D;
    static int[][] map = new int[26][26];
    static int[][] cost = new int[2600][2600]; // (i,j) -> (ny, nx) cost 저장
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static ArrayList<Integer> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 산의 세로크기 N과 가로크기 M 그리고, T와 D가 주어진다.
        // N과 M은 25보다 작거나 같은 자연수이다.
        // T는 52보다 작거나 같은 자연수이고,
        // D는 1,000,000보다 작거나 같은 자연수이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 둘째 줄부터 N개의 줄에 지도가 주어진다.
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (Character.isUpperCase(map[i][j])) map[i][j] -= 'A';
                else map[i][j] = map[i][j] - 'a' + 26;
            }
        }

        int answer = map[0][0];

        // 2차원 배열 -> 1차원 배열로 변환 후 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                points.add(i * 100 + j);
            }
        }

        // 최단 거리 배열 초기화
        for (int i = 0; i < cost.length; i++) {
            Arrays.fill(cost[i], INF);
        }

        findDistance();
        floyd();

        // D보다 시간이 적게 걸리는 경우 중 최대 높이
        for (int j : points) {
            if (D >= cost[0][j] + cost[j][0]) {
                answer = Math.max(answer, map[j / 100][j % 100]);
            }
        }

        // 첫째 줄에 세준이가 갈 수 있는 가장 높은 곳의 높이를 출력한다.
        System.out.println(answer);
    }

    private static void floyd() {
        for (int k : points) {
            for (int i : points) {
                for (int j : points) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }
    }

    private static void findDistance() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                    int diff = Math.abs(map[i][j] - map[ny][nx]);
                    if (diff > T) continue;

                    // 높은 곳으로 갈 경우
                    if (map[ny][nx] > map[i][j]) cost[i * 100 + j][ny * 100 + nx] = (int) Math.pow(diff, 2);
                        // 낮거나 같은 곳으로 갈 경우
                    else cost[i * 100 + j][ny * 100 + nx] = 1;
                }
            }
        }
    }
}