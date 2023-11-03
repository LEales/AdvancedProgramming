import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//problem 1121
public class Subsequence {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String in;
        while ((in = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(in);
            int N = Integer.parseInt(st.nextToken()), S = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
            int total = 0, last, first = 0;
            for (last = 0; last < N; last++) {
                total += arr[last];
                if (total >= S) break;
            }
            if (total < S) {
                out.append(0).append('\n');
                continue;
            }
            int min = ++last - first;
            while (last < N || total >= S) {
                if (last == N || total >= S) total -= arr[first++];
                else total += arr[last++];
                if (total >= S) min = Math.min(min, last - first);
            }
            out.append(min).append('\n');
        }
        System.out.print(out);
    }
}
