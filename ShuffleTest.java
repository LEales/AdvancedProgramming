package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ShuffleTest {
    static String x, y, z;
    static int n, m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        x = br.readLine();
        y = br.readLine();
        z = br.readLine();
        n = x.length();
        m = y.length();
        HashMap<String, Boolean> memo = new HashMap<>();
        System.out.println(solve(memo, 0, 0));
    }


    static boolean solve(HashMap<String, Boolean> memo, int xi, int yi) {
        String key = xi + " " + yi;
        if (memo.containsKey(key)) return memo.get(key);
        if (xi == n) {
            for (int i = yi+xi; i < m; i++) {
                if (y.charAt(i) != z.charAt(i)) return false;
            }
            return true;
        }
        if (yi == m) {
            for (int i = yi+xi; i < n; i++) {
                if (x.charAt(i) != z.charAt(i)) return false;
            }
            return true;
        }
        char cz = z.charAt(xi+yi);
        char cx = x.charAt(xi);
        char cy = y.charAt(yi);
        if (cx == cz && cy == cz) {
            boolean solution = solve(memo,xi + 1, yi) || solve(memo, xi, yi + 1);
            memo.put(key, solution);
            return solution;
        } else if (cx == cz) {
            boolean solution = solve(memo, xi + 1, yi);
            memo.put(key, solution);
            return solution;
        } else if (cy == cz) {
            boolean solution = solve(memo, xi, yi + 1);
            memo.put(key, solution);
            return solution;
        }
        memo.put(key, false);
        return false;
    }
}
