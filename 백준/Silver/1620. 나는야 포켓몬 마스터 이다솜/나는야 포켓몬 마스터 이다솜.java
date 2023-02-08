import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Q. 입력으로 숫자가 들어왔다면 그 숫자에 해당하는 포켓몬의 이름, 문자가 들어왔으면 그 포켓몬의 이름에 해당하는 번호를 출력
 */
public class Main {
    static int N, M;
    static HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 도감에 수록되어 있는 포켓몬의 개수 N이랑 내가 맞춰야 하는 문제의 개수 M
        // N과 M은 1보다 크거나 같고, 100,000보다 작거나 같은 자연수
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 포켓몬의 번호가 1번인 포켓몬부터 N번에 해당하는 포켓몬까지 한 줄에 하나씩 입력
        // 포켓몬의 이름은 모두 영어로만 이루어져있고, 첫 글자만 대문자이고, 나머지 문자는 소문자,
        // 일부 포켓몬은 마지막 문자만 대문자, 포켓몬 이름의 최대 길이는 20, 최소 길이는 2
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            map.put(str, String.valueOf(i));
            map.put(String.valueOf(i), str);
        }

        // 그 다음 줄부터 총 M개의 줄에 내가 맞춰야하는 문제가 입력
        // 문제가 알파벳으로만 들어오면 포켓몬 번호, 숫자는 포켓몬 번호에 해당하는 문자를 출력
        // 입력으로 들어오는 숫자는 반드시 1보다 크거나 같고, N보다 작거나 같고, 입력으로 들어오는 문자는 반드시 도감에 있는 포켓몬의 이름
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            System.out.println(map.get(str));
        }

    }
}