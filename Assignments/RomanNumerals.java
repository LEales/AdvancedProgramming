package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

class RomanNumerals {
    static int found = 0;
    static int[] tenPower;
    static HashMap<Character, Integer> romanMap = new HashMap<>();

    static char[] first, second, sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in;
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        tenPower = new int[9];
        for (int i = 0; i < 9; i++) tenPower[i] = (int) Math.pow(10, i);
        StringBuilder out = new StringBuilder();
        while (!(in = br.readLine()).equals("#")) {
            int plus = in.indexOf('+'), equals = in.indexOf('=');
            String romanF = in.substring(0, plus), romanSec = in.substring(plus + 1, equals), romanSum = in.substring(equals + 1);
            out.append(roman(romanF, romanSec, romanSum));
            out.append(arabic(romanF, romanSec, romanSum)).append("\n");
            found = 0;
        }
        System.out.print(out);
    }

    static String roman(String f, String s, String su) {
        return (romanInt(f) + romanInt(s) == romanInt(su)) ? "Correct " : "Incorrect ";
    }

    static int romanInt(String in) {
        int out = 0;
        int i;
        for (i = 0; i < in.length() - 1; i++) {
            int curr = romanMap.get(in.charAt(i));
            int next = romanMap.get(in.charAt(i + 1));
            if (next > curr) out -= curr;
            else out += curr;
        }
        return out + romanMap.get(in.charAt(i));
    }

    static String arabic(String f, String s, String su) {
        if (su.length() < s.length() || su.length() < f.length()) return "impossible";
        if (su.length() > s.length() + 1 && su.length() > f.length() + 1) return "impossible";
        first = new StringBuilder(f).reverse().toString().toCharArray();
        second = new StringBuilder(s).reverse().toString().toCharArray();
        sum = new StringBuilder(su).reverse().toString().toCharArray();
        ArrayList<Character> vari = new ArrayList<>();
        fill(vari);
        HashMap<Character, Integer> variMap = new HashMap<>();
        solve(0, vari, variMap, 0);
        if (found == 0) return "impossible";
        else if (found == 1) return "valid";
        else return "ambiguous";
    }

    static void fill(ArrayList<Character> list) {
        for (char c : first) if (!list.contains(c)) list.add(c);
        for (char c : second) if (!list.contains(c)) list.add(c);
        for (char c : sum) if (!list.contains(c)) list.add(c);
    }

    static void solve(int i, ArrayList<Character> vari, HashMap<Character, Integer> variMap, int mask) {
        if (i == vari.size()) {
            if (checkSol(variMap)) found++;
            return;
        }
        if (found >= 2) return;
        for (int j = 0; j < 10; j++) {
            if ((mask & 1 << j) > 0) continue;
            variMap.put(vari.get(i), j);
            int tempm = mask | 1 << j;
            if (check(variMap)) solve(i + 1, vari, variMap, tempm);
            variMap.remove(vari.get(i));
            if (found >= 2) return;
        }
    }


    static boolean checkSol(HashMap<Character, Integer> variMap) {
        int f = 0, s = 0, su = 0;
        for (int i = 0; i < first.length; i++) f += tenPower[i] * variMap.get(first[i]);
        for (int i = 0; i < second.length; i++) s += tenPower[i] * variMap.get(second[i]);
        for (int i = 0; i < sum.length; i++) su += tenPower[i] * variMap.get(sum[i]);
        return f + s == su;
    }

    static boolean check(HashMap<Character, Integer> variMap) {
        if (variMap.getOrDefault(first[first.length - 1], -1) == 0
                || variMap.getOrDefault(second[second.length - 1], -1) == 0
                || variMap.getOrDefault(sum[sum.length - 1], -1) == 0) return false;
        int i = 0;
        while (i < first.length && i < second.length) {
            if (!variMap.containsKey(first[i]) || !variMap.containsKey(second[i]) || !variMap.containsKey(sum[i])) {
                i++;
                continue;
            }
            int f = variMap.get(first[i]), s = variMap.get(second[i]), su = variMap.get(sum[i]);
            if ((f + s) % 10 == su || (f + s + 1) % 10 == su) i++;
            else return false;
        }
        while (i < first.length) {
            if (!variMap.containsKey(first[i]) || !variMap.containsKey(sum[i])) {
                i++;
                continue;
            }
            int f = variMap.get(first[i]), su = variMap.get(sum[i]);
            if (f == su || (f + 1) % 10 == su) i++;
            else return false;
        }
        while (i < second.length) {
            if (!variMap.containsKey(second[i]) || !variMap.containsKey(sum[i])) {
                i++;
                continue;
            }
            int s = variMap.get(second[i]), su = variMap.get(sum[i]);
            if (s == su || (s + 1) % 10 == su) i++;
            else return false;
        }
        return true;
    }
}
