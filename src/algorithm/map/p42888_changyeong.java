package algorithm.map;

import java.io.*;
import java.util.*;

public class p42888_changyeong {
    static String answer[];
    static HashMap<String, String> userInfo;
    static Deque<Chats> kakaoLog;

    public static void separateChatLog(String record[]) {
        StringTokenizer st;

        for (String input : record) {
            st = new StringTokenizer(input);

            String chatLog = st.nextToken();
            String uid = st.nextToken();

            if (chatLog.equals("Change")) {
                String name = st.nextToken();
                changeName(uid, name);
            }
            else if(chatLog.equals("Enter")){
                String name = st.nextToken();
                if (userInfo.getOrDefault(uid, "").equals("")) {
                    //닉네임 기록이 없는 경우
                    userInfo.put(uid, name);
                }
                else {
                    //기존에 닉네임과 다른 닉네임으로 들어가는 경우 -> 닉네임 변경
                    changeName(uid, name);
                }
                kakaoLog.add(new Chats(uid, chatLog));
            }
            else {
                kakaoLog.add(new Chats(uid, chatLog));                
            }
        }
    }

    public static void changeName(String uid, String name) {
        userInfo.replace(uid, name);
    }

    public static void recordChatLog() {
        int idx = 0;
        answer = new String[kakaoLog.size()];

        while (!kakaoLog.isEmpty()) {
            Chats cur = kakaoLog.poll();
            String name = userInfo.get(cur.uid);

            if (cur.message.equals("Enter")) {
                answer[idx] = name + "님이 들어왔습니다.";
            }
            else {
                answer[idx] = name + "님이 나갔습니다.";
            }
            idx++;
        }
    }

    public String[] solution(String[] record) {

        userInfo = new HashMap<>();
        kakaoLog = new ArrayDeque<>();

        separateChatLog(record);
        recordChatLog();

        return answer;
    }

    static class Chats {
        String uid, message;

        public Chats() {
        }

        public Chats(String uid, String message) {
            this.uid = uid;
            this.message = message;
        }
    }
}
