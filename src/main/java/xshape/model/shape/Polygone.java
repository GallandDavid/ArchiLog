package xshape.model.shape;

import java.awt.Color;
import java.awt.geom.Point2D;

import xshape.model.Interface.IShape;
import xshape.model.Interface.ISideable;
import xshape.model.shape.tools.toolbar.editToolBar.EditToolBar;
import xshape.model.visitor.CreateEditToolBarVisitor;
import xshape.model.visitor.DrawVisitor;

public class Polygone extends Shape implements ISideable{
    private int _side;
    private double _length;
    public Polygone(Point2D pos, int side, double lenght, boolean selected) {
        super(pos, selected, false);
        _side = side;
        _length = lenght;
    }

    public Polygone(Point2D pos, int side, double lenght, boolean selected, Color color) {
        super(pos, selected, false, color);
        _side = side;
        _length = lenght;
    }

    public Polygone(Polygone poly){
        super(poly.position(), poly.visiblePosition(), poly.selected(), poly.getId(), poly.isPlaced(), poly.deepth(), poly.grouped(), poly.rotation(), poly.color());
        side(poly.side());
        length(poly.length());
    }

    @Override public boolean isInside(Point2D pos) {
                int windingNumber = 0;
                int n = side();
                Point2D[] points = points();
                for (int i = 0; i < n; i++) {
                    Point2D p1 = points[i];
                    Point2D p2 = points[(i + 1) % n];
        
                    if (p1.getY() <= pos.getY()) {
                        if (p2.getY() > pos.getY() && isLeft(p1, p2, pos) > 0) {
                            windingNumber++;
                        }
                    } else {
                        if (p2.getY() <= pos.getY() && isLeft(p1, p2, pos) < 0) {
                            windingNumber--;
                        }
                    }
                }
                System.out.println(windingNumber != 0);
                return windingNumber != 0;
            }

    private double isLeft(Point2D p1, Point2D p2, Point2D pos) {
        return (p2.getX() - p1.getX()) * (pos.getY() - p1.getY()) - (pos.getX() - p1.getX()) * (p2.getY() - p1.getY());
    }

    public Point2D[] points(){
        Point2D[] points = new Point2D[side()];
        for (int i = 0; i < side(); i++) {
            double angle = 2 * Math.PI * i / side();
            double x = (int) Math.round(visiblePosition().getX() + length() * Math.cos(angle));
            double y = (int) Math.round(visiblePosition().getY() + length() * Math.sin(angle));
            points[i] = new Point2D.Double(x, y);
        }
        return points;
    }

    public double[] pointsXDouble(){
        Point2D[] points_double = points();
        double[] points = new double[side()];
        for (int i = 0; i < side(); i++) {
            points[i] = points_double[i].getX();
        }
        return points;
    }

    public double[] pointsYDouble(){
        Point2D[] points_double = points();
        double[] points = new double[side()];
        for (int i = 0; i < side(); i++) {
            points[i] = points_double[i].getY();
        }
        return points;
    }

    public int[] pointsXInt(){
        Point2D[] points_double = points();
        int[] points = new int[side()];
        for (int i = 0; i < side(); i++) {
            points[i] = ((int)points_double[i].getX());
        }
        return points;

    }

    public int[] pointsYInt(){
        Point2D[] points_double = points();
        int[] points = new int[side()];
        for (int i = 0; i < side(); i++) {
            points[i] = ((int)points_double[i].getY());
        }
        return points;
    }

    @Override public void accept(DrawVisitor dv) { dv.drawPolygone(this); }
    @Override public EditToolBar accept(CreateEditToolBarVisitor cetbv, Point2D pos, Point2D size) { return cetbv.polygoneEditToolBar(null, pos, size, position().getX(), position().getY(), side(), length(), color().getRed(), color().getGreen(), color().getBlue(), rotation()); }
    @Override public void side(int side) { _side = side; }
    @Override public int side() { return _side; }
    @Override public void length(double lenght) { _length = lenght; }
    @Override public double length() { return _length; }

    @Override
    public Point2D size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public Point2D visibleSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visibleSize'");
    }

    @Override
    public Shape visibleSize(Point2D vec) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visibleSize'");
    }

    @Override
    public IShape size(Point2D vec) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override public String toString(){ String str = super.toString();
        str += "\n side : " + side() + "\n";
        str += "\n lenght : " + length() + "\n";
        for (Point2D point : points()) {
            str += "\n x : " + point.getX() + " | y : " + point.getY() + "\n";
        }
    return str;}

    @Override
    public Point2D[] extremPoints() { return points(); }

    
}
