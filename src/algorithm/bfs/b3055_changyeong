package algorithm.bfs;

import java.io.*;
import java.util.*;

public class b3055_changyeong{
    static int R, C, current;
    static int dx[] = { 1, 0, -1, 0 };
    static int dy[] = { 0, 1, 0, -1 };
    static String forest[][];
    static Boolean isVisited[][];
    static Boolean isFlooded[][];
    static Deque<Pos> que;
    static Deque<Pos> water;

    public static void flooding() {
        int size = water.size();
        for (int x = 0; x < size; x++) {
            Pos cur = water.poll();

            for (int idx = 0; idx < 4; idx++) {
                int ax = cur.x + dx[idx];
                int ay = cur.y + dy[idx];

                if (isMovable(ax, ay) && !isFlooded[ax][ay] && !forest[ax][ay].equals("D")
                        && !forest[ax][ay].equals("S")) {
                    isFlooded[ax][ay] = true;
                    water.add(new Pos(ax, ay, 0));
                }
            }
        }
    }

    public static void bfs() {
        while (true) {
            flooding();

            int size = que.size();
            if (size == 0) {
                break;
            }

            for (int x = 0; x < size; x++) {
                Pos cur = que.poll();

                for (int idx = 0; idx < 4; idx++) {
                    int ax = cur.x + dx[idx];
                    int ay = cur.y + dy[idx];

                    if (!isMovable(ax, ay) || isVisited[ax][ay]) {
                        continue;
                    }

                    if (forest[ax][ay].equals("D")) {
                        current = cur.time + 1;
                        return;
                    }

                    if (isMovable(ax, ay) && !isFlooded[ax][ay]) {
                        isVisited[ax][ay] = true;
                        que.add(new Pos(ax, ay, cur.time + 1));
                    }
                }
            }
        }
    }

    public static boolean isMovable(int i, int j) {
        if (i >= 0 && i < R && j >= 0 && j < C && !forest[i][j].equals("X") && !forest[i][j].equals("*")) {
            return true;
        }

        return false;
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        current = -1;

        forest = new String[R][C];
        isVisited = new Boolean[R][C];
        isFlooded = new Boolean[R][C];
        que = new ArrayDeque<>();
        water = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                forest[i][j] = Character.toString(input.charAt(j));
                isVisited[i][j] = false;
                isFlooded[i][j] = false;

                if (forest[i][j].equals("S")) {
                    que.add(new Pos(i, j, 0));
                } else if (forest[i][j].equals("*")) {
                    water.add(new Pos(i, j, 0));
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        bfs();
        System.out.println(current == -1 ? "KAKTUS" : current);
    }

    static class Pos {
        int x, y, time;

        public Pos() {
        }

        public Pos(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}