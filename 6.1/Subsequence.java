import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Subsequence {

//  5 15 -30 10 -5 40 10

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[st.countTokens()];
        for (int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[] dp = new int[arr.length];
        int max = 0, maxI = 0;
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            if (dp[i] > max) {
                max = dp[i];
                maxI = i;
            }
        }
        int i = maxI;
        while (i >= 0 && dp[i] > 0) i--;
        System.out.println("Sum is " + Math.max(0, max));
        if (i == maxI) System.out.println("Empty sequence is largest");
        for (++i; i <= maxI; i++) System.out.print(arr[i] + " ");
    }
}
