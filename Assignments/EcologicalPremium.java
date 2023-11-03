import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
area * env ....
 */
public class EcologicalPremium {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();
        while (N-- > 0) {
            int F = Integer.parseInt(br.readLine());
            int o = 0;
            while (F-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int area = Integer.parseInt(st.nextToken());
                st.nextToken();
                int env = Integer.parseInt(st.nextToken());
                o += area * env;
            }
            out.append(o).append("\n");
        }
        System.out.print(out);
    }
}
