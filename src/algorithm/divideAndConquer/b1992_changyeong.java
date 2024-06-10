import java.io.*;
import java.util.*;

public class b1992_changyeong {
    static int N;
    static String data[][];
    static StringBuilder sb;

    public static void divideAndConquer(int a, int b, int size) {
        if (checkSame(a, b, size)) {
            sb.append(data[a][b]);
            return;
        }

        sb.append("(");
        divideAndConquer(a, b, size / 2);
        divideAndConquer(a, b + (size / 2), size / 2);
        divideAndConquer(a + (size / 2), b, size / 2);
        divideAndConquer(a + (size / 2), b + (size / 2), size / 2);
        sb.append(")");
    }

    public static boolean checkSame(int a, int b, int size) {
        for (int i = a; i < a + size; i++) {
            for (int j = b; j < b + size; j++) {
                if (!data[i][j].equals(data[a][b])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        data = new String[N ][N];
        sb = new StringBuilder();

        for (int i = 0; i <N; i++) {
            String input = br.readLine();
            data[i] = input.split("");
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        divideAndConquer(0, 0, N);
        System.out.println(sb);
    }
}