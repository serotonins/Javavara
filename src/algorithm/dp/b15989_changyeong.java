package algorithm.dp;

import java.io.*;
import java.util.*;

public class b15989_changyeong {
    static int x, T, N;
    static int dp[][];
    static StringBuilder sb;

    public static void dynamic() {
        dp[1][1] = 1;
        dp[2][1] = 0;
        dp[3][1] = 0;
        dp[1][2] = 1;
        dp[2][2] = 1;
        dp[3][2] = 0;
        dp[1][3] = 1;
        dp[2][3] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= 10000; i++) {
            dp[1][i] = dp[1][i - 1];
            dp[2][i] = dp[1][i - 2] + dp[2][i - 2];
            dp[3][i] = dp[1][i - 3] + dp[2][i - 3] + dp[3][i - 3];
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        dp = new int[4][100001];

        // dp[i][j] : 숫자 j를 숫자 i를 합연산하여 사용하는 경우의 수

        dynamic();

        for (int x = 1; x <= T; x++) {
            N = Integer.parseInt(br.readLine());
            sb.append(dp[1][N] + dp[2][N] + dp[3][N]).append('\n');
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        System.out.print(sb);
    }
}
