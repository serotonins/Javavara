package algorithm;

import java.io.*;
import java.util.*;

public class p92341_changyeong {
    static int answer[];
    static HashSet<Integer> cars;
    static HashMap<Integer, String> parking;
    static HashMap<Integer, Integer> totalTime;
    static PriorityQueue<Fee> parkingFee;

    //비용 계산
    public static int checkPrice(int fees[], int time) {
        int price = 0;

        if (time <= fees[0]) {
            price = fees[1];
        } else {
            int remain = time - fees[0];

            int div = remain / fees[2];

            if (remain != div * fees[2]) {
                div++;
            }

            price += (fees[1] + (div * fees[3]));
        }

        return price;
    }

    //차량번호 오름차순으로 정렬
    public static void alignParkingFee(int fees[]) {
        for (int num : cars) {
            parkingFee.add(new Fee(num, checkPrice(fees, totalTime.get(num))));
        }

        answer = new int[parkingFee.size()];

        int idx = 0;
        while (!parkingFee.isEmpty()) {
            Fee next = parkingFee.poll();
            answer[idx] = next.price;
            idx++;
        }
    }

    //차량별 누적 시간 기록
    public static void recordTime(int carNum, String src, String dest) {
        int time = totalTime.getOrDefault(carNum, 0);
        String input[] = src.split(":");
        String output[] = dest.split(":");

        int start = Integer.parseInt(input[0]) * 60 + Integer.parseInt(input[1]);
        int end = Integer.parseInt(output[0]) * 60 + Integer.parseInt(output[1]);

        time += (end - start);
        totalTime.put(carNum, time);
    }

    //시간 기록
    public static void checkTime(String records[]) {
        StringTokenizer st;

        for (String record : records) {
            st = new StringTokenizer(record);

            String time = st.nextToken();
            int carNum = Integer.parseInt(st.nextToken());

            cars.add(carNum);

            if (parking.containsKey(carNum)) {
                recordTime(carNum, parking.get(carNum), time);
                parking.remove(carNum);
            } 
            else {
                parking.put(carNum, time);
            }
        }

        for (int num : cars) {
            if (parking.containsKey(num)) {
                recordTime(num, parking.get(num), "23:59");
                parking.remove(num);
            }
        }
    }

    public int[] solution(int[] fees, String[] records) {

        cars = new HashSet<>();
        parking = new HashMap<>();
        totalTime = new HashMap<>();
        parkingFee = new PriorityQueue<>();

        checkTime(records);
        alignParkingFee(fees);

        return answer;
    }

    static class Fee implements Comparable<Fee> {
        int carNum, price;

        public Fee() {
        }

        public Fee(int carNum, int price) {
            this.carNum = carNum;
            this.price = price;
        }

        public int compareTo(Fee o) {
            return this.carNum - o.carNum;
        }
    }
}