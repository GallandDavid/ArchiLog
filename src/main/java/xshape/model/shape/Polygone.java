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
    public Polygone(Point2D pos, int side, double lenght, boolean selected, boolean grouped) {
        super(pos, selected, grouped);
    }

    public Polygone(Point2D pos, int side, double lenght, boolean selected, boolean grouped, Color color) {
        super(pos, selected, grouped, color);
    }

    @Override public boolean isInside(Point2D pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInside'");
    }

    public Point2D[] points(){
        Point2D[] points = new Point2D[side()];
        for (int i = 0; i < side(); i++) {
            double angle = 2 * Math.PI * i / side();
            double x = (int) Math.round(position().getX() + length() * Math.cos(angle));
            double y = (int) Math.round(position().getY() + length() * Math.sin(angle));
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

    
}
