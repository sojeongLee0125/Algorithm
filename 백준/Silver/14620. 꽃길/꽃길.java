import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Q. 진아는 서로 다른 세 씨앗을 모두 꽃이 피게하면서 가장 싼 가격에 화단을 대여하고 싶다. 진아가 꽃을 심기 위해 필요한 최소비용을 구해주자!
 * - 꽃밭은 N*N의 격자 모양이고 진아는 씨앗을 (1,1)~(N,N)의 지점 중 한곳에 심을 수 있다.
 * - 어떤 씨앗이 꽃이 핀 뒤 다른 꽃잎(혹은 꽃술)과 닿게 될 경우 두 꽃 모두 죽어버린다. 또 화단 밖으로 꽃잎이 나가게 된다면 그 꽃은 죽어버리고 만다.
 * - 화단을 대여할 때는 꽃잎이 핀 모양을 기준으로 대여를 해야하므로 꽃 하나당 5평의 땅을 대여해야만 한다.
 */
public class Main {

    static int N, min = Integer.MAX_VALUE;
    static int[][] price;
    static int[][] map;
    static int[][] chk;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력의 첫째 줄에 화단의 한 변의 길이 N(6≤N≤10)이 들어온다.
        N = Integer.parseInt(br.readLine());
        price = new int[N][N];
        map = new int[N][N];
        chk = new int[N][N];

        //  N개의 줄에 N개씩 화단의 지점당 가격(0≤G≤200)이 주어진다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                price[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 꽃을 심는다. 3개
        flower(0, 0);
        // 꽃이 3개 심어지면 가격을 구해서 최소가격을 업데이트 한다.

        // 꽃을 심기 위한 최소 비용을 출력한다.
        System.out.println(min);

    }

    private static void flower(int cnt, int price) {
        if (cnt == 3) {
            // 가격을 계산한다.
            min = Math.min(price, min);
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (isOk(i, j)) {
                        flower(cnt + 1, price + set(i, j, 1));
                        set(i, j, 0);
                    }
                }
            }
        }
    }

    private static int set(int i, int j, int value) {
        int sum = price[i][j];
        map[i][j] = value;
        for (int k = 0; k < 4; k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];
            map[ny][nx] = value;
            sum += price[ny][nx];
        }
        return sum;
    }

    private static boolean isOk(int i, int j) {
        if (map[i][j] == 1) return false;
        for (int k = 0; k < 4; k++) {
            int ny = i + dy[k];
            int nx = j + dx[k];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] != 0) return false;
        }
        return true;
    }
}