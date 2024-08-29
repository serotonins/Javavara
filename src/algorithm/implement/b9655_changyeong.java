package algorithm.implement;

import java.io.*;
import java.util.*;

public class b9655_changyeong {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N % 2 == 0) {
            System.out.println("CY");
        }
        else {
            System.out.println("SK");
        }
    }
}
