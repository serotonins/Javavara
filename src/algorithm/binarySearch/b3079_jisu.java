package algorithm.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class b3079_jisu {
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] simsa = new int[n];

        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(br.readLine());
            simsa[i] = temp;
        }

        Arrays.sort(simsa);

        long s = 1;
        long e = (long) simsa[0] * m;

        long answer = 1;

        while (s <= e) {
            long p = (s + e) / 2;
            long cnt = 0;
            for (int i : simsa) {
                cnt += (p / i);
                if (cnt >= m) break;
            }
            if (cnt >= m) e = p-1;
            else s = p+1;
        }

        System.out.println(s);
    }
}
