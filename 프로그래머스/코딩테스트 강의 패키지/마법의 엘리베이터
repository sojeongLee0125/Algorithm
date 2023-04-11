import java.util.*;

class Solution {
  public int solution(int storey) {
    return calc(storey);
  }
  
  private int calc(int cur) {
    if(cur <= 1) return cur;
    
    int bottom = cur % 10;
    int rest = cur / 10;

    int a1 = bottom + calc(rest);
    int a2 = (10 - bottom) + calc(rest + 1);
    
    return Math.min(a1, a2);
  }
    
}
