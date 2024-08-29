package algorithm.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b9655_jisu {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        while (n > 0) {
            if (n > 4) {
                n -= 3;
            } else if (n == 4) {
                System.out.println("CY");
                break;
            } else if (n == 3) {
                System.out.println("SK");
                break;
            } else if (n == 2) {
                System.out.println("CY");
                break;
            } else {
                System.out.println("SK");
                break;
            }

            if (n > 4) {
                n -= 3;
            } else if (n == 4) {
                System.out.println("SK");
                break;
            } else if (n == 3) {
                System.out.println("CY");
                break;
            } else if (n == 2) {
                System.out.println("SK");
                break;
            } else {
                System.out.println("CY");
                break;
            }
        }
    }
}

