package algorithm.dfs;

import java.io.*;
import java.util.*;

public class b1446_changyeong {
    static int N, D;
    static int dist[][];
    static List<Pos> road;

    public static int dfs(int src, int index) {
        int result = D;

        if (index == N) {
            return D;
        }

        if (dist[src][index] != 0) {
            return dist[src][index];
        }

        int start = road.get(index).src;
        int end = road.get(index).dest;
        int distance = road.get(index).dist;

        if (src <= start && end <= D) {
            int shortcut = dfs(end, index + 1) + distance - (end - start);
            result = Math.min(result, shortcut);
        }
        result = Math.min(result, dfs(src, index + 1));
        dist[src][index] = result;

        return result;
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        road = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            road.add(new Pos(src, dest, dist));
        }

        dist = new int[10001][N];
        Collections.sort(road);
    }

    public static void main(String[] args) throws IOException {
        pre();
        System.out.println(dfs(0, 0));
    }

    static class Pos implements Comparable<Pos> {
        int src, dest, dist;

        public Pos(int src, int dest, int dist) {
            this.src = src;
            this.dest = dest;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos o) {
            return this.src - o.src;
        }
    }
}
