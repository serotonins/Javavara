package algorithm.eratosthenes;

import java.io.*;
import java.util.*;

public class b4948_changyeong {
    static int N, num;
    static boolean isPrime[];
    static StringBuilder sb;

    public static int findPrime() {
        int cnt = 0;
        for(int i = N+1; i<= 2 * N;i++){
            if(isPrime[i]){
                cnt++;
            }
        }

        return cnt;
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        num = (123456 * 2) + 1;

        isPrime = new boolean[num];

        isPrime[1] = false;

        for (int i = 2; i < num; i++) {
            int cnt = 0;
            for(int j = 1; j <= (int) Math.sqrt(i); j++){
                if(i%j == 0){
                    cnt++;
                }
                if(cnt >= 2){
                    break;
                }
            }
            if(cnt == 1){
                isPrime[i] = true;
            }
            else{
                isPrime[i] = false;
            }
        }

        while (N != 0) {
            sb.append(findPrime()).append('\n');
            N = Integer.parseInt(br.readLine());
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        System.out.println(sb);
    }
}
