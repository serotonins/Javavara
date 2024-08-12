package algorithm.implement;

import java.io.*;
import java.util.*;

public class b30458_changyeong {
    static int N;
    static int alpha[];
    static String input;
    static StringBuilder sb;

    public static void palindrome() {
        for (int i = 0; i < input.length(); i++) {
            if (input.length() % 2 != 0 && i == (input.length() / 2)) {
                // 길이가 홀수인 경우 가운데 제외
                continue;
            }

            char abc = input.charAt(i);
            alpha[abc - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (alpha[i] % 2 != 0) {
                sb.append("No");
                return;
            }
        }
        sb.append("Yes");
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        input = br.readLine();

        alpha = new int[26];
    }

    public static void main(String[] args) throws IOException {
        pre();
        palindrome();
        System.out.println(sb);
    }
}
