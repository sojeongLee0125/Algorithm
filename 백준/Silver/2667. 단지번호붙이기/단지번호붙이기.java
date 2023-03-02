import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Q. 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
 * - 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다.
 * - 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
 *
 * 1. 아이디어
 * - 2중 for => 값 1, 방문x => DFS
 * - DFS를 통해 찾은 값 저장 후 정렬 출력
 *
 * 2. 시간복잡도
 * - DFS : O(V+E)
 * - V : N^2
 * - E : 4N^2
 * - V+E : 5N^2 => N^2(625)
 *
 * 3. 자료구조
 * - 그래프 : int[][]
 * - 방문 : boolean[][]
 * - 결과값 리스트 : ArrayList<Integer>
 */
public class Main {

    static int N, cnt;
    static int[][] map;
    static boolean[][] chk;
    static ArrayList<Integer> list = new ArrayList<>(); // 각 단지에 속하는 집의 수

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고,
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        chk = new boolean[N][N];

        // 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !chk[i][j]) {
                    cnt++;
                    list.add(dfs(i, j));
                }
            }
        }

        // 첫 번째 줄에는 총 단지수를 출력하시오.
        System.out.println(cnt);

        // 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
        list.sort(Integer::compareTo);
        list.forEach(i -> System.out.println(i));
    }

    private static Integer dfs(int i, int j) {
        int rs = 1;
        chk[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 0 || chk[ny][nx]) continue;
            rs += dfs(ny, nx);
        }
        return rs;
    }

}