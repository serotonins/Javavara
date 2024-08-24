package algorithm.implement;

import java.io.*;
import java.util.*;

public class c_sam_pizza_school_changyeong {
    static int N, K, max, min, answer;
    static int dough[];
    static int dir[][] = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    static List<Integer> rolledDough[];

    public static void add() {
        List<Integer> idx = new ArrayList<>();

        int min = 3001;
        for (int i = 0; i < N; i++) {
            if (min > dough[i]) {
                idx.clear();
                idx.add(i);
                min = dough[i];
            }
            else if (min == dough[i]) {
                idx.add(i);
            }
        }

        for (int index : idx) {
            dough[index]++;
        }
    }

    public static void roll() {
        int roll_w, roll_h; // 말아주는 부분의 너비와 높이
        int src = 0;
        roll_w = 1;
        roll_h = 1;

        for (int i = 0; i < rolledDough.length; i++) {
            rolledDough[i].clear();
            rolledDough[i].add(dough[i]);
        }

        while (true) {
            int idx = src + roll_w;

            for (int i = 0; i < roll_h; i++) {
                for (int j = roll_w - 1; j >= 0; j--) {
                    // 말았을 때 옆으로 쌓이는 형태 저장
                    int num = rolledDough[src + j].get(i);
                    rolledDough[idx].add(num);
                }
                idx++;
            }

            for (int i = 0; i < roll_w; i++) {
                // 말아올리기 전 쌓인 것들 비우기
                rolledDough[src].clear();
                src++;
            }

            if (roll_w == roll_h) {
                roll_h++;
            }
            else {
                roll_w++;
            }

            if (src + roll_w + roll_h > N) {
                // 바닥에 있는 밀가루보다 위에 있는 밀가루의 너비가 더 넓은 경우
                break;
            }
        }
    }

    public static void push() {
        List<Integer> tmp[] = new List[N];

        for (int i = 0; i < N; i++) {
            tmp[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < rolledDough[i].size(); j++) {
                int sum = 0;
                for (int idx = 0; idx < 4; idx++) {
                    int dx = j + dir[idx][0];
                    int dy = i + dir[idx][1];

                    if (isValid(dx, dy)) {
                        sum -= (rolledDough[i].get(j) - rolledDough[dy].get(dx)) / 5;
                    }
                }
                tmp[i].add(sum + rolledDough[i].get(j));
            }
        }

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < tmp[i].size(); j++) {
                rolledDough[idx].clear();
                rolledDough[idx].add(tmp[i].get(j));
                idx++;
            }
        }
    }

    public static void fold() {
        int src, folding, tmp;

        src = 0;
        folding = 0;
        tmp = N / 2;
        while (folding < 2) {
            folding++;
            int idx = N - tmp;
            for (int i = src + tmp - 1; i >= src; i--) {
                for (int j = rolledDough[i].size() - 1; j >= 0; j--) {
                    rolledDough[idx].add(rolledDough[i].get(j));
                }
                idx++;
                rolledDough[i].clear();
            }

            src += tmp;
            tmp /= 2;
        }
    }

    public static boolean checkFinished() {
        max = 0;
        min = 3001;

        for (int i = 0; i < N; i++) {
            int num = rolledDough[i].get(0);
            max = Math.max(max, num);
            min = Math.min(min, num);
            dough[i] = num;
        }

        if (max - min <= K) {
            return true;
        }
        return false;
    }

    public static boolean isValid(int x, int y) {
        if (y < 0 || y >= N || x < 0 || x >= rolledDough[y].size()) {
            return false;
        }
        return true;
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        max = 0;
        min = 3001;
        answer = 0;

        dough = new int[N];
        rolledDough = new List[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            rolledDough[i] = new ArrayList<>();
            dough[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        pre();

        while (true) {
            answer++;
            add();
            roll();
            push();
            fold();
            push();
            if (checkFinished()) break;
        }
        System.out.println(answer);
    }

}
