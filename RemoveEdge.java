package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class RemoveEdge {
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
        boolean[] vis = new boolean[n];
        int[] result = dfs(g, 0, vis, 0);
        System.out.println((result == null) ? "IMPOSSIBLE" : result[0] + " " + result[1]);
    }

    static int[] dfs(HashMap<Integer, LinkedList<Integer>> g, int v, boolean[] vis, int r) {
        if (vis[v]) return new int[]{v, r};
        vis[v] = true;
        for (int u : g.get(v)) {
            if (u != r) {
                int[] ans = dfs(g, u, vis, v);
                if (ans != null) return ans;
            }
        }
        return null;
    }
}
