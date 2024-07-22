package algorithm.bfs;

import java.io.*;
import java.util.*;

public class p169199_changyeong {
    static int N,M,answer;
    static Pos robot, goal;
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static boolean isVisited[][];
    static Deque<Pos> que;
    
    public static void bfs(String board[]){
        que.add(robot);
        
        while(!que.isEmpty()){
            Pos cur = que.poll();
            
            if(cur.x == goal.x && cur.y == goal.y){
                answer = cur.depth;
                return;
            }
            
            if(isVisited[cur.x][cur.y]){
                continue;
            }
            
            isVisited[cur.x][cur.y] = true;
            
            for(int idx = 0; idx < 4; idx++){
                int cx = cur.x;
                int cy = cur.y;
                
                while(isValid(cx,cy) && board[cx].charAt(cy) != 'D'){
                    cx += dx[idx];
                    cy += dy[idx];
                }
                
                cx -= dx[idx];
                cy -= dy[idx];
                
                if(isVisited[cx][cy] || (cur.x == cx && cur.y == cy)){
                    //이동 전과 동일한 위치인 경우
                    continue;
                }
                
                que.add(new Pos(cx,cy,cur.depth + 1));
            }
        }
    }
    
    public static boolean isValid(int x,int y){
        return x >= 0 && y >= 0 && x < N && y < M;
    }
    
    public int solution(String[] board) {
        answer = -1;
        N = board.length;
        M = board[0].length();
        
        isVisited = new boolean[N][M];
        que = new ArrayDeque<>();
        
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < M; j++){
                char ch = board[i].charAt(j);
                
                if(ch == 'R'){
                    robot = new Pos(i,j,0);
                }
                else if(ch == 'G'){
                    goal = new Pos(i,j,0);
                }
            }
        }
        
        bfs(board);
        
        return answer;
    }
    
    static class Pos{
        int x,y,depth;
        
        public Pos(int x,int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}
