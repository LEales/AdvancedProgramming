import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
Union-find and keep track of sizes. Print largest size
 */
class Friends {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
            int[] p = new int[n];
            int[] s = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
                s[i] = 1;
            }
            int max = 1;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                max = Math.max(max, union(p, s, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
            }
            out.append(max).append("\n");
        }
        System.out.print(out);
    }

    static int union(int[] p, int[] s, int x, int y) {
        int rx = find(p, x), ry = find(p, y);
        if (rx == ry) return -1;
        if (s[rx] > s[ry]) {
            p[ry] = rx;
            s[rx] += s[ry];
            return s[rx];
        } else {
            p[rx] = ry;
            s[ry] += s[rx];
            return s[ry];
        }
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
}
