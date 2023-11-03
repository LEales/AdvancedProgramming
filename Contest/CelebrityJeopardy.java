import java.io.BufferedReader;
import java.io.InputStreamReader;
//Problem 1124
public class CelebrityJeopardy {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in;
        StringBuilder out = new StringBuilder();
        while ((in = br.readLine()) != null) out.append(in).append('\n');
        System.out.print(out);
    }
}
