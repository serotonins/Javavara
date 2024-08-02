package algorithm.dp;

import java.io.*;
import java.util.*;

public class b3687_changyeong {
    static int x, T, num;
    static long minDP[];
    static String maxDP[];
    static StringBuilder sb;

    public static void dynamic() {
        // 1 : 2개
        // 2 : 5개
        // 3 : 5개
        // 4 : 4개
        // 5 : 5개
        // 6 : 6개
        // 7 : 3개
        // 8 : 7개
        // 9 : 6개
        // 0 : 6개
        // if num%2 == 0 then 1로만 채워진 50자리 수가 최대값
        // if num%2 == 1 then 맨 앞자리가 7이고 뒤로 1로만 채워진 수가 최대값
        // num = 8 일때부터 2자리 수 이상, num >= 9 인경우 dp[2] ~ dp[7] 값에서 문자열 조합하여 최소값 탐색

        // minDP[i] : 성냥개비 i로 만들수 있는 수 중 최소값
        Arrays.fill(minDP, Long.MAX_VALUE);
        minDP[2] = 1;
        minDP[3] = 7;
        minDP[4] = 4;
        minDP[5] = 2;
        minDP[6] = 6; // 숫자는 0으로 시작할 수 없으므로 0이 아닌 6 저장
        minDP[7] = 8;
        minDP[8] = 10;
        minDP[9] = 18;
        minDP[10] = 22;

        // 성냥 i개로 만들수 있는 최대값 maxDP에 저장
        maxDP[2] = "1";
        for (int i = 3; i <= 100; i++) {
            String val = "";
            if (i % 2 == 0) {
                for (int j = 0; j < i / 2; j++) {
                    val += "1";
                }
            }
            else {
                int div = (i - 3) / 2;
                val += "7";
                for (int j = 0; j < div; j++) {
                    val += "1";
                }
            }
            maxDP[i] = val;
        }

        // 성냥 i개로 만들 수 있는 최소값 minDP에 저장
        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String val = "" + minDP[i - j] + (j == 6 ? 0 : minDP[j]);
                minDP[i] = Math.min(minDP[i], Long.parseLong(val));
            }
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        minDP = new long[101];
        maxDP = new String[101];

        dynamic();
        sb = new StringBuilder();

        for (int x = 1; x <= T; x++) {
            num = Integer.parseInt(br.readLine());
            sb.append(minDP[num] + " " + maxDP[num]).append('\n');
        }
    }

    public static void main(String[] args) throws IOException {
        pre();
        System.out.print(sb);
    }
}
