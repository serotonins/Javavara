package algorithm.backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class b1062_jisu {
    static int n, m, a;
    static int answer = 0;
    static Set<Character> pick = new HashSet<>();
    static Set<Character>[] needs;
    static List<Character> list;

    public static void back(int p) {

        if (a == pick.size()) {
            int cnt = 0;
            for (Set<Character> s : needs) {
                if (pick.containsAll(s)) cnt++;
            }
            answer = Math.max(answer, cnt);
            return;
        }

        if (p >= list.size()) return;

        back(p+1);
        pick.add(list.get(p));
        back(p+1);
        pick.remove(list.get(p));
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if (m < 5) {
            System.out.println(0);
            return;
        }

        Set<Character> antica = "antic".chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
        String[] words = new String[n];
        needs = new Set[n];
        TreeSet<Character> allAlphabet = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            needs[i] = words[i].chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            needs[i].removeAll(antica);
            allAlphabet.addAll(needs[i]);

        }

        a = m - 5;

        if (allAlphabet.size() < a) {
            System.out.println(n);
            return;
        }

        allAlphabet.removeAll(antica);
        list = new ArrayList<>(allAlphabet);

        back(0);

        System.out.println(answer);

    }
}
