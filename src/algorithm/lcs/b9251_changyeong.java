package algorithm.lcs;

import java.io.*;
import java.util.*;

public class b9251_changyeong {
    static int len1, len2;
    static String input1, input2;
    static int dp[][];

    public static void LCS() {
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input1 = br.readLine();
        input2 = br.readLine();

        len1 = input1.length();
        len2 = input2.length();

        //dp[i][j] : input1의 i번째 문자와 input2의 j번째 문자를 비교했을때 최대 LCS 값 크기
        dp = new int[len1 + 1][len2 + 1];
    }
    
    public static void main(String args[]) throws IOException {
        pre();
        LCS();
        System.out.println(dp[len1][len2]);
    }
}
