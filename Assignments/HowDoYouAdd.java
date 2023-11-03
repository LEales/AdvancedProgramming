import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
Calculated first numbers by hand and found the pattern.
dp[i][j] = dp[i-1][j] + dp[i][j-1]
 */
public class HowDoYouAdd {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String in;
        int[][] dp = new int[100][100];
        for (int i = 0; i < 100; i++) dp[0][i] = i + 1;
        for (int i = 0; i < 100; i++) dp[i][0] = 1;
        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                dp[i][j] %= 1000000;
            }
        }
        while (!(in = br.readLine()).equals("0 0")) {
            StringTokenizer st = new StringTokenizer(in);
            int n = Integer.parseInt(st.nextToken()) - 1, k = Integer.parseInt(st.nextToken()) - 1;
            out.append(dp[n][k]).append("\n");
        }
        System.out.print(out);
    }
}
