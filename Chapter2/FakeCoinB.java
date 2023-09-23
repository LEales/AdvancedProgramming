package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FakeCoinB {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = st.countTokens();
        int[] pile = new int[n];
        for (int i = 0; i < n; i++) pile[i] = Integer.parseInt(st.nextToken());
        System.out.println(solve(pile));
    }

    static int solve(int[] pile) {
        int n = pile.length;
        int mod = n % 3;
        if (mod == 0) {
            int div = n / 3;
            int[] info = threeCompare(pile, 0, div, div + div, n);
            if (info[0] == 1) return solveMax(pile, info[1], info[2]);
            else return solveMin(pile, info[1], info[2]);
        } else if (mod == 1) {
            int div = --n / 3;
            int[] info = threeCompare(pile, 0, div, div + div, n);
            if (info[0] == 1) return solveMax(pile, info[1], info[2]);
            else if (info[0] == -1) return solveMin(pile, info[1], info[2]);
            else return n;
        } else {
            n -= 2;
            int div = n / 3;
            int[] info = threeCompare(pile, 0, div, div + div, n);
            if (info[0] == 1) return solveMax(pile, info[1], info[2]);
            else if (info[0] == -1) return solveMax(pile, info[1], info[2]);
            else if (pile[0] == pile[n + 1]) return n;
            else return ++n;
        }
    }

    static int solveMax(int[] pile, int l, int r) {
        //T(N) = T(N/2) + O(1) = O(log(N))
        if (l == r) return l;
        if ((r - l) % 2 == 0) {
            int mid = (l + r) / 2;
            if (compare(pile, l, mid, mid, r) > 0) return solveMax(pile, l, mid);
            else return solveMax(pile, mid, r);
        } else {
            int mid = (l + --r) / 2;
            int compare = compare(pile, l, mid, mid, r);
            if (compare > 0) return solveMax(pile, l, mid);
            else if (compare < 0) return solveMax(pile, mid, r);
            else return r;
        }
    }

    static int solveMin(int[] pile, int l, int r) {
        //T(N) = T(N/2) + O(1) = O(log(N))
        if (l == r) return l;
        if ((r - l) % 2 == 0) {
            int mid = (l + r) / 2;
            if (compare(pile, l, mid, mid, r) < 0) return solveMin(pile, l, mid);
            else return solveMin(pile, mid, r);
        } else {
            int mid = (l + --r) / 2;
            int compare = compare(pile, l, mid, mid, r);
            if (compare < 0) return solveMin(pile, l, mid);
            else if (compare > 0) return solveMin(pile, mid, r);
            else return r;
        }
    }

    static int compare(int[] pile, int fl, int fr, int sl, int sr) {
        //pretend this method is O(1) as stated in the problem
        int lSum = 0, rSum = 0;
        for (int i = fl; i < fr; i++) lSum += pile[i];
        for (int i = sl; i < sr; i++) rSum += pile[i];
        return Integer.compare(lSum, rSum);
    }

    static int[] threeCompare(int[] pile, int s, int mid, int e, int fin) {
        int first = 0, second = 0, third = 0;
        for (int i = s; i < mid; i++) first += pile[i];
        for (int i = mid; i < e; i++) second += pile[i];
        for (int i = e; i < fin; i++) third += pile[i];
        if (first == second && third == first) return new int[]{0, -1, -1};
        if (first == second) return new int[]{(third > first) ? 1 : -1, e, fin};
        else if (first == third) return new int[]{(second > first) ? 1 : -1, mid, e};
        else return new int[]{(first > second) ? 1 : -1, s, mid};
    }
}
