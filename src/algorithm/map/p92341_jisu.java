package algorithm.map;

import java.util.*;

class p92341_jisu {
    public int[] solution(int[] fees, String[] records) {

        TreeMap<Integer, Integer> accMap = new TreeMap<>();

        for (String str: records) {
            String[] arr = str.split(" ");
            int hour = Integer.parseInt(arr[0].substring(0, 2));
            int minute = Integer.parseInt(arr[0].substring(3, 5));
            int time = hour * 60 + minute;
            int carNum = Integer.parseInt(arr[1]);
            int inout = arr[2].equals("IN") ? -1 : 1;

            if (!accMap.containsKey(carNum)) accMap.put(carNum, 0);
            accMap.put(carNum, accMap.get(carNum) + time * inout); // getOrDefault 함수 썼으면 좋았을 것
        }

        int maxi = 24 * 60 - 1;

        int[] answer = new int[accMap.size()];
        int i = 0;
        for (int carNum : accMap.keySet()) {
            answer[i] = fees[1];

            int acc = accMap.get(carNum);
            if (acc < 1) acc += maxi;
            acc -= fees[0];

            if (acc > 0) {
                answer[i] += acc / fees[2] * fees[3] + (acc % fees[2] > 0 ? 1 : 0) * fees[3];
            }
            i++;
        }

        return answer;
    }
}
