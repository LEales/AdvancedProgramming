import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Yuckdonald {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] m = new int[st.countTokens()];
        for (int i = 0; i < m.length; i++) m[i] = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> memo = new HashMap<>();
        System.out.println(solve(memo, 0, k, m));
    }

    static int solve(HashMap<Integer, Integer> memo, int i, int k, int[] m) {
        if (memo.containsKey(i)) return memo.get(i);
        if (i >= m.length) return 0;
        int max;
        if (i + k >= m.length)
            max = Math.max(m[i], solve(memo, i + 1, k, m));
        else
            max = Math.max(m[i] + solve(memo, i + k, k, m), solve(memo, i + 1, k, m));
        memo.put(i, max);
        return max;
    }
}
