package algorithm.implement;

import java.io.*;
import java.util.*;

public class c_rudolph_rebellion_changyeong {
    static int N,M,P,C,D,rr,rc;
    static int score[];
    static Santa santa[];
    static int dir[][] = {{-1, 0, 1, 0, -1, 1, -1, 1}, {0, 1, 0, -1, 1, 1, -1, -1}};
    static int map[][];
    static Deque<Pos> que;
    static PriorityQueue<Pos> pq;
    static StringBuilder sb;

    public static void collide(char moved, int d, int num){
        que.clear();
        Santa cur = santa[num];

        int tr = -1;
        int tc = -1;

        if(moved == 'C'){
            tr = cur.r + (dir[0][d] * C);
            tc = cur.c + (dir[1][d] * C);
        }
        else if(moved == 'D'){
            tr = rr - (dir[0][d] * D);
            tc = rc - (dir[1][d] * D);
            map[cur.r][cur.c] = 0;
        }

        que.add(new Pos(tr, tc, num));

        while(true){
            //상호작용
            if(!isValid(tr,tc) || map[tr][tc] == 0){
                //범위 밖이거나 다른 산타가 없는 경우
                break;
            }

            int santaNum = map[tr][tc];
            map[tr][tc] = 0;

            if(moved == 'C'){
                tr += dir[0][d];
                tc += dir[1][d];
            }
            else if(moved == 'D'){
                tr -= dir[0][d];
                tc -= dir[1][d];
            }

            que.add(new Pos(tr, tc, santaNum));
        }

        while(!que.isEmpty()){
            //맨 뒤에 밀린 산타부터 확인
            Pos now = que.pollLast();

            if(!isValid(now.r, now.c)){
                santa[now.num] = null;
                continue;
            }

            map[now.r][now.c] = now.num;
            if(que.size() == 0){
                santa[now.num] = new Santa(now.r, now.c, 2);
            }
            else{
                santa[now.num] = new Santa(now.r, now.c, santa[now.num].stun);
            }
        }
    }

    public static void moveSanta(){
        for(int i = 1; i <= P; i++){
            if(santa[i] == null || santa[i].stun > 0){
                //해당 산타가 탈락했거나 기절상태인 경우
                continue;
            }
            
            int sr = santa[i].r;
            int sc = santa[i].c;
            int d = 0;
            int max = (int) Math.pow(rr - santa[i].r, 2) + (int) Math.pow(rc - santa[i].c, 2);

            for(int idx = 0; idx < 4; idx++){
                int nr = santa[i].r + dir[0][idx];
                int nc = santa[i].c + dir[1][idx];
                if(!isValid(nr,nc) || map[nr][nc] > 0){
                    //범위 밖이거나 이미 다른 산타가 위치한 경우
                    continue;
                }

                int dist = (int) Math.pow(rr - nr, 2) + (int) Math.pow(rc - nc, 2);
                if(max > dist){
                    max = dist;
                    sr = nr;
                    sc = nc;
                    d = idx;
                }
            }

            if(sr == santa[i].r && sc == santa[i].c){
                //루돌프에 가까워질 수 있는 방법이 없는 경우
                continue;
            }

            if(map[sr][sc] == -1){
                //루돌프와 충돌하는 경우
                score[i] += D;
                collide('D', d, i);
            }
            else{
                map[sr][sc] = i;
                map[santa[i].r][santa[i].c] = 0;
                santa[i] = new Santa(sr, sc, 0);
            }
        }
    }

    public static void moveRudolph(){
        pq.clear();

        for(int i = 1; i <= P; i++){
            if(santa[i] == null){
                continue;
            }

            int d = (int) Math.pow(rr - santa[i].r, 2) + (int) Math.pow(rc - santa[i].c, 2);
            pq.add(new Pos(santa[i].r, santa[i].c, i, d));
        }

        int num = pq.poll().num;
        int max = Integer.MAX_VALUE;
        int tr = 0;
        int tc = 0;
        int direction = 0;

        for(int idx = 0; idx < 8; idx++){
            int nr = rr + dir[0][idx];
            int nc = rc + dir[1][idx];

            if(!isValid(nr,nc)){
                continue;
            }

            int d = (int) Math.pow(nr - santa[num].r, 2) + (int) Math.pow(nc - santa[num].c, 2);

            if(max > d){
                max = d;
                tr = nr;
                tc = nc;
                direction = idx;
            }
        }

        if(map[tr][tc] > 0){
            //루돌프 이동 위치에 산타가 있는 경우
            score[num] += C;
            collide('C', direction, num);
        }

        //루돌프 위치 이동
        map[rr][rc] = 0;
        map[tr][tc] = -1;
        rr = tr;
        rc = tc;
    }

    public static boolean isValid(int r, int c){
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    public static void decreaseStun(){
        for(int i = 1; i <= P; i++){
            if(santa[i] == null || santa[i].stun == 0){
                continue;
            }
            santa[i] = new Santa(santa[i].r, santa[i].c, santa[i].stun - 1);
        }
    }

    public static boolean checkSanta(){
        int cnt = 0;
        for(int i =1; i <= P; i++){
            if(santa[i] == null){
                cnt++;
            }
            else{
                score[i]++;
            }
        }

        if(cnt == P){
            return true;
        }
        return false;
    }

    public static void pre() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        score = new int[P+1];
        santa = new Santa[P+1];
        map = new int[N][N];
        que = new ArrayDeque<>();
        pq = new PriorityQueue<>();
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        rr = Integer.parseInt(st.nextToken()) - 1;
        rc = Integer.parseInt(st.nextToken()) - 1;

        //루돌프 위치 값 : -1
        map[rr][rc] = -1;

        for(int i = 0; i < P; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = num;
            santa[num] = new Santa(r,c,0);
        }
    }

    public static void result(){
        for(int i = 1; i <= P; i++){
            sb.append(score[i]).append(" ");
        }
    }
    
    public static void main(String[] args) throws IOException{
        pre();
        while(M > 0){
            M--;
            decreaseStun();
            moveRudolph();
            moveSanta();
            if(checkSanta()){
                break;
            }
        }
        result();
        System.out.println(sb);
    }

    static class Pos implements Comparable<Pos>{
        int r,c,num,dist;

        public Pos(int r, int c, int num){
            this.r = r;
            this.c = c;
            this.num = num;
        }

        public Pos(int r, int c, int num, int dist){
            this.r = r;
            this.c = c;
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos o){
            if(this.dist == o.dist){
                if(this.r == o.r){
                    return o.c - this.c;
                }
                return o.r - this.r;
            }
            return this.dist - o.dist;
        }
    }

    static class Santa{
        int r,c,stun;

        public Santa(int r, int c, int stun){
            this.r = r;
            this.c =c ;
            this.stun = stun;
        }
    }
    
}
