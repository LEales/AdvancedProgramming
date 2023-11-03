import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
Check if all points are left of previous point or if all points are right of previous point.
 */
class TheArtGallery {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int n;
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                points[i] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            }
            if (solve(points)) out.append("No\n");
            else out.append("Yes\n");
        }
        System.out.print(out);
    }

    static boolean pLeftOfLine(Point[] line, Point p) {
        Point u = new Point(line[1].x - line[0].x, line[1].y - line[0].y);
        Point v = new Point(p.x - line[0].x, p.y - line[0].y);
        return u.x * v.y - u.y * v.x >= 0;
    }

    static boolean solve(Point[] points) {
        boolean left = pLeftOfLine(new Point[]{points[0], points[1]}, points[2]);
        for (int i = 1; i < points.length; i++) {
            Point[] line = new Point[2];
            line[0] = points[i];
            line[1] = points[(i + 1) % points.length];
            Point check = points[(i + 2) % points.length];
            if (left != pLeftOfLine(line, check)) return false;
        }
        return true;
    }

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
