import java.util.*;

// 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 
// 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
// 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
// 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
// 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
// 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 
// 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
// 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.

public class Song implements Comparable<Song>{
    int num;
    int play;
    
    public Song(int num, int play){
        this.num = num;
        this.play = play;
    }
    
    @Override
    public int compareTo(Song o){
        if(this.play == o.play) return this.num - o.num;
        else return o.play - this.play;
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> genreMap = new HashMap<>();
        HashMap<String, ArrayList<Song>> genreSongMap = new HashMap<>();
   
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        // 1. HashMap<장르, 총 재생 횟수> map 으로 장르의 순서를 구한다.
        // => 장르 리스트를 구한다.
        
        // 2. 장르 내에서 곡들의 순서를 구해야 한다.
        // => HashMap<장르, 곡 리스트> map 을 저장한다.
        
        for(int i=0; i<genres.length; i++){
            genreMap.put(genres[i], genreMap.getOrDefault(genres[i], 0) + plays[i]);
            ArrayList<Song> list = genreSongMap.getOrDefault(genres[i], new ArrayList<>());
            list.add(new Song(i, plays[i]));
            genreSongMap.put(genres[i], list);
        }
    
        // 1. 재생횟수가 많을 수록, 곡 번호가 작을 수록 앞으로 정렬한다.
        // 2. 다시 map에 넣는다.
        for(String key : genreSongMap.keySet()){
            ArrayList<Song> list = genreSongMap.get(key);
            list.sort(Song::compareTo);
            genreSongMap.put(key, list);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        // 장르 해시맵을 순서대로 꺼내서 숫자들을 정렬하고 해당 숫자를 가지는 장르를 차례대로 꺼낸다.
        for(String key : genreMap.keySet()){
            list.add(genreMap.get(key));
        }
        list.sort(Comparator.reverseOrder());
        
        // 장르 리스트 순서대로 장르를 맵에서 꺼내고 해당 곡리스트를 꺼내서 고유번호 순대로 answer에 idx++해서 넣는다.
        int idx = 0;
        for(Integer cnt : list){
            for(String key : genreMap.keySet()){
                if(genreMap.get(key) == cnt){
                    int c = 0;
                    for(Song song : genreSongMap.get(key)){
                        ans.add(song.num);
                        c++;
                        if(c == 2) break;
                    }
                }
            }
        }
        
        int[] answer = new int[ans.size()];
        for(int i=0 ; i<ans.size(); i++){
          answer[i] = ans.get(i);  
        }
        return answer;
    }
}