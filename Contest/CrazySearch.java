import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
//problem 1123
public class CrazySearch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            String in = br.readLine();
            HashSet<String> set = new HashSet<>();
            for (int i = 0; i < in.length()-length+1; i++) set.add(in.substring(i, i+length));
            out.append(set.size()).append('\n');
            if (N != 0) out.append('\n');
        }
        System.out.print(out);
    }
}
