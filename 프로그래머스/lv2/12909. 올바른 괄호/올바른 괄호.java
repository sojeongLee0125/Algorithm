import java.util.*;

class Solution {
    Stack<Character> stack = new Stack<>();
    
    boolean solution(String s) {
        for(char c : s.toCharArray()){
            if(c == '(') stack.push(c);
            else{
                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
                else {
                    stack.push(c);
                    break;
                }
            }
        }
        
        if(stack.isEmpty()) return true;
        else return false;
    }
}