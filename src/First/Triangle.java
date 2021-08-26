package First;

import First.Figure;
import First.Vector;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Figure {
    private Vector secondPoint;
    private Vector thirdPoint;

    public Triangle(Vector referencePoint, Vector secondPoint, Vector thirdPoint) {
        super(referencePoint);
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
    }

    @Override
    public double square() {
        double[] edgs = edges();
        double p = 0;
        for (double edg : edgs) {
            p += edg;
        }
        return Math.sqrt(p * (p - edgs[0]) * (p - edgs[1]) * (p - edgs[2]));
    }

    @Override
    public List<Vector> points() {
        return new ArrayList<>() {{
            add(refPoint);
            add(secondPoint);
            add(thirdPoint);
        }};
    }
}