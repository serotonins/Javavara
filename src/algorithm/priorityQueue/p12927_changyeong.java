package algorithm.priorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

public class p12927_changyeong {
    static int N;
    static long answer;
    static PriorityQueue<Integer> pq;

    public static void work(int remain[]) {
        for (int num : remain) {
            pq.add(num);
        }

        for (int time = N; time > 0 && pq.peek() > 0; time--) {
            int num = pq.poll();
            pq.add(num - 1);
        }

        while (!pq.isEmpty()) {
            int num = pq.poll();
            answer += (num * num);
        }
    }

    public long solution(int n, int[] works) {
        answer = 0;
        N = n;

        pq = new PriorityQueue<>(Collections.reverseOrder());

        work(works);

        return answer;
    }
}
