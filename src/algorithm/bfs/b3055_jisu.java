package algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b3055_jisu {

    static int[][] dr = {{0, 1, 0, -1}, {1, 0, -1, 0}};
    static int r, c;

    public static boolean isOut(int y, int x) {
        return y < 0 || y >= r || x < 0 || x >= c;
    }

    public static class Hedgehog {
        int y, x, t;
        public Hedgehog(int y, int x, int t) {
            this.y = y;
            this.x = x;
            this.t = t;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        Map<Character, int[]> animal = new HashMap<>();

        String[] map = new String[r];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine();
            for (int j = 0; j < c; j++) {
                if (map[i].charAt(j) == 'S') animal.put('S', new int[] {i, j});
                else if (map[i].charAt(j) == 'D') animal.put('D', new int[] {i, j});
            }
        }

        int waterStatus = 0;
        boolean[][] waterMap = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i].charAt(j) == '*') waterMap[i][j] = true;
            }
        }

        Queue<Hedgehog> que = new ArrayDeque<>();
        que.add(new Hedgehog(animal.get('S')[0], animal.get('S')[1], 0));
        boolean[][] visit = new boolean[r][c];
        visit[animal.get('S')[0]][animal.get('S')[1]] = true;

        while (!que.isEmpty()) {
            Hedgehog dochi = que.poll();
            if (dochi.y == animal.get('D')[0] && dochi.x == animal.get('D')[1]) {
                System.out.println(dochi.t);
                return;
            }

            boolean[][] nowMap = new boolean[r][c];
            if (waterStatus != dochi.t + 1)  {
                for (int i = 0; i < r; i++) System.arraycopy(waterMap[i], 0, nowMap[i], 0, c);

                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (waterMap[i][j]) {
                            for (int k = 0; k < 4; k++) {
                                int y = i + dr[0][k];
                                int x = j + dr[1][k];
                                if (isOut(y,x) || map[y].charAt(x) == 'D' || map[y].charAt(x) == 'X') continue;
                                nowMap[y][x] = true;
                            }
                        }
                    }
                }

                for (int i = 0; i < r; i++) System.arraycopy(nowMap[i], 0, waterMap[i], 0, c);
                waterStatus = dochi.t + 1;
            }

            for (int i = 0; i < 4; i++) {
                int y = dochi.y + dr[0][i];
                int x = dochi.x + dr[1][i];
                if (isOut(y,x) || visit[y][x] || map[y].charAt(x) == 'X') continue;
                if (waterMap[y][x]) continue;
                que.add(new Hedgehog(y,x,dochi.t+1));
                visit[y][x] = true;
            }
        }

        System.out.println("KAKTUS");
        return;
    }
}
