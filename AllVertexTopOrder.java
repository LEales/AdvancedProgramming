package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
INSTRUCTIONS FOR HOW TO SUBMIT INPUT
first line should contain two ints #Vertexes and #Edges separated by a space
then #Edges lines should follow describing an edge between two vertexes u and v where 0 <= u,v < #Vertexes

EXAMPLES IF YOU WANT TO TRY
(make sure to copy the newline as well or press enter after copying the input to the terminal)
Example Input 1:
5 4
0 1
1 2
2 3
3 4

Example Input 2:
6 9
0 1
1 2
2 3
3 4
4 5
0 5
1 5
2 5
3 5

Example Input 3:
5 5
0 1
1 2
2 3
3 4
4 0

Example Input 4:
6 8
0 1
1 2
3 4
4 5
0 5
1 5
2 5
3 5

 */

public class AllVertexTopOrder {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        HashMap<Integer, LinkedList<Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) g.put(i, new LinkedList<>());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            g.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
        }
        System.out.println(visitAll(g));
    }

    static String visitAll(HashMap<Integer, LinkedList<Integer>> g) {
        int V = g.size();
        int[] inDegree = new int[V];
        for (int v = 0; v < V; v++) {
            for (Integer u : g.get(v)) {
                inDegree[u]++;
            }
        }
        int next = -1;
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                if (next == -1) next = i;
                else return "This cannot be done :(";
            }
        }
        int[] path = new int[V];
        int visited = 0;
        while (next != -1) {
            int v = next;
            path[visited++] = v;
            next = -1;
            for (Integer u : g.get(v)) {
                inDegree[u]--;
                if (inDegree[u] == 0) {
                    if (next == -1) next = u;
                    else return "This cannot be done :(";
                }
            }
        }

        if (visited == V) {
            /*
            //remove comments if you want to see the path.
            for (Integer i : path) {
                System.out.print(i + " ");
            }
            System.out.println();
             */
            return "This can be done! :)";
        } else return "This graph has a cycle!";
    }
}
