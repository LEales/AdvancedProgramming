import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NonTrivialPower {
    //Bengt solved this with a quicker algorithm in class, but I cannot remember it.
    //To handle larger numbers exchange doubles for BigDecimal.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double n = Double.parseDouble(br.readLine());
        int maxA = (int) Math.sqrt(n);
        int maxK = (int) (Math.log(n) / Math.log(2)), minK = 2;

        for (int i = 2; i <= maxA; i++) {
            int r = binSearch(i, minK, maxK, n);
            if (r != -1) {
                System.out.println(i + "^" + r);
                System.exit(0);
            }
        }
        System.out.println("no");
    }

    static int binSearch(int a, int minK, int maxK, double n) {
        while (minK <= maxK) {
            int mid = (minK + maxK) / 2;
            double r = Math.pow(a, mid);
            if (r > n) maxK = mid - 1;
            else if (r < n) minK = mid + 1;
            else return mid;
        }
        return -1;
    }
}
