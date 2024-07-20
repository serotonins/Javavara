package algorithm.implement;

import java.io.*;
import java.util.*;

public class p258707_changyeong {
    static int N,target,round;
    static HashSet<Integer> selected, paid;
    
    public static void play(int coin, int cards[]){
        int remain = coin;
        boolean flag = true;
        
        for(int idx = N / 3; idx <= N && flag; idx += 2){
            round++;
            flag = false;
            
            if(idx == N){
                //최대 라운드에 도달한 경우
                break;
            }
            
            paid.add(cards[idx]);
            paid.add(cards[idx+1]);
            
            for(int i : selected){
                //갖고 있는 것중에 해결가능한 경우
                if(selected.contains(target - i)){
                    selected.remove(i);
                    selected.remove(target - i);
                    flag = true;
                    break;
                }
            }
            
            if(!flag){
                //코인 1개로 추가 카드 1장 가지고 다음 라운드 진행 가능
                if(remain > 0){
                    for(int i : selected){
                        if(paid.contains(target - i)){
                            selected.remove(i);
                            paid.remove(target - i);
                            remain--;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            if(!flag){
                //코인 2개로 추가 카드 2장 가지고 다음 라운드 진행 가능
                if(remain > 1){
                    for(int i : paid){
                        if(paid.contains(target - i)){
                            paid.remove(i);
                            paid.remove(target - i);
                            remain -= 2;
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public int solution(int coin, int[] cards) {
        N = cards.length;
        target = N + 1;
        round = 0;
        
        selected = new HashSet<>();
        paid = new HashSet<>();
        
        for(int i = 0; i < N / 3; i++){
            selected.add(cards[i]);
        }
        
        play(coin, cards);
        
        return round;
    }
}
