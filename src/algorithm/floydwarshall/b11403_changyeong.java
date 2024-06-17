package algorithm.floydwarshall;

import java.io.*;
import java.util.*;

public class b11403_changyeong {
    static int N;
    static int INF = 10000;
    static int connection[][];
    static StringBuilder sb;

    public static void FloydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    connection[i][j] = Math.min(connection[i][j], connection[i][k] + connection[k][j]);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(connection[i][j] == INF ? 0 : 1).append(" ");
            }
            sb.append("\n");
        }
    }
    
    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        connection = new int[N + 1][N + 1];
        sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (num == 0) {
                    connection[i][j] = INF;
                }
                else {
                    connection[i][j] = num;
                }
            }
        }
    }
    
    public static void main(String args[]) throws IOException {
        pre();
        FloydWarshall();
        System.out.println(sb);
    }
}
