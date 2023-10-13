package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SpanningForest {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()), u = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(v, u, w));
        }
        System.out.println(solve(edges, n));
    }

    static int solve(ArrayList<Edge> edges, int n) {
        edges.sort(Comparator.comparingInt(o -> o.w));
        HashSet<Integer> used = new HashSet<>();
        int w = 0;
        int i = 0;
        while (i < edges.size() && used.size() < n) {
            Edge e = edges.get(i++);
            if (!used.contains(e.to) || !used.contains(e.f)) {
                used.add(e.to);
                used.add(e.f);
                w = e.w;
            }
        }
        return w;
    }

    static class Edge {
        int f, to, w;

        public Edge(int f, int to, int w) {
            this.f = f;
            this.to = to;
            this.w = w;
        }
    }
}
