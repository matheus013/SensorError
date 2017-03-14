package adjuster.model;

import util.math.MathJson;

import java.awt.*;

/**
 * Created by bash on 14/03/17.
 */
public class Vector {
    private Point start;
    private Point end;

    public Vector(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public double dist(Point p) {
        if (m() == 0) return Math.abs(p.getY() - start.getY());
        double n = m() * p.getX() + p.getY() + c();
        return Math.abs(n) / Math.sqrt(m() * m() + 1);
    }

    public double m() {
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    public double c() {
        return start.getY() - start.getX() * m();
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
}
