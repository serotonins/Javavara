package algorithm.greedy;

import java.io.*;
import java.util.*;

public class p172927_changyeong {

  static int total, answer, dia, iron, stone;
  static int energy[][] = { { 1, 1, 1 }, { 5, 1, 1 }, { 25, 5, 1 } };
  static PriorityQueue<Mineral> pq;

  public static void greedy(int picks[]) {
    while (!pq.isEmpty()) {
      Mineral m = pq.poll();

      int md = m.dia;
      int mi = m.iron;
      int ms = m.stone;

      if (picks[0] > 0) {
        answer += md;
        picks[0]--;
        continue;
      }
      if (picks[1] > 0) {
        answer += mi;
        picks[1]--;
        continue;
      }
      if (picks[2] > 0) {
        answer += ms;
        picks[2]--;
        continue;
      }
    }
  }

  public int solution(int[] picks, String[] minerals) {
    answer = 0;
    total = Arrays.stream(picks).sum();

    pq = new PriorityQueue<>();

    for (int i = 0; i < minerals.length && total > 0; i += 5) {
      dia = 0;
      iron = 0;
      stone = 0;

      for (int j = i; j < i + 5 && j != minerals.length; j++) {
        int tmp = minerals[j].equals("iron")
          ? 1
          : (minerals[j].equals("stone") ? 2 : 0);
        // System.out.println(energy[0][tmp]+" "+energy[1][tmp]+" "+energy[2][tmp]);
        dia += energy[0][tmp];
        iron += energy[1][tmp];
        stone += energy[2][tmp];
      }

      pq.add(new Mineral(dia, iron, stone));
      total--;
    }

    greedy(picks);

    return answer;
  }

  static class Mineral implements Comparable<Mineral> {

    int dia, iron, stone;

    public Mineral(int dia, int iron, int stone) {
      this.dia = dia;
      this.iron = iron;
      this.stone = stone;
    }

    @Override
    public int compareTo(Mineral o) {
      return o.stone - this.stone;
    }
  }
}
