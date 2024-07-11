package algorithm.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b15684_jisu {

    static int n, m, h, answer;
    static int[][] lines;
    static boolean[][] addedLine;

    public static boolean allI() {
        int[] check = new int[n+1];
        for (int i = 0; i <= n; i++) {
            check[i] = i;
        }
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                if (addedLine[i][j]) {
                    int temp = check[j];
                    check[j] = check[j+1];
                    check[j+1] = temp;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (check[i] != i) return false;
        }

        return true;
    }

    public static void back(int p, int q, int cnt) {
        if (cnt > 0 && cnt <= 3 && allI()) {
            answer = Math.min(answer, cnt);
            return;
        }
        if (p > h || cnt >= 3 || cnt >= answer) return;

        for (int i = q; i < n; i++) {
            if (addedLine[p][i-1] || addedLine[p][i] || addedLine[p][i+1]) continue;
            addedLine[p][i] = true;
            back(p, i+2, cnt+1);
            addedLine[p][i] = false;
        }
        back(p+1, 1, cnt);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        lines = new int[m][2];
        addedLine = new boolean[h+1][n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
            addedLine[lines[i][0]][lines[i][1]] = true;
        }

        answer = 4;

        if (allI()) answer = 0;


        back(1, 1, 0);
        System.out.println(answer > 3 ? -1 : answer);
    }
}
