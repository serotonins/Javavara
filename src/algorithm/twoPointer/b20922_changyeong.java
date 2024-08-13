package algorithm.twoPointer;

import java.io.*;
import java.util.*;

public class b20922_changyeong {
    static int N, K, result;
    static List<Integer> list;
    static int cnt[];

    public static void twoPointer() {
        int src, dest;

        src = 0;
        dest = 0;

        while (src < N && dest < N) {
            if (cnt[list.get(dest)] < K) {
                cnt[list.get(dest)]++;
                dest++;
                result = Math.max(result, dest - src);
            }
            else {
                cnt[list.get(src)]--;
                src++;
            }
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        result = Integer.MIN_VALUE;

        list = new ArrayList<>();
        cnt = new int[100001];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        twoPointer();
        System.out.println(result);
    }
}
