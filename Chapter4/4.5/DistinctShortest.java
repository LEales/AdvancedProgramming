import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
INSTRUCTIONS FOR HOW TO SUBMIT INPUT
first line should contain two ints #Vertexes and #Edges
then #Edges lines should follow describing an edge between two vertexes u and v where 0 <= u,v < #Vertexes
then a line containing a startVertex and a destinationVertex 0 <= startVertex, destinationVertex < V should follow

EXAMPLES IF YOU WANT TO TRY
(make sure to copy the newline as well or press enter after copying the input to the terminal)
Example Input 1:
8 10
0 1
0 7
1 2
2 3
3 4
5 4
5 6
6 3
6 7
5 7
0 3

Example Input 2:
9 10
0 1
0 7
1 2
2 3
3 4
5 4
5 6
6 3
6 7
5 7
0 8

Example Input 3:
8 10
0 1
0 2
2 3
3 4
1 3
4 6
5 6
6 2
6 7
5 0
0 4

 */
public class DistinctShortest {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        HashMap<Integer, LinkedList<Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) g.put(i, new LinkedList<>());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()), u = Integer.parseInt(st.nextToken());
            g.get(v).add(u);
            g.get(u).add(v);
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
        int[] answer = bfsModified(g, s, d);
        System.out.println(answer[0] + " paths found with a distance of " + ((answer[1] == -1) ? "UNAVAILABLE" : answer[1]));
    }

    static int[] bfsModified(HashMap<Integer, LinkedList<Integer>> g, int s, int d) {
        if (s == d) return new int[]{1, 0};
        int V = g.size();
        int[] dist = new int[V];
        int[] count = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();
        dist[s] = 0;
        count[s] = 1;
        q.add(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            if (dist[v] == dist[d]) return new int[]{count[d], dist[d]};
            for (int u : g.get(v)) {
                if (dist[u] == Integer.MAX_VALUE) {
                    dist[u] = dist[v] + 1;
                    count[u] = count[v];
                    q.add(u);
                } else if (dist[u] == dist[v] + 1) count[u] += count[v];
            }
        }
        return new int[]{0, -1};
    }
}
