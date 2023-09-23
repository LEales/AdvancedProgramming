import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class MajorityElement {
    //assuming array is filled with positive integers
    //O(N) divide and conquer solution not figured out yet

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());

        int divA = divideA(a, 0, n - 1);
        int hash = hashSolution(a);
        System.out.println((divA == Integer.MAX_VALUE) ? "no solution" : divA);
        System.out.println((hash == Integer.MAX_VALUE) ? "no solution" : hash);
    }

    static int divideA(int[] a, int s, int e) {
        //T(N) = 2T(N/2) + O(N) = O(N log (N)) master theorem
        if (s == e) return a[s];
        int mid = (s + e) / 2;
        int fHalf = divideA(a, s, mid);
        int sHalf = divideA(a, mid + 1, e);
        int fCount = 0, sCount = 0;
        for (int i = s; i <= e; i++) {
            if (a[i] == sHalf) sCount++;
            if (a[i] == fHalf) fCount++;
        }
        if (sCount > (e - s + 1) / 2) return sHalf;
        if (fCount > (e - s + 1) / 2) return fHalf;
        return Integer.MAX_VALUE;
    }

    static int hashSolution(int[] a) {
        //O(N) solution (hashing might be cheating?)
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : a)
            map.put(i, map.getOrDefault(i, 0) + 1);

        for (int i : map.keySet())
            if (map.get(i) > a.length / 2) return i;

        return Integer.MAX_VALUE;
    }
}
