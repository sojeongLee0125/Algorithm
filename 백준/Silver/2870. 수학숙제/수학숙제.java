import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Q. 숫자와 알파벳 소문자로 되어있는 글자가 N줄있다. 여기서 숫자를 모두 찾은 뒤, 이 숫자를 비내림차순으로 정리해야한다.
 * - 숫자의 앞에 0이 있는 경우에는 정리하면서 생략할 수 있다.
 * - 가능한 가장 큰 숫자를 찾아야 한다. 즉, 모든 숫자의 앞과 뒤에 문자가 있거나, 줄의 시작 또는 끝이어야 한다.
 */
public class Main {
    static int N;
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫째 줄에 종이의 줄의 개수 N이 주어진다. (1 ≤ N ≤ 100)
        N = Integer.parseInt(br.readLine());

        // N개의 줄에는 각 줄의 내용이 주어진다. 각 줄은 최대 100글자이고, 항상 알파벳 소문자와 숫자로만 이루어져 있다.
        while (N-- > 0) {
            String num = "";
            String str = br.readLine();
            for (char c : str.toCharArray()) {
                if (Character.isDigit(c)) {
                    num += String.valueOf(c);
                } else {
                    if (!num.equals("")) {
                        list.add(num);
                        num = "";
                    }
                }
            }
            // 마지막 숫자 처리
            if (!num.equals("")) list.add(num);
        }

        List<String> nums = list.stream().map(num -> {
            while (!num.equals("0") && num.startsWith("0")) {
                num = num.substring(1);
            }
            return num;
        }).collect(Collectors.toList());

        nums.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return comp(o1, o2);
                } else {
                    return o1.length() - o2.length();
                }
            }
        });

        nums.forEach(System.out::println);


    }

    private static int comp(String o1, String o2) {
        int idx = 0;
        while (o1.length() > idx && o2.length() > idx) {
            if (o1.charAt(idx) != o2.charAt(idx)) {
                return o1.charAt(idx) - o2.charAt(idx);
            } else {
                idx++;
            }
        }
        return 0;
    }

}