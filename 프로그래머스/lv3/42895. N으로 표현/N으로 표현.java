import java.util.*;

class Solution {
    Set<Integer>[] set = new HashSet[9];
    
    public int solution(int N, int number) {
        int answer = -1;
        int num = N;
        
        // 1. 1-8 N 집합 만들기
        for(int i=1; i<9; i++){
            set[i] = new HashSet<>();
            set[i].add(num);
            num = (10 * num) + N;
        }
        
        for(int i=2; i<9; i++){
            for(int j=1; j<i; j++){
                for(int a : set[j]){
                    for(int b : set[i-j]){
                        set[i].add(a+b);
                        set[i].add(a-b);
                        set[i].add(a*b);
                        if(b != 0) set[i].add(a/b);
                    }
                }
            }
        }
        
        for(int i=1; i<9; i++){
            if(set[i].contains(number)){
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}