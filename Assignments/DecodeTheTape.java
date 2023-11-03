import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
Calculate ascii value of the binary tape.
 */
class DecodeTheTape {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String in;
        StringBuilder out = new StringBuilder();
        while (!(in = br.readLine()).equals("___________")) {
            int c = 0;
            char[] arr = new char[8];
            for (int i = 0; i < in.length(); i++) {
                if (in.charAt(i) == ' ') arr[c++] = '0';
                else if (in.charAt(i) == 'o') arr[c++] = '1';
            }
            out.append(((char) Integer.parseInt(new String(arr), 2)));
        }
        System.out.print(out);
    }
}
