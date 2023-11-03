import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
If a char in the pattern is found increment pattern.
If the entire pattern is gone through print yes otherwise no
 */

class AllInAll {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String in;
        while ((in = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(in);
            String s = st.nextToken(), t = st.nextToken();
            int i = 0, j = 0;
            while (i < s.length() && j < t.length()) {
                if (s.charAt(i) == t.charAt(j)) i++;
                j++;
            }
            out.append(i == s.length() ? "Yes" : "No").append("\n");
        }
        System.out.print(out);
    }
}
