package algorithm.prefixSum;

import java.io.*;
import java.util.*;

public class b1806_changyeong {
    static int N, S, len;
    static int num[], prefixSum[];

    public static void twoPointer() {
        int i, j;

        i = 0;
        j = 1;

        while (i <= j && j <= N) {
            if (prefixSum[j] - prefixSum[i] < S) {
                j++;
            }
            else {
                len = Math.min(len, j - i);
                i++;
            }
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        len = Integer.MAX_VALUE;

        num = new int[N + 1];
        prefixSum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + num[i];
        }
    }
    
    public static void main(String args[]) throws IOException {
        pre();
        twoPointer();
        System.out.println(len == Integer.MAX_VALUE ? 0 : len);
    }
}
