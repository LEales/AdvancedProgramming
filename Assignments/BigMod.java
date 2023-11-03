package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
Calculate the mod for base^1 and continue to square it
b^p can then be calculated by multiplying the binary representation
 */

class BigMod {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String in;
        while ((in = br.readLine()) != null) {
            int b = Integer.parseInt(in), p = Integer.parseInt(br.readLine()), m = Integer.parseInt(br.readLine());
            br.readLine();
            String bin = Integer.toBinaryString(p);
            int[] mods = new int[bin.length()];
            mods[0] = b % m;
            for (int i = 1; i < bin.length(); i++) mods[i] = (mods[i - 1] * mods[i - 1]) % m;
            int ans = 1;
            for (int i = 0; i < bin.length(); i++)
                if (bin.charAt(bin.length() - i - 1) == '1') ans = (ans * mods[i]) % m;
            out.append(ans).append("\n");
        }
        System.out.print(out);
    }
}
