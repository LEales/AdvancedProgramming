package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*
Plane Sweep Closest Pair as shown in lecture closest pair
 */
class ClosestPair {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int N;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            Pair[] S = new Pair[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                S[i] = new Pair(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            }
            if (N < 2) {
                out.append("INFINITY\n");
                continue;
            }
            Arrays.sort(S, Comparator.comparingDouble(o -> o.x));
            TreeSet<Pair> T = new TreeSet<>(Comparator.comparingDouble(o -> o.y));
            Pair p = S[0], q = S[1];
            double d = dist(p, q);
            int pointer = 0;
            T.add(p); T.add(q);
            for (int i = 2; i < N; i++) {
                Pair s = S[i], u = S[pointer];
                while (pointer < i && u.x <= s.x - d) {
                    T.remove(u);
                    u = S[++pointer];
                }
                for (Pair t : T.subSet(new Pair(0, s.y - d), true, new Pair(0, s.y + d), true))
                    d = Math.min(d, dist(s, t));
                T.add(s);
            }
            if (d >= 10000) out.append("INFINITY\n");
            else out.append(String.format("%.4f", d)).append("\n");
        }
        System.out.print(out);
    }
    static double dist(Pair o1, Pair o2) {
        return Math.sqrt((o1.x - o2.x) * (o1.x - o2.x) + (o1.y - o2.y) * (o1.y - o2.y));
    }
    static class Pair {
        double x, y;

        public Pair(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
