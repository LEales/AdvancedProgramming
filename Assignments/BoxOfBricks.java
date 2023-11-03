package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
Find avg tower height.
Add the difference between all the blocks over the average height and the average height.
 */
class BoxOfBricks {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();
        int count = 0;
        while (N != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            int sum = 0;
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum += arr[i];
            }
            sum /= N;
            int moves = 0;
            for (int i = 0; i < N; i++)
                if (arr[i] > sum) moves += arr[i] - sum;
            out.append("Set #").append(++count).append("\n").append("The minimum number of moves is ").append(moves).append(".\n\n");
            N = Integer.parseInt(br.readLine());
        }
        System.out.print(out);
    }
}
