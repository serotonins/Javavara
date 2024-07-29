package algorithm.implement;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class p42893_changyeong {
    static int answer, basic, tmp;
    static String home, body;
    static Pattern pagePattern, outPattern, wordPattern;
    static Matcher finder;
    static List<String> urlList;
    static HashMap<String, List<String>> pageLink;
    static HashMap<String, Page> pageData;
    static PriorityQueue<Page> result;

    public static void checkTotalScore() {
        //매칭점수 계산
        for (String key : pageLink.keySet()) {
            Page pp = pageData.get(key);
            for (String out : pageLink.get(key)) {
                if (pageData.containsKey(out)) {
                    Page oo = pageData.get(out);
                    oo.total += pp.out;
                }
            }
        }

        //정렬하기 위해 pq 사용
        for (String key : pageLink.keySet()) {
            result.add(pageData.get(key));
        }
    }

    public static void splitData(String cur) {
        // 해당 페이지 주소 추출
        finder = pagePattern.matcher(cur);
        if (finder.find()) {
            home = finder.group(1);
        }

        // 외부 링크 주소 추출후 리스트에 저장
        finder = outPattern.matcher(cur);
        urlList = new ArrayList<>();
        while (finder.find()) {
            String output = finder.group(1);
            urlList.add(output);
        }
        pageLink.put(home, urlList);

        // 단어 갯수 추출후 저장
        body = cur.split("</body>")[0];
        // 영단어만 검색을 위해 replaceAll로 숫자 제거
        body = body.split("<body>")[1].replaceAll("[0-9]", " ");
        finder = wordPattern.matcher(body.toLowerCase());
        basic = 0;
        while (finder.find()) {
            basic++;
        }
        double out_result = ((double) basic / pageLink.get(home).size());
        pageData.put(home, new Page(tmp, out_result, basic));
    }

    public int solution(String word, String[] pages) {
        pagePattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
        outPattern = Pattern.compile("<a href=\"(\\S*)\"");
        wordPattern = Pattern.compile("\\b(?i)" + word + "\\b");

        result = new PriorityQueue<>();
        pageLink = new HashMap<>();
        pageData = new HashMap<>();

        home = "";
        body = "";
        tmp = 0;
        for (String cur : pages) {
            splitData(cur);
            tmp++;
        }

        checkTotalScore();

        return result.poll().index;
    }

    static class Page implements Comparable<Page> {
        int index;
        double out, total;

        public Page(int index, double out, double total) {
            this.index = index;
            this.out = out;
            this.total = total;
        }

        @Override
        public int compareTo(Page o) {
            // 이 부분 바꿨더니 해결됐음 아마 int로 강제 형변환하면서 꼬이는듯
            // if(o.total == this.total){
            // return this.index - o.index;
            // }

            // return (int) (o.total - this.total);

            if (o.total == this.total) {
                return Integer.compare(this.index, o.index);
            } 
            else {
                return Double.compare(o.total, this.total);
            }
        }
    }
}
