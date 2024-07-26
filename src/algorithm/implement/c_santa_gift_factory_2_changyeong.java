package algorithm.implement;

import java.io.*;
import java.util.*;

public class c_santa_gift_factory_2_changyeong {
    static int q,n,m;
    static Node nodes[];
    static Belt belts[];
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void buildFactory(){
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        belts = new Belt[n];
        for(int i = 0; i < n;i++){
            belts[i] = new Belt();
        }

        nodes = new Node[m+1];
        for(int i = 1; i <= m; i++){
            int num = Integer.parseInt(st.nextToken()) - 1;
            nodes[i] = new Node();
            belts[num].addLast(i);
        }
    }

    public static void moveAll(){
        int src = Integer.parseInt(st.nextToken()) - 1;
        int dest = Integer.parseInt(st.nextToken()) - 1;

        if(belts[src].size == 0){
            //src번 벨트의 크기가 0인경우
            sb.append(belts[dest].size).append('\n');
            return;
        }

        if(belts[dest].size > 0){
            int srcTail = belts[src].tail;
            int destHead = belts[dest].head;
            
            nodes[srcTail].next = destHead;
            nodes[destHead].prev = srcTail;

            belts[dest].head = belts[src].head;
            belts[dest].size += belts[src].size;
        }
        else{
            belts[dest].head = belts[src].head;
            belts[dest].tail = belts[src].tail;
            belts[dest].size = belts[src].size;
        }

        belts[src].head = -1;
        belts[src].tail = -1;
        belts[src].size = 0;

        sb.append(belts[dest].size).append('\n');
    }

    public static void replaceFirst(){
        int src = Integer.parseInt(st.nextToken()) - 1;
        int dest = Integer.parseInt(st.nextToken()) - 1;

        if(!(belts[src].size == 0 && belts[dest].size == 0)){
            if(belts[src].size > 0 && belts[dest].size == 0){
                belts[dest].addFirst(belts[src].pollFront());
            }
            else if(belts[src].size == 0 && belts[dest].size > 0){
                belts[src].addFirst(belts[dest].pollFront());
            }
            else{
                //둘다 크기 0 이상
                int tmp1 = belts[src].pollFront();
                int tmp2 = belts[dest].pollFront();
                belts[src].addFirst(tmp2);
                belts[dest].addFirst(tmp1);
            }
        }
        sb.append(belts[dest].size).append('\n');
    }

    public static void divideGift(){
        int src = Integer.parseInt(st.nextToken()) - 1;
        int dest = Integer.parseInt(st.nextToken()) - 1;

        int size = belts[src].size;

        if(size < 2){
            sb.append(belts[dest].size).append('\n');
            return;
        }

        int start = belts[src].head;
        int end = belts[src].get((size / 2) - 1);

        belts[src].head = nodes[end].next;
        nodes[belts[src].head].prev = -1;
        nodes[end].next = -1;
        belts[src].size -= (size / 2);

        if(belts[dest].size > 0){
            int tmp = belts[dest].head;
            belts[dest].head = start;
            nodes[end].next = tmp;
            nodes[tmp].prev = end;
            belts[dest].size += (size / 2);
        }
        else{
            belts[dest].head = start;
            belts[dest].tail = end;
            belts[dest].size = (size / 2);
        }

        sb.append(belts[dest].size).append('\n');
    }

    public static void getGiftInfo(){
        int num = Integer.parseInt(st.nextToken());

        int a = -1;
        int b = -1;

        if(nodes[num].prev != -1){
            a = nodes[num].prev;
        }
        if(nodes[num].next != -1){
            b = nodes[num].next;
        }

        int result = a + (2 * b);

        sb.append(result).append('\n');
    }

    public static void getBeltInfo(){
        int num = Integer.parseInt(st.nextToken()) - 1;

        int a = -1;
        int b = -1;
        int c;

        if(belts[num].head != -1){
            a = belts[num].head;
        }
        if(belts[num].tail != -1){
            b = belts[num].tail;
        }

        c = belts[num].size;

        int result = a + (2 * b) + (3 * c);

        sb.append(result).append('\n');
    }

    public static void pre() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        q = Integer.parseInt(br.readLine());

        sb = new StringBuilder();

        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());

            int comm = Integer.parseInt(st.nextToken());

            if(comm == 100){
                buildFactory();
            }
            else if(comm == 200){
                moveAll();
            }
            else if(comm == 300){
                replaceFirst();
            }
            else if(comm == 400){
                divideGift();
            }
            else if(comm == 500){
                getGiftInfo();
            }
            else if(comm == 600){
                getBeltInfo();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        pre();
        System.out.println(sb);
    }

    static class Node{
        int prev,next;

        public Node(){
            this.prev = -1;
            this.next = -1;
        }
    }

    static class Belt{
        int head,tail,size;

        public Belt(){
            this.head = -1;
            this.tail = -1;
            this.size = 0;
        }

        public int get(int num){
            int cur = head;
            for(int i = 0; i < num; i++){
                cur = nodes[cur].next;
            }
            return cur;
        }

        public void addFirst(int num){
            if(size == 0){
                head = num;
                tail = num;
                nodes[num].prev = -1;
                nodes[num].next = -1;
                size++;
                return;
            }

            nodes[head].prev = num;
            nodes[num].next = head;
            head = num;
            size++;
        }

        public void addLast(int num){
            if(size == 0){
                addFirst(num);
                return;
            }

            nodes[tail].next = num;
            nodes[num].prev = tail;
            tail = num;
            nodes[num].next = -1;
            size++;
        }

        public int pollFront(){
            int num = head;
            if(size == 1){
                head = -1;
                tail = -1;
                size--;
                return num;
            }

            int next = nodes[head].next;
            nodes[next].prev = -1;
            head = next;
            nodes[num].next = -1;
            size--;
            return num;
        }
    }
}
