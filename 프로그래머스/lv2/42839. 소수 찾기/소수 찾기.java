import java.util.*;

// 한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
// 각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 
// 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

class Solution {
    
    int[] nums;
    int[] chk;
    int[] check = new int[99999999];
    int size, rs;
    
    public boolean isPrime(String num){
        int number = Integer.parseInt(num);
        if(number < 2) return false;
        for(int i=2; i <= (int) Math.sqrt(number); i++){
            if(number % i == 0) return false;
        }
        return true;
    }
    
    public void go(String str){
        for(int i=0; i<size; i++){
            if(chk[i] == 0){
                chk[i] = 1;
                String tmp = str + nums[i];
                if(check[Integer.parseInt(tmp)] != 1) if(isPrime(tmp)) rs++;
                // tmp 중복된 숫자는 제거
                check[Integer.parseInt(tmp)] = 1;
                go(tmp);
                chk[i] = 0;
            }
        }
    }
    
    public int solution(String numbers) {
        int answer = 0;
        size = numbers.length();
        nums = new int[size];
        chk = new int[size];
        
        // 숫자를 배열로 만든다.
        // 배열의 순열을 구한다.
        for(int i=0; i < numbers.length(); i++){
            nums[i] = numbers.charAt(i) - '0';
        }
        
        go("");
        
        // 해당 값이 소수라면 정답에 체크한다.
        return rs;
    }
}