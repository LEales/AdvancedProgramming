import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
Keep track of how many positions are wrong in total at each step and add it to the total
 */
class WineTradingInGergovia {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in;
        StringBuilder out = new StringBuilder();
        while (!(in = br.readLine()).equals("0")) {
            int n = Integer.parseInt(in);
            StringTokenizer st = new StringTokenizer(br.readLine());
            long o = 0, p = 0;
            for (int i = 0; i < n; i++) {
                p += Integer.parseInt(st.nextToken());
                o += Math.abs(p);
            }
            out.append(o).append("\n");
        }
        System.out.print(out);
    }
}
