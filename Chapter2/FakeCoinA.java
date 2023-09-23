import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FakeCoinA {
    // zero is fake coin
    // 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
    // 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0
    // 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
    // 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0
    // 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
    // 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = st.countTokens();
        int[] pile = new int[n];
        for (int i = 0; i < n; i++) pile[i] = Integer.parseInt(st.nextToken());
        System.out.println(solve(pile, 0, n));
    }

    static int solve(int[] pile, int l, int r) {
        //T(N) = T(N/2) + O(1) = O(log(N))
        if (l == r) return l;
        if ((r - l) % 2 == 0) {
            int mid = (l + r) / 2;
            if (compare(pile, l, mid, mid, r) < 0) return solve(pile, l, mid);
            else return solve(pile, mid, r);
        } else {
            int mid = (l + --r) / 2;
            int compare = compare(pile, l, mid, mid, r);
            if (compare < 0) return solve(pile, l, mid);
            else if (compare > 0) return solve(pile, mid, r);
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
}
