package algorithm.implement;

import java.io.*;
import java.util.*;

public class b16235_changyeong {
    static int N, M, K, result;
    static int dx[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static int dy[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int land[][], nutrient[][];
    static Deque<Integer> tree[][];
    static HashSet<Tree> deadTree;

    public static void spring() {

        deadTree.clear();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                int size = tree[i][j].size();

                for (int tt = 0; tt < size; tt++) {
                    int age = tree[i][j].poll();

                    if (land[i][j] < age) {
                        //현재 토지에 남은 양분보다 많은 양분을 나무가 먹어야될 경우
                        deadTree.add(new Tree(i, j, age));
                    } 
                    else {
                        //양분을 나이만큼 주고 성장
                        tree[i][j].add(age + 1);
                        land[i][j] -= age;
                    }
                }
            }
        }
    }
    
    public static void summer() {
        for (Tree deads : deadTree) {
            int r = deads.r;
            int c = deads.c;
            int age = deads.age;

            land[r][c] += (age / 2);
        }
    }
    
    public static void autumn() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                int size = tree[i][j].size();

                for (int tt = 0; tt < size; tt++) {
                    int age = tree[i][j].poll();

                    if (age % 5 == 0) {
                        for (int idx = 0; idx < 8; idx++) {
                            int rr = i + dx[idx];
                            int cc = j + dy[idx];

                            if (isAvailable(rr, cc)) {
                                tree[rr][cc].addFirst(1);
                            }
                        }
                    }

                    tree[i][j].add(age);
                }
            }
        }
    }
    
    public static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                land[i][j] += nutrient[i][j];
            }
        }
    }
    
    public static boolean isAvailable(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N) {
            return false;
        }

        return true;
    }

    public static void season() {
        for (int t = 1; t <= K; t++) {
            spring();
            summer();
            autumn();
            winter();
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                result += tree[i][j].size();
            }
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        result = 0;

        land = new int[N + 1][N + 1];
        nutrient = new int[N + 1][N + 1];
        tree = new ArrayDeque[N + 1][N + 1];
        deadTree = new HashSet<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                land[i][j] = 5;
                nutrient[i][j] = Integer.parseInt(st.nextToken());
                tree[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            tree[r][c].add(age);
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        season();
        System.out.println(result);
    }

    static class Tree implements Comparable<Tree>{
        public int r, c, age;

        public Tree() {
        }

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }
}
