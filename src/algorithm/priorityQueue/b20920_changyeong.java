package algorithm.priorityQueue;

import java.io.*;
import java.util.*;

public class b20920_changyeong {
    static int N, M;
    static HashMap<String, Integer> notepad;
    static PriorityQueue<Word> pq;
    static StringBuilder sb;

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        notepad = new HashMap<>();
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            if (input.length() < M) {
                continue;
            }

            notepad.put(input, notepad.getOrDefault(input, 0) + 1);
        }

        for(String key : notepad.keySet()){
            pq.add(new Word(key, notepad.get(key)));
        }

        while(!pq.isEmpty()){
            sb.append(pq.poll().word).append('\n');
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        System.out.print(sb);
    }

    static class Word implements Comparable<Word> {
        String word;
        int cnt, len;

        public Word(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
            this.len = word.length();
        }

        @Override
        public int compareTo(Word o) {
            if (this.cnt == o.cnt) {
                if (this.len == o.len) {
                    return this.word.compareTo(o.word);
                }
                return o.len - this.len;
            }
            return o.cnt - this.cnt;
        }
    }
}
