package algorithm.slidingWindow;

import java.io.*;
import java.util.*;

public class b1522_changyeong {
    static String input;
    static int result, acnt, bcnt;

    public static void slidingWindow() {
        for (int i = 0; i < input.length(); i++) {
            bcnt = 0;
            for (int j = i; j < i + acnt; j++) {
                if (input.charAt((j + input.length()) % input.length()) == 'b') {
                    bcnt++;
                }
            }

            result = Math.min(result, bcnt);
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine();

        result = Integer.MAX_VALUE;
        acnt = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a') {
                acnt++;
            }
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        slidingWindow();
        System.out.println(result);
    }
}
