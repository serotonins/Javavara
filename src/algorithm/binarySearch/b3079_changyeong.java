package algorithm.binarySearch;

import java.io.*;
import java.util.*;

public class b3079_changyeong {
    static int N;
    static long M, max, l, r, m, result, cnt;
    static int pass[];

    public static void binarySearch() {
        l = 0;
        r = max * M;

        while (l <= r) {
            m = (l + r) / 2;

            cnt = 0;
            for (int idx : pass) {
                if (cnt >= M || idx > m) {
                    break;
                }
                cnt += (m / idx);
            }

            if (cnt >= M) {
                result = Math.min(m, result);
                r = m - 1;
            } 
            else {
                l = m + 1;
            }
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        pass = new int[N];
        max = 0;

        for (int i = 0; i < N; i++) {
            pass[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, pass[i]);
        }

        result = max * M;

        Arrays.sort(pass);
    }

    public static void main(String args[]) throws IOException {
        pre();
        binarySearch();
        System.out.println(result);
    }
}
