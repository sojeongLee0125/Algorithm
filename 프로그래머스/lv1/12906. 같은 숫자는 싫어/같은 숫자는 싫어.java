import java.util.*;

// 배열 arr의 각 원소는 숫자 0부터 9까지로 이루어져 있습니다.
// 배열 arr에서 연속적으로 나타나는 숫자는 하나만 남기고 전부 제거하려고 합니다
// 제거된 후 남은 수들을 반환할 때는 배열 arr의 원소들의 순서를 유지해야 합니다.
// 배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들을 return 하는 solution 함수를 완성해 주세요.

public class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
       
        // 0. 첫번째 값을 넣는다.
        stack.add(arr[0]);
        
        // 1. arr을 반복문을 돌리면서 Stack의 peek()과 같지 않으면 추가한다.
        for(int i=1; i<arr.length; i++){
            if(stack.peek() != arr[i]) stack.add(arr[i]);
        }
        
        // 2. Stack에서 차례대로 꺼내서 배열로 변환하여 반환한다.
        int[] answer = new int[stack.size()];
        for(int i=0; i<stack.size(); i++){
            answer[i] = stack.get(i);
        }

        return answer;
    }
}