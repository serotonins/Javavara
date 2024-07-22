package algorithm.bfs;

import java.util.*;

class p169199_jisu {
    static int n, m;
    static int[][] dr = {{0,1,0,-1}, {1,0,-1,0}};

    public boolean isOut(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= m;
    }

    public class Robot implements Comparable<Robot> {
        int y, x, time;
        public Robot(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
        public int compareTo(Robot o) {
            return this.time - o.time;
        }
    }

    public int solution(String[] board) {
        int answer = Integer.MAX_VALUE;

        n = board.length;
        m = board[0].length();

        boolean[][] visit = new boolean[n][m];

        PriorityQueue<Robot> que = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    que.add(new Robot(i,j,0));
                    visit[i][j] = true;
                }
            }
        }

        while (!que.isEmpty() && answer == Integer.MAX_VALUE) {
            Robot now = que.poll();
            if (board[now.y].charAt(now.x) == 'G') {
                answer = now.time;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int y = now.y;
                int x = now.x;
                while (!isOut(y+dr[0][d],x+dr[1][d]) && board[y+dr[0][d]].charAt(x+dr[1][d]) != 'D') {
                    y += dr[0][d];
                    x += dr[1][d];
                }
                if (visit[y][x]) continue;
                que.add(new Robot(y,x,now.time+1));
                visit[y][x] = true;
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}