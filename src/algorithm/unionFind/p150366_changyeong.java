package algorithm.unionFind;

import java.io.*;
import java.util.*;

public class p150366_changyeong {
    static int group[];
    static String values[], answer[];
    static List<String> ans;
    static StringTokenizer st;

    public static void union(int n1, int n2) {
        n1 = find(n1);
        n2 = find(n2);

        if (n1 == n2) {
            return;
        }

        values[n2] = null;
        group[n2] = n1;
    }

    public static int find(int idx) {
        if (idx == group[idx]) {
            return idx;
        }

        return group[idx] = find(group[idx]);
    }

    public static void Update() {
        String in1 = st.nextToken();
        String in2 = st.nextToken();

        if (st.hasMoreTokens()) {
            String input = st.nextToken();
            int row = Integer.parseInt(in1) - 1;
            int col = Integer.parseInt(in2) - 1;
            values[find(row * 50 + col)] = input;
        } else {
            for (int i = 0; i < 2500; i++) {
                int idx = find(i);
                if (values[idx] != null && values[idx].equals(in1)) {
                    values[idx] = in2;
                }
            }
        }
    }

    public static void Merge() {
        int r1 = Integer.parseInt(st.nextToken()) - 1;
        int c1 = Integer.parseInt(st.nextToken()) - 1;
        int r2 = Integer.parseInt(st.nextToken()) - 1;
        int c2 = Integer.parseInt(st.nextToken()) - 1;

        int idx1 = r1 * 50 + c1;
        int idx2 = r2 * 50 + c2;

        if (values[find(idx1)] == null && values[find(idx2)] != null) {
            int tmp = idx1;
            idx1 = idx2;
            idx2 = tmp;
        }

        union(idx1, idx2);
    }

    public static void Unmerge() {
        int row = Integer.parseInt(st.nextToken()) - 1;
        int col = Integer.parseInt(st.nextToken()) - 1;
        int idx = row * 50 + col;

        int num = find(idx);
        String val = values[num];

        for (int i = 0; i < 2500; i++) {
            // 그룹 업데이트
            find(i);
        }

        for (int i = 0; i < 2500; i++) {
            if (find(i) == num) {
                group[i] = i;

                if (i == idx) {
                    values[i] = val;
                }
                else {
                    values[i] = null;
                }
            }
        }
    }

    public static void Print() {
        int row = Integer.parseInt(st.nextToken()) - 1;
        int col = Integer.parseInt(st.nextToken()) - 1;
        int idx = row * 50 + col;

        ans.add(values[find(idx)] != null ? values[find(idx)] : "EMPTY");
    }

    public String[] solution(String[] commands) {

        group = new int[2500];
        values = new String[2500];
        ans = new ArrayList<>();

        for (int i = 0; i < 2500; i++) {
            group[i] = i;
        }

        for (String command : commands) {
            st = new StringTokenizer(command);
            String com = st.nextToken();

            if (com.equals("UPDATE")) {
                Update();
            }
            else if (com.equals("MERGE")) {
                Merge();
            }
            else if (com.equals("UNMERGE")) {
                Unmerge();
            }
            else {
                Print();
            }
        }

        answer = new String[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }

}
