package ol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = aLotOfPretend(); //randomizes a sorted array with random size and input
        /*
        if you cant be bothered trying a bunch of different values
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + " ");
        }
        System.out.println();
         */
        int n = findN(input); //finds size of array
        int in;
        while ((in = Integer.parseInt(br.readLine())) != -1) {
            int o = binarySearch(input, n, in);
            if (o == -1) {
                for (int i = 0; i < n; i++) {
                    if (input[i] == in) System.out.println("You suck at programming"); //if my binary search misses a value;
                }
                System.out.println(-1);
            } else System.out.println(o);
        }
    }

    static int binarySearch(int[] arr, int n, int t) {
        //O(log(n))
        int min = 0, max = n - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            int v = arr[mid];
            if (v == t) return mid;
            else if (v < t) min = mid + 1;
            else max = mid - 1;
        }
        return -1;
    }

    static int findN(int[] arr) {
        int max = 1;
        int min = max;
        //O(log(n))
        while (access(arr, max) != Integer.MAX_VALUE) {
            min = max;
            max *= 2;
        }
        min++;
        max--;
        //O(log(n))
        while (min <= max) {
            int mid = (min + max) / 2;
            if (access(arr, mid) == Integer.MAX_VALUE) max = mid - 1;
            else min = mid + 1;
        }
        return min;
        //total O(2 log (n)) = O(log(n))
    }

    //pretend access function to simulate conditions of the problem
    static int access(int[] arr, int i) {
        return (i >= arr.length) ? Integer.MAX_VALUE : arr[i];
    }

    static int[] aLotOfPretend() {
        SecureRandom r = new SecureRandom();
        int n = r.nextInt(1000) + 100;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(10000);
        }
        Arrays.sort(arr);
        return arr;
    }
}
