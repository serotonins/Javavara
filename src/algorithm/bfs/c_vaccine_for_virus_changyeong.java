package algorithm.bfs;

import java.io.*;
import java.util.*;

public class c_vaccine_for_virus_changyeong {
    static int N, M, result;
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int city[][];
    static boolean isVisited[][];
    static List<Pos> hosp;
    static HashSet<Pos> vacc;
    static HashSet<Pos> virus;
    static Deque<Pos> que;

    public static void combine(int index, int depth){
        if(depth == M){
            bfs();
            return;
        }

        for(int i = index; i < hosp.size(); i++){
            vacc.add(hosp.get(i));
            combine(i + 1, depth + 1);
            vacc.remove(hosp.get(i));
        }
    }

    public static void bfs(){
        isVisited = new boolean[N][N];
        que.clear();
        int check = 0;
        boolean flag = true;

        for(Pos cur : vacc){
            que.add(cur);
        }        

        while(!que.isEmpty()){
            Pos cur = que.poll();

            if(isVisited[cur.x][cur.y]) continue;

            isVisited[cur.x][cur.y] = true;
            if(city[cur.x][cur.y] == 0){
                check = Math.max(cur.time, check);
            }

            for(int idx = 0; idx < 4; idx++){
                int nx = cur.x + dx[idx];
                int ny = cur.y + dy[idx];

                if(isValidPosition(nx,ny) && !isVisited[nx][ny]){
                    que.add(new Pos(nx,ny,cur.time+1));
                }
            }
        }

        for(Pos next : virus){
            if(!isVisited[next.x][next.y]){
                // System.out.println(next.x+" "+next.y);
                flag = false;
                break;
            }
        }

        if(flag){
            //모든 바이러스가 사라진 경우만
            result = Math.min(result, check);
        }
    }

    public static boolean isValidPosition(int x, int y){
        if(x>=0 && x<N && y>=0 && y<N && city[x][y] != 1){
            return true;
        }

        return false;
    }

    public static void pre() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = Integer.MAX_VALUE;

        city = new int[N][N];
        hosp = new ArrayList<>();
        vacc = new HashSet<>();
        virus = new HashSet<>();
        que = new ArrayDeque<>();

        for(int i = 0;i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                city[i][j] = Integer.parseInt(st.nextToken());
                if(city[i][j] == 2){
                    hosp.add(new Pos(i,j));
                }
                else if(city[i][j] == 0){
                    virus.add(new Pos(i,j));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        pre();
        combine(0,0);
        System.out.println(result==Integer.MAX_VALUE?-1:result);
    }

    static class Pos{
        int x, y, time;

        public Pos(int x, int y){
            this.x=x;
            this.y=y;
            this.time=0;
        }

        public Pos(int x, int y, int time){
            this.x=x;
            this.y=y;
            this.time=time;
        }

    }
}
