package algorithm.dp;

import java.io.*;
import java.util.*;

public class b10844_changyeong {
    static int N;
    static long result;
    static long dp[][];

    public static void dynamic() {
        if (N == 1) {
            result = 9;
            return;
        }

        dp[1][0] = 0;

        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1];
            dp[i][9] = dp[i - 1][8];
            for (int j = 1; j < 9; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1_000_000_000;
            }
        }

        for (int j = 0; j < 10; j++) {
            result += dp[N][j] % 1_000_000_000;
            result %= 1_000_000_000;
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        //dp[i][j] : 길이 i이고 마지막 값이 j인 경우의 수
        dp = new long[N + 1][10];
    }
    
    public static void main(String args[]) throws IOException {
        pre();
        dynamic();
        System.out.println(result);
    }
}
