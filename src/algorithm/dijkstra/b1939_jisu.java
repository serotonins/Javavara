package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class b1939_jisu {

    static int n, m, start, end;
    static TreeMap<Integer, Integer>[] map;

    public static class Island implements Comparable<Island> {
        int arr, wei;
        public Island(int arr, int wei) {
            this.arr = arr;
            this.wei = wei;
        }

        public int compareTo(Island o) {
            if (o.wei == this.wei) return this.arr - o.arr;
            return o.wei - this.wei;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new TreeMap[n+1];
        for (int i = 0; i < n + 1; i++) {
            map[i] = new TreeMap<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int dep = Integer.parseInt(st.nextToken());
            int arr = Integer.parseInt(st.nextToken());
            int wei = Integer.parseInt(st.nextToken());

            map[dep].put(arr, Math.max(map[dep].getOrDefault(arr, 0), wei));
            map[arr].put(dep, Math.max(map[arr].getOrDefault(dep, 0), wei));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        boolean[] visit = new boolean[n+1];

        PriorityQueue<Island> que = new PriorityQueue<>();
        que.add(new Island(start, 1000000000));

        while (!que.isEmpty()) {
            Island now = que.poll();
            if (visit[now.arr]) continue;
            visit[now.arr] = true;
            for (int i : map[now.arr].keySet()) {
                int thisRouteCap = Math.min(map[now.arr].get(i), now.wei);
                int cap = Math.max(thisRouteCap, map[start].getOrDefault(i, 0));
                if (cap < map[start].getOrDefault(end, 0)) continue;
                if (now.arr == start || cap > map[start].getOrDefault(i, 0)) que.add(new Island(i, cap));
                map[start].put(i, cap);
            }
        }

        System.out.println(map[start].get(end));
    }
}
