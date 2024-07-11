package algorithm.backtracking;

import java.io.*;
import java.util.*;

public class b15684_changyeong {
    static int N, M, H, result;
    static boolean flag;
    static boolean existLine[][];

    public static boolean isPossible() {
        for (int i = 1; i <= N; i++) {
            int row = 1;
            int col = i;
            for (int j = 1; j <= H; j++) {
                if (existLine[row][col - 1]) {
                    row++;
                    col--;
                    continue;
                }
                if (existLine[row][col]) {
                    row++;
                    col++;
                    continue;
                }
                row++;
            }

            if (col != i) {
                return false;
            }
        }

        return true;
    }

    public static void backTracking(int r, int c, int depth, int limit) {
        if (flag) {
            return;
        }

        if (depth == limit) {
            if (isPossible()) {
                result = depth;
                flag = true;
            }
            return;
        }

        for (int i = r; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (!existLine[i][j] && !existLine[i][j - 1] && !existLine[i][j + 1]) {
                    existLine[i][j] = true;
                    backTracking(i, j, depth + 1, limit);
                    existLine[i][j] = false;
                }
            }
        }
    }

    public static void doProcess() {
        for (int i = 0; i <= 3; i++) {
            backTracking(1, 1, 0, i);
            if (flag) {
                break;
            }
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        result = -1;
        flag = false;

        existLine = new boolean[H + 2][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            existLine[A][B] = true;
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        doProcess();
        System.out.println(result);
    }
}