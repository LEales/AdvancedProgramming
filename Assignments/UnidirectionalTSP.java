package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
/*
From right to left keep track of minimum cost to get here.
Also keep track of what row to move to next from the current node.
 */
class UnidirectionalTSP {
    static int[][] matrix;
    static int[][] path;
    static HashMap<String, Integer> mem = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String in;
        while ((in = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(in);
            int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());

            matrix = new int[r][c];
            path = new int[r][c];
            mem.clear();
            int cI = 0;
            while (cI != r * c) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    matrix[cI / c][cI % c] = Integer.parseInt(st.nextToken());
                    cI++;
                }
            }

            int min = Integer.MAX_VALUE;
            int minI = -1;
            for (int i = 0; i < r; i++) {
                int curr = solve(i, 0);
                if (min > curr) {
                    min = curr;
                    minI = i;
                }
            }
            out.append(minI + 1);
            for (int i = 0; i < c - 1; i++) {
                minI = path[minI][i];
                out.append(" ").append(minI + 1);
            }
            out.append("\n").append(min).append("\n");
        }
        System.out.print(out);
    }


    static int solve(int r, int c) {
        if (mem.containsKey(r + " " + c)) return mem.get(r + " " + c);
        if (c == matrix[0].length) return 0;

        int minus = (r - 1 + matrix.length) % matrix.length;
        int plus = (r + 1) % matrix.length;
        int x = solve(minus, c + 1);
        int y = solve(r, c + 1);
        int z = solve(plus, c + 1);
        int min = Math.min(x, Math.min(y, z));

        if (x == y && y == z) path[r][c] = Math.min(minus, Math.min(r, plus));
        else if (x == y && x == min) path[r][c] = Math.min(minus, r);
        else if (x == z && x == min) path[r][c] = Math.min(minus, plus);
        else if (z == y && z == min) path[r][c] = Math.min(plus, r);
        else if (x == min) path[r][c] = minus;
        else if (y == min) path[r][c] = r;
        else path[r][c] = plus;

        mem.put(r + " " + c, min + matrix[r][c]);
        return min + matrix[r][c];
    }
}