package algorithm.bfs;

import java.io.*;
import java.util.*;

public class p81302_changyeong {
    static int answer[];
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static Deque<int[]> que;
    
    static boolean bfs(int R, int C, String p[]){        
        que.add(new int[]{R,C});
        
        boolean[][] isVisited = new boolean[5][5];
        isVisited[R][C] = true;
        
        while(!que.isEmpty()){
            int cur[] = que.poll();
            
            for(int idx = 0; idx < 4; idx++){
                int nx = cur[0] + dx[idx];
                int ny = cur[1] + dy[idx];
                
                if(!isMovable(nx,ny)){
                    continue;
                }
                
                if(!isVisited[nx][ny]){
                    int d = Math.abs(nx - R) + Math.abs(ny - C);
                    
                    if(d <= 2 && p[nx].charAt(ny) == 'P'){
                        return false;
                    }
                    else if(p[nx].charAt(ny) == 'O' && d < 2){
                        isVisited[nx][ny] = true;
                        que.add(new int[]{nx, ny});
                    }
                }
            }
        }
        
        return true;
    }
    
    public static boolean isMovable(int x, int y){
        if(x < 0 || y < 0 || x >= 5 || y >= 5){
            return false;
        }
        return true;
    }
    
    public int[] solution(String[][] places) {
        
        answer = new int[places.length];
        que = new ArrayDeque<>();
        
        int idx = 0;
        for(String place[] : places){
            String p[] = place;
            que.clear();
            
            boolean isValid = true;
            for(int i = 0; i < 5 && isValid; i++){
                for(int j = 0; j < 5 && isValid; j++){
                    if(p[i].charAt(j) == 'P'){
                        if(!bfs(i,j,p)){
                            isValid = false;
                        }
                    }
                }
            }
            
            answer[idx] = isValid ? 1 : 0;
            idx++;
        }
        
        return answer;
    }
}
