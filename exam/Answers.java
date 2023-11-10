package ol.exam;

import java.util.Arrays;

public class Answers {

    public static void main(String[] args) {
        int[] S = new int[]{5, 2, 8, 6, 3, 6, 9, 7};
        float[] W = new float[]{1, 2, 3, 1, 2, 3, 2, 1};
        System.out.println(question3(S, W, 8));
    }

    static boolean question1(float[] arr, float b) {
        Arrays.sort(arr);
        int L = 0, R = arr.length - 1;
        while (R > L) {
            float sum = arr[R] + arr[L];
            if (sum == b) return true;
            else if (sum > b) R--;
            else L++;
        }
        return false;
    }

    static Contestant[] question2(Contestant[] contestants) {
        Arrays.sort(contestants); //check comparator in contestant class
        return contestants;
    }

    static float question3(int[] S, float[] W, int N) {
        float[] dp = new float[N];
        for (int i = N - 1; i >= 0; i--) {
            dp[i] = W[i];
            float max = 0;
            for (int j = i + 1; j < N; j++) {
                if (S[j] > S[i] && max < dp[j]) {
                    max = dp[j];
                }
            }
            dp[i] += max;
        }
        float max = Float.MIN_VALUE;
        for (float f : dp) max = Math.max(max, f);
        return max;
    }

    static Tree kruskal(Graph g) {
        return null;
    }

    static Tree question4(Graph G) {
        Tree MST = kruskal(G);
        Tree T2;
        int T2cost;
        for (Edge e : G.edges) {
            if (MST.contains(e)) continue;
            Graph Gp = MST + e;
            Edge[] cycle = getCycle(Gp);
            Edge min;
            for (Edge ep : cycle) {
                if (ep == e) continue;
                ;
                if (w(min) < w(ep)) min = ep;
            }
            int cost = MST.cost + w(e) - w(min);
            if (cost < T2cost) {
                T2 = MST - min + e;
                T2cost = cost;
            }
        }
        return T2;
    }

    static boolean Question5Partition(int[] A) {
        int total = 0;
        for (int a : A) total += a;
        if (total % 2 == 0) return subsetsum(A, total /2);
        else return false;
    }

    static boolean subsetsum(int[] a, int i) {
        return false;
    }

    static int w(Edge e) {
        return 0;
    }

    static Edge[] getCycle(Graph g) {
        return null;
    }

    static class Tree {

    }

    static class Graph {
        Edge[] edges;
    }

    static class Edge {

    }

    static class Contestant implements Comparable<Contestant> {
        int swim, run, bike, id;

        @Override
        public int compareTo(Contestant o) {
            return Integer.compare(o.bike + o.run, bike + run);
        }
    }
}
