package algorithm.prefixSum;

class p118667_jisu {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        int answer = Integer.MAX_VALUE;
        int maxi = 0;

        long onesum = 0;
        long twosum = 0;

        int[] arr = new int[n * 2];
        long[] partSum = new long[n * 3];

        for (int i = 0; i < n; i++) {
            arr[i] = queue1[i];
            onesum += arr[i];
            partSum[i] = onesum;
            arr[n + i] = queue2[i];
            twosum += arr[n + i];
            maxi = Math.max(maxi, queue1[i]);
            maxi = Math.max(maxi, queue2[i]);
        }

        long half = (onesum + twosum) / 2;
        if ((onesum + twosum) % 2 != 0 | maxi > half) return -1;

        for (int i = 0; i < n; i++) {
            partSum[n + i] = partSum[n + i - 1] + queue2[i];
        }

        for (int i = 0; i < n; i++) {
            partSum[2 * n + i] = partSum[2 * n + i - 1] + queue1[i];
        }

        int s = 0;
        int e = n-1;
        while (true) {
            if (e >= 3 * n | s > e | e - (s-1) >= 2 * n) break;

            long now;
            if (s == 0) {
                now = partSum[e];
            } else {
                now = partSum[e] - partSum[s-1];
            }

            if (now == half) {
                answer = Math.min(Math.abs(e-n+1) + s, answer);
                e++;
            } else if (now > half) {
                s++;
            } else {
                e++;
            }
        }


        if (answer == Integer.MAX_VALUE) return -1;

        return answer;
    }
}
