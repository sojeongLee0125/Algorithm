import java.util.*;

class Solution {
    public int solution(int[] coin, int k) {
        int answer = Integer.MAX_VALUE;
        int initSum = 0;

        for(int a : coin) initSum += a;

        if(initSum == 0 || initSum == coin.length) return 0; // 최초에 모두 같은 면인 경우
        if(coin.length == k && (initSum != 0 || initSum != coin.length)) return -1; // 불가능한 경우

        int flipOne = flip(Arrays.copyOf(coin, coin.length), k, 1);
        int flipZero = flip(Arrays.copyOf(coin, coin.length), k, 0);
        answer = Math.min(flipOne, flipZero);

        if(answer == Integer.MAX_VALUE) return -1;
        else return answer;
    }

    public int flip(int[] coins, int k, int flag){
        int rs = 0;

        for(int i=0; i <= coins.length-k ; i++){
            if(coins[i] == flag) continue;

            for(int j=0; j<k; j++){
                coins[i+j] = (coins[i+j] + 1) % 2; // 뒤집기
            }
            
            rs++;
        }

        int cnt = 0;
        for(int c : coins){
            if(flag == c) cnt++;
        }

        if(cnt == coins.length) return rs;
        else return Integer.MAX_VALUE;
    }
}
