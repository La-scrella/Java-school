package First;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + '}';
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector add(Vector a) {
        return new Vector(x + a.x, y + a.y);
    }

    public Vector subtract(Vector a) {
        return new Vector(x - a.x, y - a.y);
    }
}
