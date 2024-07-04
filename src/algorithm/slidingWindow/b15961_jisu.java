package algorithm.slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class b15961_jisu {

    static int n, d, k, c, answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[n];
        answer = 2;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(c, 1);

        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
            map.put(sushi[i], map.getOrDefault(sushi[i], 0) + 1);
            if (i >= k) {
                if (map.get(sushi[i-k]) == 1) map.remove(sushi[i-k]);
                else map.put(sushi[i-k], map.get(sushi[i-k])-1);
            }
            answer = Math.max(map.size(), answer);
        }

        for (int i = n-k; i < n; i++) {
            map.put(sushi[(i+k)%n], map.getOrDefault(sushi[(i+k)%n], 0) + 1);
            if (map.get(sushi[i]) == 1) map.remove(sushi[i]);
            else map.put(sushi[i], map.get(sushi[i])-1);
            answer = Math.max(map.size(), answer);
        }

        System.out.println(answer);
    }
}
