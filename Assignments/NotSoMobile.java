package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
Recursively run calculate the weights and check if it's correct.
 */
class NotSoMobile {
    static BufferedReader br;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            while (!br.readLine().isEmpty());
            if (solve() == -1) out.append("NO").append("\n\n");
            else out.append("YES").append("\n\n");
        }
        out.replace(out.length() - 1, out.length(), "");
        System.out.print(out);
    }

    static int solve() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int wl = Integer.parseInt(st.nextToken()), dl = Integer.parseInt(st.nextToken()), wr = Integer.parseInt(st.nextToken()), dr = Integer.parseInt(st.nextToken());
        if (wl == 0) wl = solve();
        if (wl == -1) return -1;
        if (wr == 0) wr = solve();
        if (wr == -1) return -1;
        if (wl * dl == wr * dr) return wl + wr;
        return -1;
    }
}

