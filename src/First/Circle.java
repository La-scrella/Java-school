package First;

import java.util.ArrayList;
import java.util.List;

public class Circle extends Figure {
    private double radius = 1;
    private int discretization = 3;

    public Circle(Vector referencePoint, double radius, int discretization){
        super(referencePoint);
        setDiscretization(discretization);
        setRadius(radius);
    }

    @Override
    public double square() {
        return Math.PI * radius * radius;
    }

    @Override
    public List<Vector> points() {
        ArrayList<Vector> list = new ArrayList<>(discretization);
        for (int i = 0; i < discretization; i++) {
            double v = 2 * Math.PI * i / discretization;
            list.add(refPoint.add(new Vector(radius * Math.cos(v), radius * Math.sin(v))));
        }
        return list;
    }

    @Override
    public double perimetr() {
        return 2 * Math.PI * radius;
    }

    public double getRadius() {
        return radius;
    }

    public int getDiscretization() {
        return discretization;
    }

    public void setDiscretization(int discretization) {
        if (discretization > 2)
        {
            this.discretization = discretization;
        }
    }

    public void setRadius(double radius) {
        if (radius > 0)
        {
            this.radius = radius;
        }
    }
}
