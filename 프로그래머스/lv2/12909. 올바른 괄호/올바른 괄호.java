import java.util.*;

// 괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다.
// '(' 또는 ')' 로만 이루어진 문자열 s가 주어졌을 때, 문자열 s가 올바른 괄호이면 true를 return 하고, 
// 올바르지 않은 괄호이면 false를 return 하는 solution 함수를 완성해 주세요.
class Solution {
    
    boolean solution(String s) {
        
        // 스택을 사용한다.
        // '(' 일경우 스택에 넣고 ')'  경우 스택에서 제거한다.
        // 마지막에 스택에 비어있는 경우 TRUE / 아니면 FALSE
        Stack<Character> stack = new Stack<>();
        
        for(char c : s.toCharArray()){
            if(c == '(') stack.push(c);
            else if(!stack.isEmpty() && c == ')') {
                stack.pop();
            }else return false;
        }
        
        if(stack.isEmpty()) return true;
        else return false;
    }
}