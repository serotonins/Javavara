package algorithm.implement;

import java.io.*;
import java.util.*;

public class p67257_changyeong {

    static long answer;
    static String oper[][] = {
            { "+", "-", "*" },
            { "+", "*", "-" },
            { "*", "+", "-" },
            { "*", "-", "+" },
            { "-", "*", "+" },
            { "-", "+", "*" },
    };
    static List<String> list;
    static List<String> num;

    public String calc(String num1, String num2, String op) {
        long n1 = Long.parseLong(num1);
        long n2 = Long.parseLong(num2);

        if (op.equals("+")) {
            return n1 + n2 + "";
        } else if (op.equals("-")) {
            return n1 - n2 + "";
        } else {
            return n1 * n2 + "";
        }
    }

    public long solution(String expression) {
        answer = 0;

        list = new ArrayList<>();

        int start = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '-' ||
                    expression.charAt(i) == '+' ||
                    expression.charAt(i) == '*') {
                list.add(expression.substring(start, i));
                list.add(expression.charAt(i) + "");
                start = i + 1;
            }
        }

        list.add(expression.substring(start));

        for (int i = 0; i < oper.length; i++) {
            num = new ArrayList<>(list);
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < num.size(); k++) {
                    if (oper[i][j].equals(num.get(k))) {
                        num.set(k - 1, calc(num.get(k - 1), num.get(k + 1), num.get(k)));
                        num.remove(k);
                        num.remove(k);
                        k--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(num.get(0))));
        }

        return answer;
    }
}