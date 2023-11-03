import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
Idea, run bfs from all A and S to all other A and S to create a EdgeList.
Find Minimum spanning tree of that edge list to find minimum weight.
 */

class Aliens {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
            char[][] g = new char[x][y];
            HashMap<Pair, Integer> aliens = new HashMap<>();
            int a = 0;
            for (int i = 0; i < x; i++) {
                String s = br.readLine();
                for (int j = 0; j < s.length(); j++) {
                    g[i][j] = s.charAt(j);
                    if (g[i][j] == 'A' || g[i][j] == 'S') aliens.put(new Pair(i, j), a++);
                }
            }
            ArrayList<Edge> edges = new ArrayList<>();
            int V = aliens.size();
            for (Pair first : aliens.keySet()) {
                LinkedList<Pair> queue = new LinkedList<>();
                queue.add(first);
                boolean[][] visited = new boolean[x][y];
                visited[first.x][first.y] = true;
                int[][] dFrom = new int[x][y];
                dFrom[first.x][first.y] = 0;
                while (!queue.isEmpty()) {
                    Pair curr = queue.poll();
                    check(g, dFrom, visited, edges, queue, aliens, first, curr.x, curr.y, -1, 0);
                    check(g, dFrom, visited, edges, queue, aliens, first, curr.x, curr.y, 1, 0);
                    check(g, dFrom, visited, edges, queue, aliens, first, curr.x, curr.y, 0, -1);
                    check(g, dFrom, visited, edges, queue, aliens, first, curr.x, curr.y, 0, 1);
                }
            }
            Collections.sort(edges);
            int[] p = new int[V];
            int[] s = new int[V];
            for (int i = 0; i < V; i++) {
                p[i] = i;
                s[i] = 1;
            }
            int i = 0, used = 0, w = 0;
            while (used < V - 1) {
                Edge edge = edges.get(i++);
                if (union(p, s, edge.from, edge.to)) {
                    used++;
                    w += edge.w;
                }
            }
            out.append(w).append("\n");
        }
        System.out.print(out);
    }

    static void check(char[][] g, int[][] dFrom, boolean[][] visited, List<Edge> edges, Queue<Pair> queue, HashMap<Pair, Integer> aliens, Pair first, int x, int y, int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;
        if (g[newX][newY] != '#' && !visited[newX][newY]) {
            visited[newX][newY] = true;
            dFrom[newX][newY] = dFrom[x][y] + 1;
            if (g[newX][newY] != ' ')
                edges.add(new Edge(aliens.get(first), aliens.get(new Pair(newX, newY)), dFrom[newX][newY]));
            else queue.add(new Pair(newX, newY));
        }
    }

    static boolean union(int[] p, int[] s, int x, int y) {
        int rx = find(p, x), ry = find(p, y);
        if (rx == ry) return false;
        if (s[rx] > s[ry]) {
            p[ry] = rx;
            s[rx] += s[ry];
        } else {
            p[rx] = ry;
            s[ry] += s[rx];
        }
        return true;
    }

    static int find(int[] p, int x) {
        int r = x;
        while (r != p[r]) r = p[r];
        while (x != r) {
            int t = p[x];
            p[x] = r;
            x = t;
        }
        return r;
    }

    static class Edge implements Comparable<Edge> {
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(w, o.w);
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
