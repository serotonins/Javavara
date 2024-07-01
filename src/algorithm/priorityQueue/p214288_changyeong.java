package algorithm.priorityQueue;

import java.io.*;
import java.util.*;

class p214288_changyeong {
    static int answer;
    static PriorityQueue<Integer> pq[];
    static List<List<Integer>> division;

    public static void backtracking(int remain, int idx, List<Integer> mentor) {
        if (remain == 0) {
            division.add(mentor);
            return;
        }

        for (int i = idx; i < mentor.size(); i++) {
            mentor.set(i, mentor.get(i) + 1);
            backtracking(remain - 1, i, new ArrayList<Integer>(mentor));
            mentor.set(i, mentor.get(i) - 1);
        }
    }

    public static void matchConference(int k, int reqs[][]) {
        for (List<Integer> next : division) {

            pq = new PriorityQueue[k + 1];
            int result = 0;

            for (int i = 1; i <= k; i++) {
                pq[i] = new PriorityQueue<>();

                // k번 유형마다 상담자 next.get(i)명씩 배치 및 초기화
                for (int j = 0; j < next.get(i); j++) {
                    pq[i].add(0);
                }
            }

            for (int req[] : reqs) {
                int src = req[0];
                int time = req[1];
                int idx = req[2];

                int cur = pq[idx].poll();

                if (cur > src) {
                    pq[idx].add(cur + time);
                    result += cur - src;
                }

                else if (cur < src) {
                    pq[idx].add(src + time);
                }

                else {
                    pq[idx].add(cur + time);
                }
            }
            answer = Math.min(answer, result);
        }
    }

    public int solution(int k, int n, int[][] reqs) {
        answer = Integer.MAX_VALUE;
        division = new ArrayList<>();
        List<Integer> mentor = new ArrayList<>();

        for (int i = 0; i <= k; i++) {
            mentor.add(1);
        }

        // n명의 멘토를 k개의 상담 유형에 분배하는 방법 저장
        backtracking(n - k, 1, mentor);

        // 각 멘토 분배 조합마다 최소 시간 찾기
        matchConference(k, reqs);

        return answer;
    }
}