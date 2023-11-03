package contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;
//impossible to read input without runtime error, wonderful problem
//problem 1125
public class SherlockHolmes {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String in = br.readLine();
        while (in == null) in = br.readLine();
        StringTokenizer st = new StringTokenizer(in);
        while (true) {
            int c = 0;
            boolean bre = false;
            while (!st.hasMoreElements()) {
                in = br.readLine();
                if (in == null) {
                    if (c == 5) {
                        bre = true;
                        break;
                    }
                    else {
                        c++;
                        continue;
                    }
                }
                st = new StringTokenizer(in);
            }
            if (bre) break;
            int boxes = Integer.parseInt(st.nextToken());
            while (!st.hasMoreElements()) {
                in = br.readLine();
                if (in == null) continue;
                st = new StringTokenizer(in);
            }
            int balls = Integer.parseInt(st.nextToken());
            if (boxes <= 0 || balls <= 0 || boxes % 2 == 1) {
                out.append("No solution").append('\n');
                continue;
            }
            int total = 0;
            ArrayList<P> diff = new ArrayList<>();
            for (int i = 0; i < boxes; i++) {
                while (!st.hasMoreElements()) {
                    in = br.readLine();
                    if (in == null) continue;
                    st = new StringTokenizer(in);
                }
                int white = Integer.parseInt(st.nextToken());
                while (!st.hasMoreElements()) {
                    in = br.readLine();
                    if (in == null) continue;
                    st = new StringTokenizer(in);
                }
                int black = Integer.parseInt(st.nextToken());
                total += white - black;
                diff.add(new P(white, black, white - black));
            }
            diff.sort(Comparator.comparingInt(o -> o.diff));
            if (total == 0) {
                out.append("No solution").append('\n');
            } else if (total > 0) {
                int set1 = 0, set2 = 0;
                set1 += diff.get(0).w;
                boolean first = false;
                for (int i = 1; i < boxes; i += 2) {
                    if (first) {
                        set1 += diff.get(i).w;
                        if (i + 1 < boxes) set1 += diff.get(i + 1).w;
                    } else {
                        set2 += diff.get(i).w;
                        set2 += diff.get(i + 1).w;
                    }
                    first = !first;
                }
                double inSet = (boxes / 2.d) * balls;
                double sol = Math.min(set1 / inSet * 100, set2 / inSet * 100);
                if (sol <= 50) out.append("No solution").append('\n');
                else out.append("W ").append(sol).append('\n');
            } else {
                int set1 = 0, set2 = 0;
                set1 += diff.get(0).b;
                boolean first = false;
                for (int i = 1; i < boxes; i += 2) {
                    if (first) {
                        set1 += diff.get(i).b;
                        if (i + 1 < boxes) set1 += diff.get(i + 1).b;
                    } else {
                        set2 += diff.get(i).b;
                        set2 += diff.get(i + 1).b;
                    }
                    first = !first;
                }
                double inSet = (boxes / 2.d) * balls;
                double sol = Math.min(set1 / inSet * 100, set2 / inSet * 100);
                if (sol <= 50) out.append("No solution").append('\n');
                else out.append("B ").append(sol).append('\n');
            }
        }
        System.out.print(out);
    }

    static class P {
        int w, b, diff;

        public P(int w, int b, int diff) {
            this.w = w;
            this.b = b;
            this.diff = diff;
        }

    }
}
