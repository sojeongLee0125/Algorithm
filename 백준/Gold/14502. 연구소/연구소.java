import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.
 * - 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.
 * - 연구소는 크기가 N×M인 직사각형, 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다.
 * - 일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다.
 * - 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다. 벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다.
 * -  0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다.
 */
public class Main {

    static int N, M, max;
    static int[][] map;
    static int[][] copy;

    // 벽을 세울 수 있는 곳
    static ArrayList<int[]> list = new ArrayList<>();

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // N개의 줄에 지도의 모양이 주어진다.
        // 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.빈 칸의 개수는 3개 이상이다.

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 빈칸일 경우 벽 세우기 후보지로 추가
                if (map[i][j] == 0) list.add(new int[]{i, j});
            }
        }

        // 해결방법
        // 1. 3개의 조합을 선택하여 벽을 세운다.
        // 2. 해당 조합으로 벽을 세웠을 때 바이러스가 퍼져나간 뒤 안전영역을 구한다.
        // 3. 안전영역 최대값을 업데이트 한다.

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    // 벽 세우기
                    changeWall(i, j, k, 1);
                    // 맵 복제본 생성
                    copy = new int[N][M];
                    makeCopy();
                    // 바이러스 전파
                    virus();
                    // 안전영역 구하기
                    find();
                    // 벽제거
                    changeWall(i, j, k, 0);
                }
            }
        }
        // 얻을 수 있는 안전 영역의 최대 크기를 출력한다.
        System.out.println(max);
    }

    private static void find() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 0) cnt++;
            }
        }
        max = Math.max(max, cnt);
    }

    private static void makeCopy() {
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, map[i].length);
        }
    }

    private static void virus() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 2) dfs(i, j);
            }
        }
    }

    private static void dfs(int i, int j) {
        for (int k = 0; k < 4; k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];
            if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
            if (copy[ny][nx] == 0) {
                copy[ny][nx] = 2;
                dfs(ny, nx);
            }
        }
    }

    private static void changeWall(int i, int j, int k, int num) {
        int[] w1 = list.get(i);
        int[] w2 = list.get(j);
        int[] w3 = list.get(k);
        map[w1[0]][w1[1]] = num;
        map[w2[0]][w2[1]] = num;
        map[w3[0]][w3[1]] = num;
    }

}