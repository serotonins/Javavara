package algorithm.queue;

import java.io.*;
import java.util.*;

class Solution {
    static int answer;
    static long sum1, sum2;
    static Deque<Integer> que1, que2;

    public static void pushAndPop() {
        while (sum1 != sum2) {
            if (sum1 > sum2) {
                int num = que1.poll();
                sum1 -= num;
                sum2 += num;
                que2.add(num);
                answer++;
            } 
            else {
                int num = que2.poll();
                sum2 -= num;
                sum1 += num;
                que1.add(num);
                answer++;
            }

            if (answer >= (que1.size() + que2.size()) * 2) {
                answer = -1;
                break;
            }
        }
    }

    public int solution(int[] queue1, int[] queue2) {
        answer = 0;
        sum1 = 0;
        sum2 = 0;

        que1 = new ArrayDeque<>();
        que2 = new ArrayDeque<>();

        for (int num : queue1) {
            sum1 += num;
            que1.add(num);
        }

        for (int num : queue2) {
            sum2 += num;
            que2.add(num);
        }

        pushAndPop();

        return answer;
    }
}