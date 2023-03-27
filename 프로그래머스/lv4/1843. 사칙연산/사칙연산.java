import java.util.*;

// 문자열 형태의 숫자와, 더하기 기호("+"), 뺄셈 기호("-")가 들어있는 배열 arr가 매개변수로 주어질 때, 
// 서로 다른 연산순서의 계산 결과 중 최댓값을 return 하도록 solution 함수를 완성해 주세요.

class Solution {
    
    // 최소(0) 최대(1) / 시작 인덱스 / 끝 인덱스
    int[][][] dp = new int[2][205][205];
    
    ArrayList<Integer> nums = new ArrayList<>(); // 숫자들 저장
    ArrayList<String> ops = new ArrayList<>(); // 연산자 저장
    
    public int sol(int flag, int st, int ed){
        
        // 범위가 일치하는 경우 = 숫자 하나인 경우
        if(st == ed){
            dp[flag][st][ed] = nums.get(st);
            return dp[flag][st][ed];
        }
        
        // dp 값 존재하는 경우
        if(flag == 0 && dp[flag][st][ed] != Integer.MAX_VALUE){
            return dp[flag][st][ed];
        }else if(flag == 1 && dp[flag][st][ed] != Integer.MIN_VALUE){
            return dp[flag][st][ed];
        }
        
        // 초기값 설정
        int rs = 0;
        if(flag == 0) rs = Integer.MAX_VALUE; // 최솟값일 경우
        else if(flag == 1) rs = Integer.MIN_VALUE; // 최댓값일 경우
        
        // DP 갱신
        dp[flag][st][ed] = 0;
        
        // 최솟값을 구하는 경우
        if(flag == 0){
            for(int i=st; i<ed; i++){
                // 구간 간 - 연산자의 경우 "최소 - 최대"가 되어야 한다.
                if(ops.get(i).equals("-")){
                    rs = Math.min(rs, sol(0, st, i) - sol(1, i+1, ed));
                }else{
                    // 최소 + 최소
                    rs = Math.min(rs, sol(0, st, i) + sol(0, i+1, ed));
                }
            }
        }else{
            //최댓값을 구하는 경우
            for(int i=st; i<ed; i++){
                // 구간 간 - 연산자의 경우 최대 - 최소가 되어야 한다.
                if(ops.get(i).equals("-")){
                    rs = Math.max(rs, sol(1, st, i) - sol(0, i+1, ed));
                }else{
                    //최대 + 최대
                    rs = Math.max(rs, sol(1, st, i) + sol(1, i+1, ed));
                }
            }
        }
        
        return dp[flag][st][ed] = rs;
    }
    
    public int solution(String arr[]) {
        
        // 숫자/문자 배열 따로 저장
        for(int i=0; i<arr.length; i++){
            if(i % 2 == 0) nums.add(Integer.parseInt(arr[i]));
            else ops.add(arr[i]);
        }
        
        // dp 배열 초기화
        for(int i=0; i < nums.size(); i++){
            for(int j=0; j < nums.size(); j++){
                // 최솟값
                dp[0][i][j] = Integer.MAX_VALUE;
                // 최댓값 
                dp[1][i][j] = Integer.MIN_VALUE;
            }
        }
        
        int answer = sol(1, 0, nums.size() - 1);
        
        return answer;
    }

}