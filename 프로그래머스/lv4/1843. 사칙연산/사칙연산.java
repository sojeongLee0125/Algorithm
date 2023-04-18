import java.util.*;

// 사칙연산에서 더하기(+)는 결합법칙이 성립하지만, 빼기(-)는 결합법칙이 성립하지 않습니다.
// 뺄셈은 연산 순서에 따라 그 결과가 바뀔 수 있습니다.
// 문자열 형태의 숫자와, 더하기 기호("+"), 뺄셈 기호("-")가 들어있는 배열 arr가 매개변수로 주어질 때, 
// 서로 다른 연산순서의 계산 결과 중 최댓값을 return 하도록 solution 함수를 완성해 주세요.

class Solution {
    ArrayList<String> ops = new ArrayList<>(); // 연산자
    ArrayList<Integer> nums = new ArrayList<>(); // 숫자
    int[][][] dp = new int[2][205][205]; // 최대(1) or 최소(0) flag, st, ed
    
    public int solution(String arr[]) {
        int answer = -1;
        
        // 1. 연산자와 숫자를 구분해서 저장하기
        for(String str : arr){
            if(str.equals("+") || str.equals("-")) ops.add(str);
            else nums.add(Integer.parseInt(str));
        }
        
        // 2. dp 배열 초기화
        for(int i=0; i<dp.length; i++){
            for(int j=0; j<dp[i].length; j++){
                if(i == 0) Arrays.fill(dp[i][j], Integer.MAX_VALUE);
                else if(i == 1) Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        
        return sol(1, 0, nums.size() - 1);
    }
    
    public int sol(int flag, int st, int ed){
        
        // 1. st == ed 일 경우 (값이 하나일 경우)
        if(st == ed){
            dp[flag][st][ed] = nums.get(st);
            return dp[flag][st][ed];
        }
        
        // 2. dp값이 존재하는 경우
        if((flag == 0 && dp[flag][st][ed] != Integer.MAX_VALUE) || (flag == 1 && dp[flag][st][ed] != Integer.MIN_VALUE)){
            return dp[flag][st][ed];
        }
        
        // 3. rs 값 초기화
        int rs = 0;
        if(flag == 0) rs = Integer.MAX_VALUE; // 최솟값을 구할 경우 최댓값으로 초기화
        else rs = Integer.MIN_VALUE; // 최댓값을 구할 경우 최솟값으로 초기화
        
        for(int i=st; i<ed; i++){
            
            // 최솟값을 구할 경우 
            if(flag == 0){
                // 기호가 - 일 경우 (최소 - 최대)가 되어야 한다.
                if(ops.get(i).equals("-")){
                    rs = Math.min(rs, sol(0, st, i) - sol(1, i+1, ed));
                } // 기호가 + 일 경우 (최소 - 최소)가 되어야 한다.
                else if(ops.get(i).equals("+")){
                    rs = Math.min(rs, sol(0, st, i) + sol(0, i+1, ed));
                }
            }
            // 최댓값을 구할 경우 
            else if(flag == 1){
                // 기호가 - 일 경우 (최대 - 최소)가 되어야 한다.
                if(ops.get(i).equals("-")){
                    rs = Math.max(rs, sol(1, st, i) - sol(0, i+1, ed));
                } // 기호가 + 일 경우 (최대 - 최대)가 되어야 한다.
                else if(ops.get(i).equals("+")){
                    rs = Math.max(rs, sol(1, st, i) + sol(1, i+1, ed));
                }
            }   
        }   
        
        return dp[flag][st][ed] = rs;
    }
}