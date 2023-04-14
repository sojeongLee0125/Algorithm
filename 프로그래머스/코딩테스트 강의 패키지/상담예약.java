import java.util.*;

/*
* 1. 예약 고객 중 먼저 도착한 고객의 업무 먼저 처리
  2. 예약 고객이 없으면 먼저 도착한 일반 고객 업무 처리
  3. 시작한 업무는 중간에 중단하지 않음
  - 창구는 하나, 업무 처리 시간은 10분
  - 고객의 이름을 업무가 처리된 순으로 담은 배열을 리턴
*/

class Solution {

    public class Order{
        int time; // 도착시간
        String name; // 이름

        public Order(String time, String name){
            this.time = convertTime(time);
            this.name = name;
        } 
    }

    public int convertTime(String time){
        String[] strr = time.split(":");
        int h = Integer.parseInt(strr[0]);
        int m = Integer.parseInt(strr[1]);
        return h*60 + m;
    }

    public String[] solution(String[][] booked, String[][] unbooked) {
        ArrayList<String> answer = new ArrayList<>();

        Queue<Order> bq = new LinkedList<>();
        Queue<Order> ubq = new LinkedList<>();
        
        for(String[] arr : booked){
            bq.add(new Order(arr[0], arr[1]));
        }

        for(String[] arr : unbooked){
            ubq.add(new Order(arr[0], arr[1]));
        }
        
        // 시작 시간
        int t = Math.min(bq.peek().time, ubq.peek().time);

        while(!bq.isEmpty() || !ubq.isEmpty()){
            
            // 예약된 손님이 없는 경우
            if(bq.isEmpty()) {
                while(!ubq.isEmpty()) answer.add(ubq.poll().name); 
                break;
            }

            // 비예약된 손님이 더 이상 없는 경우
            if(ubq.isEmpty()) {
                while(!bq.isEmpty()) answer.add(bq.poll().name); 
                break;
            }

            Order bk = bq.peek();
            Order ubk = ubq.peek();

            // 현재 시간에서 예약 손님을 받을 수 있는 경우
            if(t >= bk.time){
                answer.add(bq.poll().name);
                t += 10;
            }else if(t >= ubk.time){
                // 현재 시간에서 비예약 손님을 받을 수 있는 경우
                answer.add(ubq.poll().name);
                t += 10;
            }else{
                // 현재 시간에 아무도 오지 않은 경우
                if(bk.time <= ubk.time){
                    // 예약 고객이 먼저 온 경우
                    Order c = bq.poll();
                    answer.add(c.name);
                    t = c.time + 10;
                }else{
                    // 비 예약 고객이 먼저 온 경우
                    Order c = ubq.poll();
                    answer.add(c.name);
                    t = c.time + 10;
                }
            }
        }

        return answer.toArray(new String[]{});
    }  
}
