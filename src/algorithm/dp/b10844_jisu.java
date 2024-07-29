package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b10844_jisu {

    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n+1][10];
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j-1 >= 0) {
                    dp[i+1][j-1] += dp[i][j];
                    dp[i+1][j-1] %= 1000000000;
                }
                if (j+1 <= 9) {
                    dp[i+1][j+1] += dp[i][j];
                    dp[i+1][j+1] %= 1000000000;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[n][i];
            answer %= 1000000000;
        }

        System.out.println(answer);
    }
}
