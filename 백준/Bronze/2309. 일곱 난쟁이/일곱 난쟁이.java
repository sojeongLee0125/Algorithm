import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Q. 아홉 난쟁이의 키가 주어졌을 때, 일곱 난쟁이를 찾는 프로그램.
 * - 일곱 난쟁이의 키의 합이 100
 * - 키는 100을 넘지 않는 자연수, 아홉 난쟁이의 키는 모두 다르며, 가능한 정답이 여러 가지인 경우에는 아무거나 출력
 * - 키를 오름차순으로 출력한다. 일곱 난쟁이를 찾을 수 없는 경우는 없다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> nums = new ArrayList<>();

        // 아홉 개의 줄에 걸쳐 난쟁이들의 키가 주어진다.
        for (int i = 0; i < 9; i++) {
            nums.add(Integer.parseInt(br.readLine()));
        }

        // 전체 키의 합을 구한다.
        int sum = nums.stream().mapToInt(i -> i).sum();

        // 2명을 골라서 제거했을 때 나머지 합이 100인 경우를 찾는다.
        outer:
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                int num1 = nums.get(i);
                int num2 = nums.get(j);
                if (sum - (num1 + num2) == 100) {
                    nums.remove(new Integer(num1));
                    nums.remove(new Integer(num2));
                    break outer;
                }
            }
        }

        nums.sort(Integer::compareTo);
        nums.forEach(System.out::println);
    }

}