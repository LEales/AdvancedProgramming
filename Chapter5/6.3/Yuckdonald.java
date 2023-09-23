import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Yuckdonald {
    /*
    fist line should contain the integer k
    the second line should contain the integers M1 M2 ... Mn separeted by a space
    Example input 1:
    2
    1 5 3 1 9 6 7 8

    Example input 2:
    3
    1 5 3 1 9 6 7 8

    Example input 3:
    7
    1 5 3 1 9 6 7 8 10 12
    
     */
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
        if (i >= m.length) return 0;
        if (memo.containsKey(i)) return memo.get(i);
        int max = Math.max(m[i] + solve(memo, i + k, k, m), solve(memo, i + 1, k, m));
        memo.put(i, max);
        return max;
    }
}
