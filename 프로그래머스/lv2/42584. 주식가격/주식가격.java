import java.util.*;

// 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 
// 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[prices.length];
        
        // 1. prices 배열들을 순서대로 해당 인덱스를 스택에 담는다.
        // 2. 만약 스택에 peek한 가격보다 현재 입력값이 작다면 peek에 해당하는 정답 배열 값을 업데이트 한다.
        // 3. 입력값이 크다면 그대로 push 한다.
        for(int i=0; i<prices.length; i++){
            while(!stack.isEmpty() && prices[i] < prices[stack.peek()]){
                answer[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty()){
            answer[stack.peek()] = (prices.length - 1) - stack.pop();
        }
        
        return answer;
    }
}