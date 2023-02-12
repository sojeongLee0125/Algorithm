import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. M, N과 K 그리고 K개의 직사각형의 좌표가 주어질 때, K개의 직사각형 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지,
 * 분리된 각 영역의 넓이가 얼마인지를 구하여 이를 출력하는 프로그램을 작성하시오.
 */
public class Main {
    static int M, N, K;
    static int[][] map;
    static int[][] chk;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // M, N, K는 모두 100 이하의 자연수
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        chk = new int[M][N];

        // K개의 줄에는 직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과 오른쪽 위 꼭짓점의 x, y좌표값
        // 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위 꼭짓점의 좌표는(N,M)
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());
            makeMap(lx, ly, rx, ry);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && chk[i][j] == 0) {
                    chk[i][j] = 1;
                    list.add(dfs(i, j));
                }
            }
        }

        // 분리되어 나누어지는 영역의 개수를 출력
        System.out.println(list.size());

        // 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력
        list.sort(Integer::compareTo);
        list.forEach(i -> System.out.print(i + " "));
    }

    private static void makeMap(int lx, int ly, int rx, int ry) {
        for (int i = M - ry; i < M - ly; i++) {
            for (int j = lx; j < rx; j++) {
                map[i][j] = 1;
            }
        }
    }

    private static int dfs(int i, int j) {
        int rs = 1;
        for (int k = 0; k < 4; k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];
            if (ny < 0 || nx < 0 || ny >= M || nx >= N) continue;
            if (map[ny][nx] == 0 && chk[ny][nx] == 0) {
                chk[ny][nx] = 1;
                rs += dfs(ny, nx);
            }
        }
        return rs;
    }

}