import java.util.*;

class Solution {
    Stack<Integer> s = new Stack<>();
    
    public int[] solution(int[] prices) {
        int[] arr = new int[prices.length];
        
        // 1. prices를 순차적으로 순회하면서 현재 값이 stack의 peek 값보다 작은 경우 
        // 해당 peek 값의 정답배열에 현재 인덱스 - pop()을 넣는다.
        // 2. 작아지는 경우에는 curIdx - peek 값을  peek IDX의 초로 갱신하고 poll하고 curIdx 는 넣는다.
        
        for(int i=0; i<prices.length; i++){
            while(!s.isEmpty() && prices[i] < prices[s.peek()]){ // 가격이 떨어지는 경우
                arr[s.peek()] = i - s.pop();
            }
            // 현재 인덱스는 그대로 넣는다.
            s.push(i);
        }
        
        // 3. 스택에서 위에서부터 하나씩 lastIdx-peek 을 arr[peekIdx] 에 넣는다.
        while(!s.isEmpty()){
            int c = s.pop();
            arr[c] = (prices.length-1) - c;
        }
        
        return arr;
    }
}