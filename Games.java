package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Games {
    /*
    For the factor solution the denominator long variable will overflow if n-i > 32 || n-j > 32 unless i == j (change to BigDecimal to fix, but it will be slower)
    Needs accurate testing to find out if this code is accurate
    Stack will also explode for relatively small N
     */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int i = Integer.parseInt(st.nextToken()), j = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
        HashMap<String, Fraction> memo = new HashMap<>();
        HashMap<String, Double> memo2 = new HashMap<>();
        double result = solveDecimal(memo2, n-i, n-j);
        System.out.println((result < 0) ? 0 : result);
        Fraction total = Games.solveFraction(memo, n - i, n - j);
        long win = total.n;
        long games = total.d;
        System.out.println(win);
        System.out.println("_____");
        System.out.println(games);
    }

    static double solveDecimal(HashMap<String, Double> memo, int i, int j) {
        if (i == 0) return 1;
        if (j == 0) return 0;
        if (i == j) return 0.5;
        String key = i + " " + j;
        if (memo.containsKey(key)) return memo.get(key);
        double win = solveDecimal(memo, i-1, j);
        double lose = solveDecimal(memo, i, j-1);
        double result = (win + lose) / 2;
        memo.put(key, result);
        return result;
    }

    static Fraction solveFraction(HashMap<String, Fraction> memo, int i, int j) {
        if (i == 0) return new Fraction(1, 1);
        if (j == 0) return new Fraction(0, 0);
        if (i == j) return new Fraction(1, 2);
        String key = i + " " + j;
        if (memo.containsKey(key)) return memo.get(key);
        Fraction win = Games.solveFraction(memo, i - 1, j);
        Fraction lose = Games.solveFraction(memo, i, j - 1);
        Fraction result = add(win, lose);
        result.d *= 2;
        while (result.n % 2 == 0) {
            result.n /= 2;
            result.d /= 2;
        }
        memo.put(key, result);
        return result;
    }
    static Fraction add(Fraction a, Fraction b) {
        long ad = a.d, bd = b.d;
        long an = a.n, bn = b.n;
        if (bd == 0 && ad == 0) {
            System.out.println("overflow");
            System.exit(0);
        }
        if (bd == 0) return new Fraction(an, ad);
        if (ad == 0) return new Fraction(bn, bd);
        while (bd > ad) {
            if (ad <= 0 || bd <= 0) {
                System.out.println("overflow");
                System.exit(0);
            }
            ad*=2;
            an*=2;
        }
        while (ad > bd) {
            if (bd <= 0 || ad <= 0) {
                System.out.println("overflow");
                System.exit(0);
            }
            bd *= 2;
            bn *= 2;
        }
        return new Fraction(an + bn, bd);
    }

    static class Fraction {
        long d, n;

        public Fraction(long n, long d) {
            this.d = d;
            this.n = n;
        }
    }
}
