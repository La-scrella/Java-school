package First;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Figure {
    private double length;
    private double width;

    public Rectangle(Vector referencePoint, double length, double width) {
        super(referencePoint);
        this.length = length;
        this.width = width;
    }

    @Override
    public double square() {
        return length * width;
    }

    @Override
    public List<Vector> points() {
        return new ArrayList<>() {{
            add(refPoint);
            add(new Vector(refPoint.getX() + length, refPoint.getY()));
            add(new Vector(refPoint.getX() + length, refPoint.getY() + width));
            add(new Vector(refPoint.getX(), refPoint.getY() + width));
        }};
    }
}