package algorithm.implement;

import java.io.*;
import java.util.*;

public class p87377_changyeong {
    static long x, y, minx, miny, maxx, maxy, height, width;
    static int rx, ry;
    static String answer[];
    static HashSet<Pos> pset;
    static StringBuilder sb;
    
    public static void makeResult(){
        height = maxy - miny + 1;
        width = maxx - minx + 1;
        
        answer = new String[(int)height];
        
        for(int i = 0; i < width; i++){
            sb.append(".");
        }
        
        Arrays.fill(answer, sb.toString());
        
        for(Pos cur : pset){
            rx = (int) (cur.x - minx);
            ry = (int) (maxy - cur.y);
            
            answer[ry] = answer[ry].substring(0, rx) + "*" + answer[ry].substring(rx + 1);
        }
    }
    
    public static void findStar(int line[][]){
        long a1,b1,c1,a2,b2,c2,parallel,cross_x,cross_y;
        //a1x + b1y + c1 = 0
        //a2x + b2y + c2 = 0
        for(int i = 0; i < line.length - 1; i++){
            a1 = line[i][0];
            b1 = line[i][1];
            c1 = line[i][2];
            
            for(int j = i + 1; j < line.length; j++){
                a2 = line[j][0];
                b2 = line[j][1];
                c2 = line[j][2];
                
                parallel = a1 * b2 - a2 * b1;
                
                if(parallel == 0 ){
                    continue;
                }
                
                //y = -(a1x + c1) / b1
                //y = -(a2x + c2) / b2
                //(a1x + c1) * b2 = (a2x + c2) * b1
                //(a1 * b2 - a2 * b1)x = b1 * c2 - b2 * c1
                //x = (b1 * c2 - b2 * c1) / (a1 * b2 - a2 * b1) = (b1 * c2 - b2 * c1) / parallel
                
                //x = -(b1y + c1) / a1
                //x = -(b2y + c2) / a2
                //(b1y + c1) * a2 = (b2y + c2) * a1
                //(b1 * a2 - b2 * a1)y = a1 * c2 - a2 * c1
                //y = (a1 * c2 - a2 * c1) / (a2 * b1 - a1 * b2) = (a1 * c2 - a2 * c1) / parallel
                
                cross_x = (b1 * c2 - b2 * c1) / parallel;
                cross_y = (a2 * c1 - a1 * c2) / parallel;
                
                if(((b1 * c2 - b2 * c1) % parallel) != 0){
                    //0이 아닌 경우 x좌표값이 정수가 아님
                    continue;
                }
                
                if(((a2 * c1 - a1 * c2) % parallel) != 0){
                    //0이 아닌 경우 y좌표값이 정수가 아님
                    continue;
                }
                
                pset.add(new Pos(cross_x, cross_y));
                
                minx = Math.min(minx, cross_x);
                miny = Math.min(miny, cross_y);
                maxx = Math.max(maxx, cross_x);
                maxy = Math.max(maxy, cross_y);
            }
        }
    }
    
    public String[] solution(int[][] line) {
        minx = Long.MAX_VALUE;
        miny = Long.MAX_VALUE;
        maxx = Long.MIN_VALUE;
        maxy = Long.MIN_VALUE;
        
        pset = new HashSet<>();
        
        sb = new StringBuilder();
        
        findStar(line);
        makeResult();
        
        return answer;
    }
    
    static class Pos{
        long x,y;
        
        public Pos(long x, long y){
            this.x=x;
            this.y=y;
        }
    }
}
