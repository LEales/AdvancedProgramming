package contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//timelimit
//problem 1129
public class Stargate {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int[] p = new int[1], s = new int[1];
        boolean def = false;
        String in;
        int t = 0;
        while ((in = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(in);
            char cmd = st.nextToken().charAt(0);
            if (cmd == 'd' || cmd == 'D') {
                def = true;
                t = Integer.parseInt(st.nextToken());
                if (t == 0) {
                    def = false;
                    continue;
                }
                p = new int[t];
                s = new int[t];
                for (int i = 0; i < t; i++) {
                    p[i] = i;
                    s[i] = 1;
                }
            } else if ((cmd == 'c' || cmd == 'C') && def) {
                int tokens = st.countTokens();
                if (tokens == 2) {
                    int u = Integer.parseInt(st.nextToken()) - 1;
                    int v = Integer.parseInt(st.nextToken()) - 1;
                    if (isValid(u, v, t)) union(u, v, p, s);
                } else if (tokens == 3) {
                    int u = Integer.parseInt(st.nextToken()) - 1;
                    int v = Integer.parseInt(st.nextToken()) - 1;
                    int c = Integer.parseInt(st.nextToken());
                    for (int i = v; i < v + c; i++) {
                        if (isValid(u, i, t)) union(u, i, p, s);
                    }
                } else if (tokens == 4) {
                    int u = Integer.parseInt(st.nextToken()) - 1;
                    int v = Integer.parseInt(st.nextToken()) - 1;
                    int c = Integer.parseInt(st.nextToken());
                    int step = Integer.parseInt(st.nextToken());
                    for (int i = v; i < v + c; i += step) {
                        if (isValid(u, i, t)) union(u, i, p, s);
                    }
                } else {
                    int u = Integer.parseInt(st.nextToken()) - 1;
                    int v = Integer.parseInt(st.nextToken()) - 1;
                    int c = Integer.parseInt(st.nextToken());
                    int dststep = Integer.parseInt(st.nextToken());
                    int srcstep = Integer.parseInt(st.nextToken());
                    while (c-- > 0) {
                        if (isValid(u, v, t)) union(u, v, p, s);
                        u += srcstep;
                        v += dststep;
                    }
                }
            } else if (def) {
                int tokens = st.countTokens();
                int con = 0;
                int ncon = 0;
                if (tokens == 2) {
                    int u = Integer.parseInt(st.nextToken()) - 1;
                    int v = Integer.parseInt(st.nextToken()) - 1;
                    if (isValid(u, v, t) && isEqual(u, v, p)) con++;
                    else ncon++;
                } else if (tokens == 3) {
                    int u = Integer.parseInt(st.nextToken()) - 1;
                    int v = Integer.parseInt(st.nextToken()) - 1;
                    int c = Integer.parseInt(st.nextToken());
                    for (int i = v; i < v + c; i++) {
                        if (isValid(u, i, t) && isEqual(u, i, p)) con++;
                        else ncon++;
                    }
                } else if (tokens == 4) {
                    int u = Integer.parseInt(st.nextToken()) - 1;
                    int v = Integer.parseInt(st.nextToken()) - 1;
                    int c = Integer.parseInt(st.nextToken());
                    int step = Integer.parseInt(st.nextToken());
                    for (int i = v; i < v + c; i += step) {
                        if (isValid(u, i, t) && isEqual(u, i, p)) con++;
                        else ncon++;
                    }
                } else {
                    int u = Integer.parseInt(st.nextToken()) - 1;
                    int v = Integer.parseInt(st.nextToken()) - 1;
                    int c = Integer.parseInt(st.nextToken());
                    int dststep = Integer.parseInt(st.nextToken());
                    int srcstep = Integer.parseInt(st.nextToken());
                    while (c-- > 0) {
                        if (isValid(u, v, t) && isEqual(u, v, p)) con++;
                        else ncon++;
                        u += srcstep;
                        v += dststep;
                    }
                }
                out.append(con).append(" - ").append(ncon).append('\n');
            } else {
                int ncon = 0;
                int tokens = st.countTokens();
                if (tokens == 2) ncon++;
                else {
                    st.nextToken();
                    st.nextToken();
                    ncon = Integer.parseInt(st.nextToken());
                }
                out.append(0).append(" - ").append(ncon).append('\n');
            }
        }
        System.out.print(out);
    }

    static void union(int a, int b, int[] p, int[] s) {
        int ra = find(a, p), rb = find(b, p);
        if (ra == rb) return;
        if (s[ra] > s[rb]) {
            s[ra] += s[rb];
            p[rb] = ra;
        } else {
            s[rb] += s[ra];
            p[ra] = rb;
        }
    }

    static int find(int x, int[] p) {
        int r = x;
        while (r != p[r]) r = p[r];
        while (x != r) {
            int t = p[x];
            p[x] = r;
            x = t;
        }
        return r;
    }

    static boolean isEqual(int u, int v, int[] p) {
        return find(u, p) == find(v, p);
    }

    static boolean isValid(int u, int v, int t) {
        return u >= 0 && v >= 0 && u < t && v < t;
    }
}
