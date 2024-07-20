package algorithm.implement;

import java.util.*;

class p258707_jisu {

    static int n, answer, zero;

    static Set<Integer> origin, newCards;

    public boolean choice(int c) {
        int f = n+1-c;
        if (newCards.contains(f)) {
            newCards.remove(f);
            return true;
        }
        return false;
    }

    public int solution(int coin, int[] cards) {

        n = cards.length;
        origin = new HashSet<>();
        newCards = new HashSet<>();
        for (int i = 0; i < n/3; i++) {
            origin.add(cards[i]);
        }

        for (int i = 0; i < n/3; i++) {
            int c = cards[i];
            int f = n+1-c;
            if (origin.contains(f)) {
                zero++;
                origin.remove(c);
                origin.remove(f);
            }
        }

        boolean payable = true;
        Label : for (int round = 1; round <= n/3+1 && payable; round++) {
            int idx = n/3 + (round-1)*2;
            payable = false;

            answer = Math.max(round, answer);
            if (idx >= n) break;

            newCards.add(cards[idx]);
            newCards.add(cards[idx+1]);

            if (zero > 0) {
                zero--;
                payable = true;
                continue;
            } else if (coin > 0) {
                for (int c : origin) {
                    if (choice(c)) {
                        coin--;
                        origin.remove(c);
                        payable = true;
                        continue Label;
                    }
                }
                if (coin < 2) break;
                for (int c : newCards) {
                    if (choice(c)) {
                        coin-=2;
                        newCards.remove(c);
                        payable = true;
                        break;
                    }
                }
            }
        }

        return answer;
    }
}