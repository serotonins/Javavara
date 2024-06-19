package algorithm.implement;

class p70129_jisu {
    static int zero = 0;

    public int countOne(String s) {
        int cnt = 0;

        for (String c : s.split("")) {
            if (c.equals("1")) cnt++;
        }

        zero += (s.length() - cnt);

        return cnt;
    }

    public String binary(int cnt) {
        String s = "";
        while (cnt != 1) {
            s = cnt % 2 + s;
            cnt /= 2;
        }
        return 1 + s;
    }

    public int[] solution(String s) {

        String str = s;
        int check = s.length();
        int cnt = 0;
        while (check != 1) {
            cnt++;
            check = countOne(str);
            if (check == 1) break;
            str = binary(check);
        }

        return new int[] {cnt, zero};
    }
}