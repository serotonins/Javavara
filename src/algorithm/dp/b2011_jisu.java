package algorithm.dp;

import java.util.*;
import java.io.*;

public class b2011_jisu {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        TreeSet<String> set = new TreeSet<>();
        for (int i = 11; i < 27; i++) {
            if (i != 20) set.add(String.valueOf(i));
        }

        int[] fibonacci = new int[5001];
        fibonacci[0] = fibonacci[1] = 1;
        for (int i = 2; i <= 5000; i++) {
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
            fibonacci[i] %= 1000000;
        }

        long answer = 1;
        int continuity = 1;

        for (int i = str.length() - 2; i >= 0; i--) {
            String num = str.substring(i, i+2);
            if (set.contains(num)) continuity++;
            else if (str.charAt(i+1) == '0') {
                if (str.charAt(i) > '2' | str.charAt(i) == '0') {
                    answer = 0;
                    break;
                } else continuity--;
            }
            else {
                answer *= fibonacci[continuity];
                answer %= 1000000;
                continuity = 1;
            }
        }

        answer *= fibonacci[continuity];
        if (str.equals("0") | str.charAt(0) == '0') answer = 0;

        System.out.println(answer % 1000000);

    }
}
