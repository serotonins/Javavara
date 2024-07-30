package algorithm.lcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b9251_jisu {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String one = br.readLine();
        String two = br.readLine();
        int answer = 0;

        int[][] lcs = new int[one.length()][two.length()];
        for (int i = 0; i < one.length(); i++) {
            for (int j = 0; j < two.length(); j++) {
                if (one.charAt(i) == two.charAt(j)) {
                    lcs[i][j]++;
                    if (i > 0 && j > 0) lcs[i][j] += lcs[i-1][j-1];
                } else {
                    int a = 0, b = 0;
                    if (i > 0) a = lcs[i-1][j];
                    if (j > 0) b = lcs[i][j-1];
                    int c = Math.max(a, b);
                    lcs[i][j] = c;
                }

                answer = Math.max(answer, lcs[i][j]);
            }
        }

        System.out.println(answer);
    }
}
