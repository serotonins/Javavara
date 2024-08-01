package algorithm.implement;

import java.io.*;
import java.util.*;

public class p87391_changyeong{
    static long answer,row_s,row_e,col_s,col_e;
    
    public void checkRange(int n, int m, int queries[][]){
        for(int i = queries.length -1; i >= 0; i--){
            int dir = queries[i][0];
            long cnt = queries[i][1];
            
            if(dir == 0){
                //좌->우
                if(col_s != 0){
                    col_s += cnt;
                }
                if(col_s >= m){
                    return;
                }
                
                //끝 범위는 경계에 닿거나 최대 칸수만큼 이동
                col_e = Math.min(col_e + cnt, m - 1);
            }
            else if(dir == 1){
                //우->좌
                if(col_e != m - 1){
                    col_e -= cnt;
                }
                if(col_e < 0){
                    return;
                }
                
                //끝 범위는 경계에 닿거나 최대 칸수만큼 이동
                col_s = Math.max(col_s - cnt, 0);
            }
            else if(dir == 2){
                //위->아래
                if(row_s != 0){
                    row_s += cnt;
                }
                if(row_s >= n){
                    return;
                }
                
                //끝 범위는 경계에 닿거나 최대 칸수만큼 이동
                row_e = Math.min(row_e + cnt, n - 1);
            }
            else if(dir == 3){
                //아래->위
                if(row_e != n - 1){
                    row_e -= cnt;
                }
                if(row_e < 0){
                    return;
                }
                
                //끝 범위는 경계에 닿거나 최대 칸수만큼 이동
                row_s = Math.max(row_s - cnt, 0);
            }
        }
        
        answer = (row_e - row_s + 1) * (col_e - col_s + 1);
    }
    
    public long solution(int n, int m, int x, int y, int[][] queries) {
        answer = 0;
        //각각 시작&끝 행과 열
        row_s = x;
        row_e = x;
        col_s = y;
        col_e = y;
        checkRange(n, m, queries);
        
        return answer;
    }
}