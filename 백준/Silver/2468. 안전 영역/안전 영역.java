import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 어떤 지역의 높이 정보가 주어졌을 때, 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 계산
 * - 그 지역에 많은 비가 내렸을 때 물에 잠기지 않는 안전한 영역이 최대로 몇 개가 만들어 지는 지를 조사
 * - 장마철에 내리는 비의 양에 따라 일정한 높이 이하의 모든 지점은 물에 잠긴다고 가정
 * - 높이 정보는 행과 열의 크기가 각각 N인 2차원 배열 형태로 주어지며 배열의 각 원소는 해당 지점의 높이를 표시하는 자연수
 * - 물에 잠기지 않는 안전한 영역이라 함은 물에 잠기지 않는 지점들이 위, 아래, 오른쪽 혹은 왼쪽으로 인접해 있으며 그 크기가 최대인 영역
 */
public class Main {
    static int N, MAX;
    static int[][] map;
    static int[][] newMap;
    static int[][] chk;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에는 어떤 지역을 나타내는 2차원 배열의 행과 열의 개수를 나타내는 수 N이 입력(N은 2 이상 100 이하의 정수)
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // N개의 각 줄에는 2차원 배열의 첫 번째 행부터 N번째 행까지 순서대로 한 행씩 높이 정보가 입력(높이는 1이상 100 이하의 정수)
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 강수량 기준 1 ~ 101 까지 기준으로 각각의 안전영역을 구한 뒤, 안전영역의 최대값을 구한다.
        for (int h = 0; h < 101; h++) {
            newMap = new int[N][N];
            chk = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > h) newMap[i][j] = 1;
                }
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (newMap[i][j] == 1 && chk[i][j] == 0) {
                        cnt++;
                        chk[i][j] = 1;
                        dfs(i, j);
                    }
                }
            }
            MAX = Math.max(MAX, cnt);
        }
        System.out.println(MAX);
    }

    private static void dfs(int cy, int cx) {
        for (int k = 0; k < 4; k++) {
            int ny = cy + dy[k];
            int nx = cx + dx[k];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
            if (newMap[ny][nx] == 1 && chk[ny][nx] == 0) {
                chk[ny][nx] = 1;
                dfs(ny, nx);
            }
        }
    }

}