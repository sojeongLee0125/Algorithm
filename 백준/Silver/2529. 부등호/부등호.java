import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Q. 제시된 k개의 부등호 순서를 만족하는 (k+1)자리의 정수 중에서 최댓값과 최솟값을 찾아야 한다.
 * - 두 종류의 부등호 기호 ‘<’와 ‘>’가 k개 나열된 순서열 A가 있다.
 * - 이 부등호 기호 앞뒤에 서로 다른 한 자릿수 숫자를 넣어서 모든 부등호 관계를 만족시키려고 한다.
 * - 부등호 기호 앞뒤에 넣을 수 있는 숫자는 0부터 9까지의 정수이며 선택된 숫자는 모두 달라야 한다.
 * - 부등호 기호를 제거한 뒤, 숫자를 모두 붙이면 하나의 수를 만들 수 있는데 이 수를 주어진 부등호 관계를 만족시키는 정수라고 한다.
 * - 주어진 부등호 관계를 만족하는 정수는 하나 이상 존재한다.
 */
public class Main {
    static int k;
    static ArrayList<String> answer = new ArrayList<>();
    static ArrayList<Character> ops = new ArrayList<>();
    static boolean[] chk = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄에 부등호 문자의 개수를 나타내는 정수 k가 주어진다.
        k = Integer.parseInt(br.readLine());

        // 그 다음 줄에는 k개의 부등호 기호가 하나의 공백을 두고 한 줄에 모두 제시된다. k의 범위는 2 ≤ k ≤ 9 이다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        while (st.hasMoreTokens()) {
            ops.add(st.nextToken().charAt(0));
        }

        go(0, "");

        //  제시된 부등호 관계를 만족하는 k+1 자리의 최대, 최소 정수를 첫째 줄과 둘째 줄에 각각 출력해야 한다.
        //  첫 자리가 0인 경우도 정수에 포함되어야 한다.
        answer.sort(String::compareTo);
        System.out.println(answer.get(answer.size()-1));
        System.out.println(answer.get(0));
    }

    private static void go(int idx, String num) {
        if (idx == k + 1) {
            answer.add(num);
        } else {
            for (int i = 0; i <= 9; i++) {
                if (chk[i]) continue;
                if (idx == 0 || isRight(ops.get(idx - 1), num.charAt(idx - 1) - '0', i)) {
                    chk[i] = true;
                    go(idx + 1, num + i);
                    chk[i] = false;
                }
            }
        }
    }

    private static boolean isRight(char s, int num1, int num2) {
        if (s == '<') {
            return num1 < num2;
        } else {
            return num1 > num2;
        }
    }


}