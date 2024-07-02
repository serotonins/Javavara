package algorithm;

import java.io.*;
import java.util.*;

public class p214289_changyeong {
    static int answer, INF;
    static int dp[][];

    public static void dynamic(int temp, int t1, int t2, int a, int b, int onboard[]) {
        int flag = temp <= t2 ? 1 : -1;

        for (int i = 1; i < onboard.length; i++) {
            for (int j = 0; j < 51; j++) {
                if ((onboard[i] == 1 && t1 <= j && t2 >= j) || onboard[i] == 0) {
                    if (j == temp) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                    }
                    if (isSuitable(j + flag)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + flag]);
                    }
                    if (isSuitable(j - flag)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - flag] + a);
                    }
                    if (t1 <= j && t2 >= j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + b);
                    }
                }
            }
        }

        answer = dp[onboard.length - 1][0];
        for (int j = 1; j < 51; j++) {
            answer = Math.min(answer, dp[onboard.length - 1][j]);
        }
    }

    public static boolean isSuitable(int a) {
        if (a >= 0 && a <= 50) {
            return true;
        }
        return false;
    }

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        answer = 0;
        INF = 100000;

        t1 += 10;
        t2 += 10;
        temperature += 10;

        // dp[i][j] : i분에 j온도를 맞추는 전력
        dp = new int[onboard.length][51];

        for (int i = 0; i < onboard.length; i++) {
            for (int j = 0; j < 51; j++) {
                dp[i][j] = INF;
            }
        }

        dp[0][temperature] = 0;

        dynamic(temperature, t1, t2, a, b, onboard);

        return answer;
    }
}
