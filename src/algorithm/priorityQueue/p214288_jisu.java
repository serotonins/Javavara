package algorithm.priorityQueue;

import java.util.*;

class p214288_jisu {
    static int answer, K;
    static int[][] waitTimeArr;
    static PriorityQueue<Gamso> gamsoQueue = new PriorityQueue<>();

    class Gamso implements Comparable<Gamso> {
        int type, many, reduce;

        public Gamso(int type, int many, int reduce) {
            this.type = type;
            this.many = many;
            this.reduce = reduce;
        }

        public int compareTo(Gamso o) {
            if (this.reduce == o.reduce) return this.many - o.many;
            return o.reduce - this.reduce;
        }

        public String toString() {
            return "Gamso " + this.type + " " + this.many + " " + this.reduce;
        }
    }

    public int calculateOne(int[][] reqs, int many, int type) {
        int wait = 0;
        PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (int i = 0; i < many; i++) que.add(new int[] {i, 0});
        for (int[] i : reqs) {
            if (i[2] != type) continue;
            int[] now = que.poll();
            if (now[1] > i[0]) {
                wait += now[1] - i[0];
                que.add(new int[] {now[0], now[1]+i[1]});
            } else que.add(new int[] {now[0], i[0]+i[1]});
        }
        return wait;
    }


    public int solution(int k, int n, int[][] reqs) {
        answer = 0;
        K = k;

        waitTimeArr = new int[k+1][2+(n-k)];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= 1+(n-k); j++) {
                waitTimeArr[i][j] = calculateOne(reqs, j, i);
                if (j != 1) gamsoQueue.add(new Gamso(i, j, waitTimeArr[i][j-1] - waitTimeArr[i][j]));
                System.out.println(i + " " + j + " " + waitTimeArr[i][j]);
                if (waitTimeArr[i][j] == 0) break;
            }
        }

        int cnt = n-k;
        int[] count = new int[k+1];
        for (int i = 1; i < k+1; i++) {
            count[i]++;
        }
        while (cnt > 0 && !gamsoQueue.isEmpty()) {
            Gamso g = gamsoQueue.poll();
            // System.out.println(g.toString());
            if (g.many - count[g.type] > cnt) continue;
            cnt -= g.many - count[g.type];
            count[g.type] = Math.max(count[g.type], g.many);
        }
        for (int i = 1; i < k+1; i++) {
            answer += waitTimeArr[i][count[i]];
        }

        return answer;
    }
}