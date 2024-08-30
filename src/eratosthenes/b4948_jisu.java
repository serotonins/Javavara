package eratosthenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class b4948_jisu {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] sosu = new boolean[123456*2+1];
        Arrays.fill(sosu, true);
        sosu[0] = false;
        sosu[1] = false;
        for (int i = 2; i <= (int) Math.sqrt(123456*2+1); i++) {
            if (!sosu[i]) {
                continue;
            }
            for (int j = i * i; j < 123456*2+1; j += i) {
                sosu[j] = false;
            }
        }

        int[] prefixSum = new int[123456*2+1];
        for (int i = 2; i < 123456*2+1; i++) {
            prefixSum[i] = prefixSum[i-1] + (sosu[i] ? 1 : 0);
        }

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            System.out.println(prefixSum[2*n] - prefixSum[n]);
        }
    }
}

