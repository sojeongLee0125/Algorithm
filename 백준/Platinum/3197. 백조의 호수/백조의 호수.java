import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 3197 - 시간 초과
 * Q. 며칠이 지나야 백조들이 만날 수 있는 지 계산하는 프로그램을 작성하시오.
 * - 호수는 행이 R개, 열이 C개인 직사각형 모양이다. 어떤 칸은 얼음으로 덮여있다.
 * - 호수는 차례로 녹는데, 매일 물 공간과 접촉한 모든 빙판 공간은 녹는다.
 * - 두 개의 공간이 접촉하려면 가로나 세로로 닿아 있는 것만 (대각선은 고려하지 않는다) 생각한다.
 * - 백조는 오직 물 공간에서 세로나 가로로만(대각선은 제외한다) 움직일 수 있다.
 */
public class Main {
    static int R, C, day, sY, sX;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static char[][] map;

    static Queue<int[]> water = new LinkedList<>();
    static Queue<int[]> water_tmp = new LinkedList<>();
    static int[][] waterChk;

    static Queue<int[]> swan = new LinkedList<>();
    static Queue<int[]> swan_tmp = new LinkedList<>();
    static int[][] swanChk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력의 첫째 줄에는 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1500.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        waterChk = new int[R][C];
        swanChk = new int[R][C];

        // 다음 R개의 줄에는 각각 길이 C의 문자열이 하나씩 주어진다.
        // '.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조가 있는 공간으로 나타낸다.
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                // 도착 백조 위치 저장
                if (map[i][j] == 'L') {
                    sY = i;
                    sX = j;
                }
                // 빙판이 아닌 위치 큐 저장
                if (map[i][j] != 'X') {
                    waterChk[i][j] = 1;
                    water.add(new int[]{i, j});
                }
            }
        }
        
        // 도착 백조 위치 큐 저장
        swanChk[sY][sX] = 1;
        swan.add(new int[]{sY, sX});

        go();

        // 첫째 줄에 문제에서 주어진 걸리는 날을 출력한다.
        System.out.println(day);
    }

    private static void go() {
        while (true) {
            if (isMeet()) break;
            waterMelt();
            water = water_tmp;
            swan = swan_tmp;
            water_tmp = new LinkedList<>();
            swan_tmp = new LinkedList<>();
            day++;
        }
    }
    
    private static boolean isMeet() {
        while (!swan.isEmpty()) {
            int[] cur = swan.poll();
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];
                if (ny < 0 || nx < 0 || ny >= R || nx >= C || swanChk[ny][nx] != 0) continue;
                swanChk[ny][nx] = 1;
                if (map[ny][nx] == '.') swan.add(new int[]{ny, nx});
                else if (map[ny][nx] == 'X') swan_tmp.add(new int[]{ny, nx});
                else if (map[ny][nx] == 'L') return true;
            }
        }
        return false;
    }

    private static void waterMelt() {
        while (!water.isEmpty()) {
            int[] cur = water.poll();
            for (int k = 0; k < 4; k++) {
                int ny = cur[0] + dy[k];
                int nx = cur[1] + dx[k];
                if (ny < 0 || nx < 0 || ny >= R || nx >= C || waterChk[ny][nx] != 0) continue;
                if (map[ny][nx] == 'X') {
                    waterChk[ny][nx] = 1;
                    water_tmp.add(new int[]{ny, nx});
                    map[ny][nx] = '.';
                }
            }
        }
    }

}