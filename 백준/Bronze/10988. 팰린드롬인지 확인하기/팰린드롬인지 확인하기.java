import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 알파벳 소문자로만 이루어진 단어. 이 단어가 팰린드롬인지 아닌지 확인하는 프로그램
 * - 첫째 줄에 팰린드롬이면 1, 아니면 0을 출력한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 단어가 주어진다. 
        // 단어의 길이는 1보다 크거나 같고, 100보다 작거나 같으며, 알파벳 소문자로만 이루어져 있다.
        String str = br.readLine();

        StringBuilder sb = new StringBuilder(str).reverse();
        if (str.equals(sb.toString())) System.out.println("1");
        else System.out.println("0");
    }
}
