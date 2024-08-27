package algorithm.dp;

import java.io.*;
import java.util.*;

public class b15988_changyeong {
    static int T, N;
    static long a, b, c, tmp;
    static StringBuilder sb;

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int x = 1; x <= T; x++) {
            N = Integer.parseInt(br.readLine());

            if (N <= 3) {
                sb.append(((int) Math.pow(2, N - 1))).append('\n');
            }
            else {
                a = 1;
                b = 2;
                c = 4;

                for (int i = 4; i <= N; i++) {
                    tmp = c;
                    c = (a + b + c) % 1_000_000_009;
                    a = b % 1_000_000_009;
                    b = tmp % 1_000_000_009;
                }
                c %= 1_000_000_009;
                sb.append(c).append('\n');
            }
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        System.out.println(sb);
    }
}
