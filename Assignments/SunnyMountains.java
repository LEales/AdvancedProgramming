import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
Keep track of the previous mountain peak and the previous node.
Calculate y value from previous peak
Calculate x value with intersection of the segment of the current point and the previous node.
Add the distance of this new point with the current point.
 */
class SunnyMountains {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(points);
            double s = 0;
            Point prev = points[0];
            Point curr = points[0];
            for (int i = 1; i < n; i++) {
                if (points[i].y > prev.y) {
                    double[] le = le(points[i], curr);
                    s += dist(new Point((prev.y - le[1]) / le[0], prev.y), points[i]);
                    prev = points[i];
                    curr = points[i];
                } else curr = points[i];
            }
            out.append(String.format("%.2f", s)).append("\n");
        }
        System.out.print(out);
    }

    static double dist(Point o1, Point o2) {
        return Math.sqrt((o1.x - o2.x) * (o1.x - o2.x) + (o1.y - o2.y) * (o1.y - o2.y));
    }

    static double[] le(Point p1, Point p2) {
        double k = (p2.y - p1.y) / (p2.x - p1.x);
        double m = p1.y - k * p1.x;
        return new double[]{k, m};
    }

    static class Point implements Comparable<Point> {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return Double.compare(o.x, x);
        }
    }
}
