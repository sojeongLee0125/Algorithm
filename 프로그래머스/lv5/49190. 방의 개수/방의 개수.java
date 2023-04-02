import java.util.*;

// 원점(0,0)에서 시작해서 아래처럼 숫자가 적힌 방향으로 이동하며 선을 긋습니다.
// 그림을 그릴 때, 사방이 막히면 방하나로 샙니다.
// 이동하는 방향이 담긴 배열 arrows가 매개변수로 주어질 때, 방의 갯수를 return 하도록 solution 함수를 작성하세요.

class Solution {
    
    int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    
    class Point{
        int y;
        int x;
        
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
        
        public int hashCode(){
            return Objects.hash(y, x);
        }
        
        public boolean equals(Object o){
            return this.y == ((Point) o).y && this.x == ((Point)o).x;
        }
    }
    
    public int solution(int[] arrows) {
        int answer = 0;
        
        // 1. 방문했던 정점을 다시 방문하는 경우 방이 1개 생성된다.
        //  - 단 이미 통과한 간선을 다시 통과하는 경우에는 다시 방문해도 방이 생기지 않는다.
        // 2. 정점 단위가 작아 간선끼리 교차가 발생할 수 있다. -> 단위를 스케일 업
        
        // 방문했던 점은 해쉬맵의 키값에 넣고, 그 점과 연결되었던 점들은 리스트화해서 추가한다.
        // 방문할 점의 경우 기존의 점과 연결되어 있는지를 리스트 내에서 확인한 후 카운트를 올려야 한다.
        
        Point p = new Point(0, 0); // 시작점
        HashMap<Point, ArrayList<Point>> chk = new HashMap<>();
        
        for(int dir : arrows){
            for(int i=0; i<2; i++) { // 교차점 처리용 스케일업 (반복을 2번)
                  
                // 다음 이동점
                Point np = new Point(p.y + dy[dir], p.x + dx[dir]);
                
                // 처음 방문하는 정점일 경우
                if(!chk.containsKey(np)){
                    ArrayList<Point> list = new ArrayList<>();
                    list.add(p);
                    chk.put(np, list);
                    
                    // 이전에 왔던 점에도 정보 추가
                    if(chk.get(p) == null){
                        ArrayList<Point> list2 = new ArrayList<>();
                        list2.add(np);
                        chk.put(p, list2);
                    }else{
                        chk.get(p).add(np);
                    }
                }
                // 재방문 했는데 간선을 처음으로 통과하는 경우
                else if(chk.containsKey(np) && !(chk.get(np).contains(p))){
                    chk.get(np).add(p);
                    chk.get(p).add(np);
                    answer++;
                }
                
                p = np;
           }
        }
                        
      return answer;
    }
}