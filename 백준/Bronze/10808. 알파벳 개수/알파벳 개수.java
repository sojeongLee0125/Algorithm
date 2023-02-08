import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Q. 알파벳 소문자로만 이루어진 단어 S, 각 알파벳이 단어에 몇 개가 포함되어 있는지 구하는 프로그램
 * - 단어의 길이는 100을 넘지 않는다.
 * - 단어에 포함되어 있는 a의 개수, b의 개수, …, z의 개수를 공백으로 구분해서 출력
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] arr = new int[26];

        for (char c : str.toCharArray()) {
            arr[c - 'a']++;
        }

        for (int i = 'a'; i <= 'z'; i++) System.out.print(arr[i - 'a'] + " ");
    }

}