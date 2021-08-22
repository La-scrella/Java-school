import java.util.List;

public abstract class Figure {
    protected Vector refPoint;

    public Figure(Vector referencePoint) {
        refPoint = referencePoint;
    }

    abstract public double square();

    abstract public List<Vector> points();

    protected double[] edges() {
        List<Vector> pts = points();
        int count =  pts.size();
        double[] lens = new double[Math.max(count, 1)];
        Vector prev = refPoint;
        for (int i = 1; i < count; i++) {
            lens[i] = pts.get(i).subtract(prev).length();
            prev = pts.get(i);
        }
        lens[0] = refPoint.subtract(prev).length();
        return lens;
    }

    public double perimetr() {
        double sum = 0;
        for (double edge : edges()) {
            sum += edge;
        }
        return sum;
    }

    public void printPoints() {
        for (Vector point : points()) {
            System.out.print(point.toString() + ' ');
        }
        System.out.println();
    }
}
