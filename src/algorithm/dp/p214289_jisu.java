package algorithm.dp;

import java.util.*;

class p214289_jisu {
    static int answer;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        answer = Math.max(a, b) * onboard.length;

        temperature += 10;
        t1 += 10;
        t2 += 10;


        int[][] dp = new int[onboard.length][51];
        for (int i = 0; i < onboard.length; i++) {
            for (int j = 0; j < 51; j++) {
                dp[i][j] = -1;
            }
        }

        dp[0][temperature] = 0;

        for (int i = 1; i < onboard.length; i++) {
            int start = onboard[i] == 1 ? t1 : 0;
            int end = onboard[i] == 1 ? t2 : 50;

            for (int j = start; j <= end; j++) {
                int one = Integer.MAX_VALUE;
                int two = Integer.MAX_VALUE;
                int thr = Integer.MAX_VALUE;
                if (dp[i-1][j] != -1) two = dp[i-1][j] + (j == temperature ? 0 : b);
                if (j != 0 && dp[i-1][j-1] != -1) one = dp[i-1][j-1] + (j-1 < temperature ? 0 : a);
                if (j != 50 && dp[i-1][j+1] != -1) thr = dp[i-1][j+1] + (j+1 > temperature ? 0 : a);
                dp[i][j] = Math.min(one, Math.min(two, thr));
                if (dp[i][j] == Integer.MAX_VALUE) dp[i][j] = -1;
            }
        }

        for (int i = 0; i <= 50; i++) {
            if (dp[onboard.length-1][i] == -1) continue;
            answer = Math.min(answer, dp[onboard.length-1][i]);
        }

        return answer;
    }
}