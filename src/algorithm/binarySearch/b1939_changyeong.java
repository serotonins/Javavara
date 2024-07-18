package algorithm.binarySearch;

import java.io.*;
import java.util.*;

public class b1939_changyeong {
    static int N, M, A, B, l, r, m, result;
    static boolean isVisited[];
    static List<Bridge> connection[];

    public static void DFS(int src, int dest, int weight) {
        if (src == dest) {
            result = src;
            return;
        }

        isVisited[src] = true;
        for (Bridge b : connection[src]) {
            if (!isVisited[b.dest] && b.w >= weight) {
                DFS(b.dest, dest, weight);
            }
        }
    }

    public static void binaraySearch() {
        while (l <= r) {
            m = (l + r) / 2;
            result = -1;

            isVisited = new boolean[N + 1];

            DFS(A, B, m);

            if (result != -1) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        connection = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            connection[i] = new ArrayList<>();
        }

        l = 0;
        r = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            connection[src].add(new Bridge(dest, weight));
            connection[dest].add(new Bridge(src, weight));

            l = Math.min(l, weight);
            r = Math.max(r, weight);
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
    }

    public static void main(String args[]) throws IOException {
        pre();
        binaraySearch();
        System.out.println(r);
    }

    static class Bridge {

        int dest, w;

        public Bridge() {
        }

        public Bridge(int dest, int w) {
            this.dest = dest;
            this.w = w;
        }
    }
}
