import java.util.HashSet;
import java.util.Objects;
/*
this code is quite ugly, but it's a solution :|
 */
public class Pints {
    static int AMAX = 10, BMAX = 7, CMAX = 4; //the size of the pints
    static int ASTART = 0, BSTART = 7, CSTART = 4; //the starting configuration
    static int TARGET = 2; //what either pint B or pint C should be equal to

    public static void main(String[] args) {
        HashSet<Pint> seen = new HashSet<>();
        Pint start = new Pint(ASTART, BSTART, CSTART);
        seen.add(start);
        Pint solution = generate(seen, start);
        if (solution == null) System.out.println("no solution");
        //This code shows a valid solution, it's not necessarily the shortest one.
        //The output is printed in the "wrong" order (read it from down to up)
        while (solution != null) {
            System.out.println(solution.a + " " + solution.b + " " + solution.c);
            solution = solution.parent;
        }
    }


    static Pint generate(HashSet<Pint> seen, Pint p) {
        if (p.b == TARGET || p.c == TARGET) return p;
        if (p.a != 0) {
            if (p.b != BMAX) {
                int newa = p.a, newb = p.b;
                while (newa > 0 && newb < BMAX) {
                    newa--;
                    newb++;
                }
                Pint generated = new Pint(newa, newb, p.c);
                if (!seen.contains(generated)) {
                    generated.parent = p;
                    seen.add(generated);
                    Pint child = generate(seen, generated);
                    if (child != null) return child;
                }
            }
            if (p.c != CMAX) {
                int newa = p.a, newc = p.c;
                while (newa > 0 && newc < CMAX) {
                    newa--;
                    newc++;
                }
                Pint generated = new Pint(newa, p.b, newc);
                if (!seen.contains(generated)) {
                    generated.parent = p;
                    seen.add(generated);
                    Pint child = generate(seen, generated);
                    if (child != null) return child;
                }
            }
        }
        if (p.b != 0) {
            if (p.a != BMAX) {
                int newb = p.b, newa = p.a;
                while (newb > 0 && newa < AMAX) {
                    newa++;
                    newb--;
                }
                Pint generated = new Pint(newa, newb, p.c);
                if (!seen.contains(generated)) {
                    generated.parent = p;
                    seen.add(generated);
                    Pint child = generate(seen, generated);
                    if (child != null) return child;
                }
            }
            if (p.c != CMAX) {
                int newb = p.b, newc = p.c;
                while (newb > 0 && newc < CMAX) {
                    newb--;
                    newc++;
                }
                Pint generated = new Pint(p.a, newb, newc);
                if (!seen.contains(generated)) {
                    generated.parent = p;
                    seen.add(generated);
                    Pint child = generate(seen, generated);
                    if (child != null) return child;
                }
            }
        }
        if (p.c != 0) {
            if (p.a != AMAX) {
                int newa = p.a, newc = p.c;
                while (newc > 0 && newa < AMAX) {
                    newc--;
                    newa++;
                }
                Pint generated = new Pint(newa, p.b, newc);
                if (!seen.contains(generated)) {
                    generated.parent = p;
                    seen.add(generated);
                    Pint child = generate(seen, generated);
                    if (child != null) return child;
                }
            }
            if (p.b != BMAX) {
                int newb = p.b, newc = p.c;
                while (newc > 0 && newb < BMAX) {
                    newc--;
                    newb++;
                }
                Pint generated = new Pint(p.a, newb, newc);
                if (!seen.contains(generated)) {
                    generated.parent = p;
                    seen.add(generated);
                    Pint child = generate(seen, generated);
                    if (child != null) return child;
                }
            }
        }
        return null;
    }
    static class Pint {
        int a, b, c;
        Pint parent;

        public Pint(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pint pint = (Pint) o;
            return a == pint.a && b == pint.b && c == pint.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
    }
}
