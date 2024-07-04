package algorithm.slidingWindow;

import java.io.*;
import java.util.*;

public class b15961_changyeong {
    static int N, D, K, C, result;
    static int belt[], sushi[];

    public static void slidingWindow() {
        int cnt = 1;
        sushi[C]++;

        for (int i = 0; i < K; i++) {
            if (sushi[belt[i]] == 0) {
                cnt++;
            }
            sushi[belt[i]]++;
        }

        result = cnt;

        for (int i = K; i < N + K; i++) {
            
            sushi[belt[i - K]]--;
            if (sushi[belt[i - K]] == 0) {
                cnt--;
            }

            if (sushi[belt[i % N]] == 0) {
                cnt++;
            }
            sushi[belt[i % N]]++;

            result = Math.max(cnt, result);
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        belt = new int[N];
        sushi = new int[D + 1];

        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        slidingWindow();
        System.out.println(result);
    }
}