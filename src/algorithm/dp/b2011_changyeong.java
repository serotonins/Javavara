package algorithm.dp;

import java.io.*;
import java.util.*;

public class b2011_changyeong {
    static String code;
    static long dp[];

    public static void dynamic() {
        dp[0] = 1;

        if (code.length() == 1) {
            if (Character.getNumericValue(code.charAt(0)) == 0) {
                dp[0] = 0;
            }
            return;
        }

        int first = Character.getNumericValue(code.charAt(0));
        int second = Character.getNumericValue(code.charAt(1));

        if (first == 1 || (first == 2 && second <= 6)) {
            if (second == 0) {
                dp[1] = 1;
            }
            else {
                dp[1] = 2;
            }
        } 
        else {
            if (first == 0 || second == 0) {
                dp[code.length()-1] = 0;
                return;
            }
            dp[1] = 1;
        }
        // dp[N] = 암호 N번째 위치까지 만들 수 있는 암호 갯수

        for (int i = 2; i < code.length(); i++) {
            int prev = Character.getNumericValue(code.charAt(i - 1));
            int current = Character.getNumericValue(code.charAt(i));

            if (prev == 1 || (prev == 2 && current <= 6)) {
                if (current == 0) {
                    dp[i] = dp[i - 2];
                }
                else {
                    dp[i] = (dp[i - 1] + dp[i - 2])%1000000;
                }
            }
            else {
                if (current == 0) {
                    dp[code.length() - 1] = 0;
                    return;
                }
                dp[i] = dp[i - 1];
            }
            dp[i] %= 1000000;
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        code = br.readLine();

        dp = new long[code.length()];
    }

    public static void main(String args[]) throws IOException {
        pre();
        dynamic();
        System.out.println(dp[code.length() - 1]);
    }
}