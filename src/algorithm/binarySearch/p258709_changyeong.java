package algorithm.binarySearch;

import java.io.*;
import java.util.*;

public class p258709_changyeong {
    static int N,rate;
    static int answer[];
    static List<Integer> sumA, sumB, selected;
    
    public static void binarySearch(){
        int l, r, m, cnt;
        
        cnt = 0;
        for(int i = 0; i < sumA.size(); i++){
            l = 0;
            r = sumB.size() - 1;
            
            int check = Integer.MIN_VALUE;
            while(l <= r){
                m = (l + r) / 2;
                
                if(sumB.get(m) < sumA.get(i)){
                    l = m + 1;
                    check = Integer.max(check, m);
                }
                else{
                    r = m - 1;
                }
            }
            
            if(check != Integer.MIN_VALUE){
                cnt += check + 1;
            }
        }
        
        if(rate < cnt){
            rate = cnt;
            
            for(int i = 0; i < selected.size(); i++){
                answer[i] = selected.get(i);
            }
        }
    }
    
    public static void separate(int dice[][]){
        //A 주사위의 합 모든 경우의 수와 B 주사위의 합 모든 경우의 수 각각 저장
        
        sumA.clear();
        sumB.clear();
        
        for(int i = 1; i <= N; i++){
            if(selected.contains(i)){
                //A의 주사위인 경우
                if(sumA.isEmpty()){
                    for(int a : dice[i - 1]){
                        sumA.add(a);
                    }
                }
                else{
                    int size = sumA.size();
                    for(int idx = 0; idx < size; idx++){
                        int num = sumA.get(0);
                        sumA.remove(0);
                        
                        for(int a : dice[i - 1]){
                            sumA.add(num + a);
                        }
                    }
                }
            }
            else{
                //B의 주사위인 경우
                if(sumB.isEmpty()){
                    for(int b: dice[i - 1]){
                        sumB.add(b);
                    }
                }
                else{
                    int size = sumB.size();
                    for(int idx = 0; idx < size; idx++){
                        int num = sumB.get(0);
                        sumB.remove(0);
                        
                        for(int b : dice[i - 1]){
                            sumB.add(num + b);
                        }
                    }
                }
            }
        }
        
        // Collections.sort(sumA);
        Collections.sort(sumB);        
        
        //최대 6의 5승? 6 36 216 1272 7632 45792
        binarySearch();
    }
    
    public static void combine(int depth, int prev, int dice[][]){
        if(depth == N / 2){
            separate(dice);
            return;
        }
        
        for(int i = prev + 1; i <= N; i++){
            selected.add(i);
            combine(depth + 1, i, dice);
            selected.remove(selected.size() - 1);
        }
    }
    
    public int[] solution(int[][] dice) {
        N = dice.length;
        rate = 0;
        answer = new int[N / 2];
        sumA = new ArrayList<>();
        sumB = new ArrayList<>();
        selected = new ArrayList<>();
        
        combine(0, 0, dice);
        
        return answer;
    }
}