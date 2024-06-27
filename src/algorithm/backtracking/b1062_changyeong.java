package algorithm.backtracking;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class b1062_changyeong {
    static int N, K, result;
    static List<String> words;
    static boolean isVisited[];

    public static void backtracking(int idx, int depth) {
        if (K < 5) {
            result = 0;
            return;
        }

        if (depth == K) {
            int cnt = 0;

            for (String next : words) {
                boolean check = true;

                for (int i = 0; i < next.length(); i++) {
                    if (!isVisited[next.charAt(i) - 'a']) {
                        check = false;
                        break;
                    }
                }

                if (check) {
                    cnt++;
                }
            }

            result = Math.max(result, cnt);

            return;
        }

        for (int i = idx; i < 26; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                backtracking(i, depth + 1);
                isVisited[i] = false;
            }
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        result = 0;

        isVisited = new boolean[26];

        isVisited['a' - 'a'] = true;
        isVisited['c' - 'a'] = true;
        isVisited['i' - 'a'] = true;
        isVisited['n' - 'a'] = true;
        isVisited['t' - 'a'] = true;

        words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            if (Pattern.matches("anta(.*)tica", input)) {
                words.add(input);
            }
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        backtracking(0, 5);
        System.out.println(result);
    }
}