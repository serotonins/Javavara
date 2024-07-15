package algorithm.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b16235_jisu {

    static int n, m, k, answer;
    static int[][] ground, yangboons, addLater, fiveTree;
    static int[][] dr = {{0,1,1,1,0,-1,-1,-1}, {1,1,0,-1,-1,-1,0,1}};
    static PriorityQueue<Integer>[][] trees;


    public static boolean isOut(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= n;
    }

    public static void spring() {
        addLater = new int[n][n];
        fiveTree = new int[n][n];
        ArrayDeque<Integer> deque;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int len = trees[i][j].size();
                deque = new ArrayDeque<>();
                for (int l = 0; l < len; l++) {
                    int t = trees[i][j].poll();
                    if (ground[i][j] < t) {
                        addLater[i][j] += t / 2;
                    } else {
                        ground[i][j] -= t;
                        deque.add(t+1);
                        if ((t+1)%5 == 0) fiveTree[i][j]++;
                    }
                }
                trees[i][j].addAll(deque);
            }
        }
    }

    public static void autumn() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ground[i][j] += addLater[i][j];
                if (fiveTree[i][j] > 0) {
                    for (int l = 0; l < 8; l++) {
                        int y = i + dr[0][l];
                        int x = j + dr[1][l];
                        if (isOut(y,x)) continue;
                        for (int o = 0; o < fiveTree[i][j]; o++) {
                            trees[y][x].add(1);
                        }
                    }
                }
            }
        }
    }

    public static void winter() {
        answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ground[i][j] += yangboons[i][j];
                answer += trees[i][j].size();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
        칸마다 일괄 5
        봄에는 나이만큼 양분을 먹고, 한 칸에 여러 나무 있으면 어린 애부터.
            나이만큼 양분 못 먹으면 즉사
        여름에는 즉사 나무가 양분됨 + 나이 / 2
        가을에는 5의 배수 나이인 나무가 인접 8칸에 번식(나이 1)
        겨울에는 양분 추가
         */

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        yangboons = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                yangboons[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ground = new int[n][n];
        trees = new PriorityQueue[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ground[i][j] = 5;
                trees[i][j] = new PriorityQueue<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees[y-1][x-1].add(z);
        }

        for (int i = 0; i < k; i++) {
            spring();
            autumn();
            winter();
        }

        System.out.println(answer);
    }
}
