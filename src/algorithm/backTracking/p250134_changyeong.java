package algorithm.backTracking;

import java.io.*;
import java.util.*;

public class p250134_changyeong {
    static int N,M,answer;
    static boolean isRedEnd, isBlueEnd;
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};
    static boolean isVisited[][][];
    
    public static int backtracking(int maze[][], Pos red, Pos blue, int num){
        if(isRedEnd && isBlueEnd){
            return num;
        }
        
        int cnt = Integer.MAX_VALUE;
        boolean tmp1, tmp2;
        
        tmp1 = isRedEnd;
        tmp2 = isBlueEnd;
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Pos newRed = red;
                Pos newBlue = blue;
            
                if(!isRedEnd){
                    newRed = new Pos(red.x + dx[i], red.y + dy[i]);
                }
                if(!isBlueEnd){
                    newBlue = new Pos(blue.x + dx[j], blue.y + dy[j]);
                }
            
                if(!isValid(maze, red, blue, newRed, newBlue)){
                    continue;
                }
            
                isVisited[0][newRed.x][newRed.y] = true;
                isVisited[1][newBlue.x][newBlue.y] = true;
            
                if(maze[newRed.x][newRed.y] == 3){
                    isRedEnd = true;
                }
                if(maze[newBlue.x][newBlue.y] == 4){
                    isBlueEnd = true;
                }
                
                cnt = Math.min(cnt, backtracking(maze, newRed, newBlue, num+1));
            
                isVisited[0][newRed.x][newRed.y] = false;
                isVisited[1][newBlue.x][newBlue.y] = false;
                isRedEnd = tmp1;
                isBlueEnd = tmp2;
            }
        }
        
        return cnt;
    }
    
    public static boolean isValid(int maze[][], Pos red, Pos blue, Pos newRed, Pos newBlue){
        if(newRed.x < 0 || newRed.y < 0 || newRed.x >= N || newRed.y >= M){
            return false;
        }
        if(newBlue.x < 0 || newBlue.y < 0 || newBlue.x >= N || newBlue.y >= M){
            return false;
        }
        
        if(maze[newRed.x][newRed.y] == 5 || maze[newBlue.x][newBlue.y] == 5){
            return false;
        }
        
        if((red.x == newBlue.x && red.y == newBlue.y) && (blue.x == newRed.x && blue.y == newRed.y)){
            return false;
        }
        
        if((!isRedEnd && isVisited[0][newRed.x][newRed.y])
           || (!isBlueEnd && isVisited[1][newBlue.x][newBlue.y])){
            return false;
        }
        
        if(newRed.x == newBlue.x && newRed.y == newBlue.y){
            return false;
        }
        
        return true;
    }
    
    public int solution(int[][] maze) {
        Pos Red, Blue;
        
        Red = null;
        Blue = null;
        isRedEnd = false;
        isBlueEnd = false;
        
        N = maze.length;
        M = maze[0].length;
        isVisited = new boolean[2][N][M];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maze[i][j] == 1){
                    Red = new Pos(i,j);
                }
                else if(maze[i][j] == 2){
                    Blue = new Pos(i,j);
                }
            }
        }
        isVisited[0][Red.x][Red.y] = true;
        isVisited[1][Blue.x][Blue.y] = true;
        answer = backtracking(maze, Red, Blue, 0);
        
        return answer == Integer.MAX_VALUE ? 0 : answer ;
    }
    
    static class Pos{
        int x,y;
        
        public Pos(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}
