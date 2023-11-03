package contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//problem 1127
public class WordPuzzle {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken()), W = Integer.parseInt(st.nextToken());
            char[][] board = new char[R][C];
            for (int i = 0; i < R; i++) board[i] = br.readLine().toCharArray();
            for (int i = 0; i < W; i++) {
                Return r = solve(board, br.readLine(), R, C);
                out.append(r.R).append(' ').append(r.C).append(' ').append(r.D).append('\n');
            }
            if (N != 0) out.append('\n');
        }
        System.out.print(out);
    }
    static Return solve(char[][] board, String word, int R, int C) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (testN(board, word, R, C, i, j)) return new Return(i, j, 'A');
                    if (testNE(board, word, R, C, i, j)) return new Return(i, j, 'B');
                    if (testE(board, word, R, C, i, j)) return new Return(i, j, 'C');
                    if (testSE(board, word, R, C, i, j)) return new Return(i, j, 'D');
                    if (testS(board, word, R, C, i, j)) return new Return(i, j, 'E');
                    if (testSW(board, word, R, C, i, j)) return new Return(i, j, 'F');
                    if (testW(board, word, R, C, i, j)) return new Return(i, j, 'G');
                    if (testNW(board, word, R, C, i, j)) return new Return(i, j, 'H');
                }
            }
        }
        return null;
    }

    static boolean testN(char[][] board, String word, int R, int C, int i, int j) {
        int pos = 1;
        for (int k = i-1; k >= 0; k--) {
            if (board[k][j] != word.charAt(pos++)) return false;
            if (pos == word.length()) return true;
        }
        return false;
    }
    static boolean testE(char[][] board, String word, int R, int C, int i, int j) {
        int pos = 1;
        for (int k = j+1; k < C; k++) {
            if (board[i][k] != word.charAt(pos++)) return false;
            if (pos == word.length()) return true;
        }
        return false;
    }

    static boolean testS(char[][] board, String word, int R, int C, int i, int j) {
        int pos = 1;
        for (int k = i+1; k < R; k++) {
            if (board[k][j] != word.charAt(pos++)) return false;
            if (pos == word.length()) return true;
        }
        return false;
    }
    static boolean testW(char[][] board, String word, int R, int C, int i, int j) {
        int pos = 1;
        for (int k = j-1; k >= 0; k--) {
            if (board[i][k] != word.charAt(pos++)) return false;
            if (pos == word.length()) return true;
        }
        return false;
    }

    static boolean testNE(char[][] board, String word, int R, int C, int i, int j) {
        int pos = 1;
        while (--i >= 0 && ++j < C) {
            if (board[i][j] != word.charAt(pos++)) return false;
            if (pos == word.length()) return true;
        }
        return false;
    }
    static boolean testSE(char[][] board, String word, int R, int C, int i, int j) {
        int pos = 1;
        while (++i < R && ++j < C) {
            if (board[i][j] != word.charAt(pos++)) return false;
            if (pos == word.length()) return true;
        }
        return false;
    }

    static boolean testNW(char[][] board, String word, int R, int C, int i, int j) {
        int pos = 1;
        while (--i >= 0 && --j >= 0) {
            if (board[i][j] != word.charAt(pos++)) return false;
            if (pos == word.length()) return true;
        }
        return false;
    }
    static boolean testSW(char[][] board, String word, int R, int C, int i, int j) {
        int pos = 1;
        while (++i < R && --j >= 0) {
            if (board[i][j] != word.charAt(pos++)) return false;
            if (pos == word.length()) return true;
        }
        return false;
    }

    static class Return {
        int R, C;
        char D;

        public Return(int r, int c, char d) {
            R = r;
            C = c;
            D = d;
        }
    }
}
