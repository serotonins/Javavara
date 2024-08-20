package algorithm.slidingWindow;

import java.io.*;
import java.util.*;

public class p12904_changyeong {
    static int answer;
    public int solution(String s) {
        answer = 0;

        int len = s.length();

        for (int i = len; i >= 1; i--) {
            for (int j = 0; j <= len - i; j++) {
                int src = j;
                int dest = i + j - 1;

                boolean isPal = true;

                while (src < dest) {
                    if (s.charAt(src) != s.charAt(dest)) {
                        isPal = false;
                        break;
                    }
                    src++;
                    dest--;
                }

                if (isPal) {
                    return i;
                }
            }
        }

        return answer;
    }
}
