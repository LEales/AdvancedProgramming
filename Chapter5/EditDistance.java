package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class EditDistance {
    static String a, b;
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine(); b = br.readLine();
        n = a.length(); m = b.length();
        HashMap<String, Info> memo = new HashMap<>();
        Info result = solve(memo, 0, 0);
        System.out.println(result.c);
        System.out.println(result.a);
        System.out.println(result.b);
    }

    static Info solve(HashMap<String, Info> memo, int i, int j) {
        String key = i + " " + j;
        if (memo.containsKey(key)) return memo.get(key);
        if (i == n) {
            Info result = new Info();
            for (int k = j; k < m; k++) {
                result.c++;
                result.a.append('_');
                result.b.append(b.charAt(k));
            }
            memo.put(key, result);
            return result;
        }
        if (j == m) {
            Info result = new Info();
            for (int k = i; k < n; k++) {
                result.c++;
                result.b.append('_');
                result.a.append(a.charAt(k));
            }
            memo.put(key, result);
            return result;
        }
        Info result = new Info();
        Info del = solve(memo, i + 1, j);
        Info add = solve(memo, i, j + 1);
        Info other = solve(memo, i + 1, j + 1);
        char A = a.charAt(i), B = b.charAt(j);
        int delC = del.c + 1, addC = add.c + 1, otherC = other.c + ((A == B) ? 0 : 2);
        if (delC < addC && delC < otherC) {
            result.b.append('_').append(del.b);
            result.a.append(A).append(del.a);
            result.c = delC;
        } else if (addC < otherC) {
            result.a.append('_').append(add.a);
            result.b.append(B).append(add.b);
            result.c = addC;
        } else if (A == B) {
            result.a.append(A).append(other.a);
            result.b.append(B).append(other.b);
            result.c = otherC;
        } else {
            result.a.append('_').append(A).append(other.a);
            result.b.append(B).append('_').append(other.b);
            result.c = otherC;
        }
        memo.put(key, result);
        return result;
    }

    static class Info {
        StringBuilder a, b;
        int c;

        public Info() {
            a = new StringBuilder();
            b = new StringBuilder();
            c = 0;
        }
    }
}
