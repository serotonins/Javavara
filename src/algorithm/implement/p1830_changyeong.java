package algorithm.implement;

import java.io.*;
import java.util.*;

public class p1830_changyeong {
    
    static String answer, text; //text는 sentence 문자열의 전역변수화
    static List<Word> word;
    static LinkedHashMap<Character, List<Integer>> symbol;    
    
    public static String concatWord(int src, int dest){
        StringBuilder sb = new StringBuilder();
        
        for(int i = src; i <= dest; i++){
            if(text.charAt(i) >= 'A' && text.charAt(i) <= 'Z'){
                sb.append(text.charAt(i));
            }
        }
        
        return sb.toString()+" ";
    }
    
    public static void convertSentence(){
        StringBuilder sb = new StringBuilder();
        int src, dest, rule, idx, strIndex;
        
        idx = 0;
        strIndex = 0;
        for(Word cur : word){
            rule = cur.ruleNum;
            src = cur.src;
            dest = cur.dest;
            
            if(rule == 3){
                //규칙3 : 규칙1 또는 규칙2
                int end = idx < word.size() - 1 ? word.get(idx + 1).src : text.length();
                
                if(strIndex <= src - 1 && dest + 1 < end){
                    //규칙1 확정
                    src--;
                    dest++;
                }                
            }
            
            if(strIndex < src){
                //이전과 현재 사이에서 안 붙인 구간이 있는 경우
                sb.append(concatWord(strIndex, src - 1));
            }
            sb.append(concatWord(src, dest));
            strIndex = dest + 1;
            idx++;
        }
        
        if(strIndex < text.length()){
            //맨 마지막 안 붙인 구간이 있는 경우
            sb.append(concatWord(strIndex, text.length() - 1));
        }
        
        answer = sb.toString().trim();
    }
    
    public static void detectRule(){
        int rule;
        Boolean valid_dist;
        int src_wprev, dest_wprev; // 이전 단어 시작, 끝 인덱스(한 규칙 중복 적용 || 이전 단어와 겹침 유무 확인용)
        int src_sprev, dest_sprev; // 이전 기호 시작, 끝 인덱스
        int src_w, dest_w; //단어 시작, 끝 인덱스
        int src_s, dest_s; //기호 시작, 끝 인덱스
        
        src_wprev = -1;
        dest_wprev = -1;
        src_sprev = -1;
        dest_sprev = -1;
        
        for(List<Integer> pos : symbol.values()){
            int size = pos.size();
            valid_dist = true;
            
            src_w = pos.get(0);
            src_s = pos.get(0);
            dest_w = pos.get(size - 1);
            dest_s = pos.get(size - 1);
            
            for(int i = 1; i < size; i++){
                //간격 확인
                if(pos.get(i) - pos.get(i - 1) < 2){
                    //같은 문자 기호끼리 붙어있는 경우
                    return;
                }
                if(pos.get(i) - pos.get(i - 1) > 2){
                    valid_dist = false;
                    if(size >= 3){
                        //aAAaAa 이런 경우
                        //aAAAa 이럴수도 있어서 size가 3 이상일 경우만
                        return;
                    }
                    break;
                }
            }
                
            rule = -1;
            if(size == 1 || size >= 3){
                //규칙1인 경우
                rule = 1;
                src_w--;
                dest_w++;
                if(src_w < 0 || text.length() <= dest_w){
                    //범위 벗어난 경우
                    return;
                }
            }
            
            if(size == 2){
                //거리가 2보다 큰 게 있는 경우(aAAAa : 규칙2)
                //거리가 2보다 큰 게 없는 경우 규칙3(규칙1 또는 규칙2)
                rule = !valid_dist ? 2 : 3;
            }
            
            if(src_sprev < src_s && dest_sprev > dest_s){
                //이전 기호 범위 내에 포함되는 경우
                if(rule == 2){
                    return;
                }
                continue;
            }
            
            if(dest_wprev >= src_w){
                //단어 겹치는 경우
                return;
            }
            
            word.add(new Word(src_w, dest_w, rule));
            src_wprev = src_w;
            dest_wprev = dest_w;
            src_sprev = src_s;
            dest_sprev = dest_s;
            System.out.println(src_w+" "+dest_w+" / "+src_s+" "+dest_s);
        }
        
        convertSentence();
    }
    
    public String solution(String sentence) {
        answer = "invalid";
        text = sentence;
        
        //너무 어려운데....?
        //이런 문제를 싸피니티 문제에 왜 넣어둔거야
        
        word = new ArrayList<>();
        symbol = new LinkedHashMap<>();
        
        for(int i = 0; i < sentence.length(); i++){
            Character current = sentence.charAt(i);
            if(current >= 'a' && current <= 'z'){
                if(!symbol.containsKey(current)){
                    symbol.put(current, new ArrayList<>());
                }
                symbol.get(current).add(i);
            }
        }
        
        detectRule();
        
        return answer;
    }
    
    static class Word {
        int src, dest, ruleNum;
        
        public Word(int src, int dest, int ruleNum){
            this.src = src;
            this.dest = dest;
            this.ruleNum = ruleNum;
        }
    }
}
