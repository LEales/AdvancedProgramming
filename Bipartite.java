package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
INSTRUCTIONS FOR HOW TO SUBMIT INPUT
first line should contain two ints #Vertexes and #Edges
then #Edges lines should follow describing an edge between two vertexes u and v where 0 <= u,v < #Vertexes

(make sure to copy the newline as well or press enter after copying the input to the terminal)
Example input 1:
6 5
0 1
0 3
2 1
2 5
4 3

Example input 2:
6 6
0 1
0 3
2 1
2 5
4 3
4 2

Example input 3:
8 4
0 1
2 3
4 5
6 7

 */

public class Bipartite {
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
        System.out.println(isBipartite(g) ? "It is Bipartite" : "It is NOT Bipartite");
    }

    static boolean isBipartite(HashMap<Integer, LinkedList<Integer>> g) {
        int V = g.size();
        int[] set = new int[V]; //this array could be used to identify what set a vertex belongs to.
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                visited[i] = true;
                set[i] = 1;
                if (!dfs(g, visited, set, i)) return false;
            }
        }
        return true;
    }


    static boolean dfs(HashMap<Integer, LinkedList<Integer>> g, boolean[] visited, int[] set, int v) {
        int toSet = (set[v] == 1) ? 2 : 1;
        for (Integer u : g.get(v)) {
            if (!visited[u]) {
                visited[u] = true;
                set[u] = toSet;
                if (!dfs(g, visited, set, u)) return false;
            } else if (set[u] == set[v]) return false;
        }
        return true;
    }
}
