package algorithm.implement;

import java.io.*;
import java.util.*;

public class c_cooling_system_changyeong {
    static int N,M,K,time;
    static int dx[] = {0,0,0,-1,0,1};
    static int dy[] = {0,0,-1,0,1,0};
    static int spreadX[][] = {{0,0,0}, {0,0,0}, {0, -1, 1}, {-1, -1, -1}, {0, -1, 1}, {1, 1, 1}};
    static int spreadY[][] = {{0,0,0}, {0,0,0}, {-1, -1, -1}, {0, -1, 1}, {1, 1, 1}, {0, -1, 1}};
    static int room[][];
    static int cooling[][];
    static boolean isBlowed[][];
    static boolean wall[][][][];
    static List<Pos> office, air;
    static Deque<Pos> que;

    public static void blowWind(){
        cooling = new int[N][N];

        for(Pos robot : air){
            isBlowed = new boolean[N][N];
            que.clear();

            int rx = robot.x;
            int ry = robot.y;
            int idx = robot.dir;

            //에어칸 바로 앞 값 저장
            rx += dx[idx];
            ry += dy[idx];
            isBlowed[rx][ry] = true;
            cooling[rx][ry] += 5;

            que.add(new Pos(rx,ry,2));
            while(!que.isEmpty()){
                Pos cur = que.poll();
                int x = cur.x;
                int y = cur.y;
                int tmp = cur.dir;

                if(tmp > 5){
                    continue;
                }

                for(int d = 0; d < 3; d++){
                    int nx = x + spreadX[idx][d];
                    int ny = y + spreadY[idx][d];
                    if(!isValid(nx,ny) || isBlowed[nx][ny] || isWall(x,y,nx,ny,idx)){
                        //확산 안되는 경우(범위 밖, 이미 방문, 벽 존재)
                        continue;
                    }

                    isBlowed[nx][ny] = true;
                    cooling[nx][ny] += 5 - tmp + 1;
                    que.add(new Pos(nx, ny, tmp + 1));
                }
            }

        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                room[i][j] += cooling[i][j];
            }
        }
    }

    public static boolean isValid(int x, int y){
        if(x >= 0 && x < N && y >= 0 && y < N){
            return true;
        }
        return false;
    }

    public static boolean isWall(int x, int y, int nx, int ny, int dir){
        if(x == nx && y == ny){
            return !wall[x][y][nx][ny];
        }
        else{
            if((dir == 2 || dir == 4)){
                if(wall[x][y][nx][y] || wall[nx][y][nx][ny]){
                    return true;
                }
            }
            else{
                if(wall[x][y][x][ny] ||wall[x][ny][nx][ny]){
                    return true;
                }
            }
        }
        return false;
    }



    public static void spread(){
        cooling = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int d = 2; d < 6; d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(!isValid(nx,ny) || wall[i][j][nx][ny]){
                        continue;
                    }

                    int diff = room[i][j] - room[nx][ny];
                    if(diff > 0){
                        int trans = (int) (diff / 4);
                        cooling[i][j] -= trans;
                        cooling[nx][ny] += trans;
                    }
                }
            }
        }

        for(int i=0; i<N;i++){
            for(int j=0;j<N;j++){
                room[i][j] += cooling[i][j];
            }
        }
    }

    public static void decreaseCoolness(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i == 0 || i == N - 1 || j == 0 || j == N - 1){
                    if(room[i][j] > 0){
                        room[i][j]--;
                    }
                }
            }
        }
    }

    public static boolean checkOffice(){
        for(Pos cur : office){
            if(room[cur.x][cur.y] < K){
                return false;
            }
        }

        return true;
    }

    public static void work(){
        while(time <= 100 && !checkOffice()){
            blowWind();
            spread();
            decreaseCoolness();
            time++;
            if(checkOffice()){
                return;
            }
        }
        time = -1;
    }

    public static void pre() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        time = 0;

        room = new int[N][N];
        office = new ArrayList<>();
        air = new ArrayList<>();
        wall = new boolean[N][N][N][N];
        que = new ArrayDeque<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                room[i][j] = 0;
                int num = Integer.parseInt(st.nextToken());
                if(num == 1){
                    office.add(new Pos(i,j));
                }
                else if(num != 0){
                    air.add(new Pos(i,j,num));
                }
            }
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            if(dir == 0) {
                wall[x][y][x - 1][y] = true;
                wall[x - 1][y][x][y] = true;
            }
            else {
                wall[x][y][x][y-1] = true;
                wall[x][y-1][x][y] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        pre();
        work();
        System.out.println(time);
    }
    
    static class Pos{
        int x,y,dir;

        public Pos(int x,int y){
            this.x=x;
            this.y=y;
            this.dir = -1;
        }

        public Pos(int x,int y, int dir){
            this.x=x;
            this.y=y;
            this.dir=dir;
        }
    }
}