package algorithm.dijkstra;

import java.io.*;
import java.util.*;

public class p72413_changyeong {
    static int answer;
    static int share[], aloneA[], aloneB[];
    static List<Node> connection[];
    
    public static int[] dijkstra(int src, int N){
        int dist[] = new int[N + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src,0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(dist[cur.num] < cur.cost){
                continue;
            }
            
            for(Node next : connection[cur.num]){
                if(dist[next.num] > cur.cost + next.cost){
                    dist[next.num] = cur.cost + next.cost;
                    pq.add(new Node(next.num, dist[next.num]));
                }
            }
        }
        
        return dist;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        answer = Integer.MAX_VALUE;
        
        connection = new List[n+1];
        share = new int[n+1];
        aloneA = new int[n+1];
        aloneB = new int[n+1];
        
        for(int i=1;i<=n;i++){
            connection[i] = new ArrayList<>();
        }
        
        for(int next[] : fares){
            connection[next[0]].add(new Node(next[1], next[2]));
            connection[next[1]].add(new Node(next[0], next[2]));
        }
        
        aloneA = dijkstra(a,n);
        aloneB = dijkstra(b,n);
        share = dijkstra(s,n);
        
        for(int i = 1; i <= n; i++){
            answer = Math.min(answer, share[i] + aloneA[i] + aloneB[i]);
        }
        
        return answer;
    }
    
    static class Node implements Comparable<Node>{
        int num, cost;
        public Node(int num, int cost){
            this.num = num;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}
