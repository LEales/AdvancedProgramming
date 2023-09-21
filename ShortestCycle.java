package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
This solution runs in O(V**2 + V*(E log(V))) for a sparse graph and O(V**2 + V * V**2) = O(V**3) for a dense graph
INSTRUCTIONS FOR HOW TO SUBMIT INPUT
first line should contain two ints #Vertexes and #Edges
then #Edges lines should follow describing an edge between two vertexes u and v with a weight w where 0 <= u,v < #Vertexes

EXAMPLE IF YOU WANT TO TRY
(make sure to copy the newline as well or press enter after copying the input to the terminal)
Example Input 1:
9 14
0 1 3
0 7 20
1 7 2
1 2 6
2 3 7
3 4 2
4 6 1
5 4 2
6 5 10
6 3 9
7 6 1
7 8 5
7 5 3
8 0 3

Example Input 2:
9 13
0 1 3
0 7 20
1 7 2
1 2 6
2 3 7
3 4 2
5 4 2
6 5 10
6 3 9
7 6 1
7 8 5
7 5 3
8 0 3

Example Input 3:
6 5
0 1
1 2
2 3
3 4
4 5

 */
public class ShortestCycle {
    static int[] pq; //for binary heap
    static int[] qp; //for binary heap
    static Integer[] keys; //for binary heap
    static int V;
    static int n = 0; //for binary heap
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        pq = new int[V + 1];
        qp = new int[V + 1];
        Arrays.fill(qp, -1);
        keys = new Integer[V + 1];
        int m = Integer.parseInt(st.nextToken());
        HashMap<Integer, LinkedList<Edge>> g = new HashMap<>();
        for (int i = 0; i < V; i++) g.put(i, new LinkedList<>());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            g.get(from).add(new Edge(to, w));
        }
        int min = shortest(g);
        System.out.println("Weight of smallest cycle is " + ((min == Integer.MAX_VALUE) ? "nonexistent (acyclic)" : min));
    }

    static int shortest(HashMap<Integer, LinkedList<Edge>> g) {
        int[][] mtx = new int[V][];
        for (int i = 0; i < V; i++) {

            mtx[i] = dijkstra(g, i);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) continue;
                if (mtx[i][j] == Integer.MAX_VALUE || mtx[j][i] == Integer.MAX_VALUE) continue;
                min = Math.min(min, mtx[i][j] + mtx[j][i]);
            }
        }
        return min;
    }


    static int[] dijkstra(HashMap<Integer, LinkedList<Edge>> g, int s) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        push(s, 0);
        while (n != 0) {
            int v = pop();
            visited[v] = true;
            for (Edge e : g.get(v)) {
                int u = e.to;
                if (visited[u]) continue;
                if (dist[u] > dist[v] + e.w) {
                    dist[u] = dist[v] + e.w;
                    if (contains(u)) decreaseKey(u, dist[u]);
                    else push(u, dist[u]);
                }
            }
        }
        return dist;
    }

    //binary heap methods from old algorithm course

    static boolean greater(int i, int j) {
        return keys[pq[i]] > keys[pq[j]];
    }

    static void decreaseKey(int i, Integer key) {
        keys[i] = key;
        swim(qp[i]);
    }

    static boolean contains(int i) {
        return qp[i] != -1;
    }

    static void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    static int pop() {
        int min = pq[1];
        exch(1, n--);
        sink();
        qp[min] = -1;
        keys[min] = null;
        pq[n + 1] = -1;
        return min;
    }

    static void push(int i, int key) {
        qp[i] = ++n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    static void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    static void sink() {
        int k = 1;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


    static class Edge {
        int to, w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}
