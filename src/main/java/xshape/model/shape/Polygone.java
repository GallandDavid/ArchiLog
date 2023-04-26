package xshape.model.shape;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import xshape.model.Interface.IShape;
import xshape.model.Interface.ISideable;
import xshape.model.shape.tools.toolbar.editToolBar.EditToolBar;
import xshape.model.visitor.ApplyEditToolBarVisitor;
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
        Point2D[] points = points();
        Path2D.Double polygon = new Path2D.Double();
        polygon.moveTo(points[0].getX(), points[0].getY());
        for (int i = 1; i < side(); i++) {
            polygon.lineTo(points[i].getX(), points[i].getY());
        }
        polygon.closePath();

        return polygon.contains(pos);
    }


    public Point2D[] points(){
        Point2D[] points = new Point2D[side()];
        double radius = length() / (2 * Math.sin(Math.PI / side()));
        for (int i = 0; i < side(); i++) {
            double angle = rotation() + i * 2 * Math.PI / side();
            double x = visiblePosition().getX() + radius * Math.cos(angle);
            double y = visiblePosition().getY() + radius * Math.sin(angle);
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
    @Override public EditToolBar accept(CreateEditToolBarVisitor cetbv, Point2D pos, Point2D size) { return cetbv.polygoneEditToolBar(this, pos, size, position().getX(), position().getY(), side(), length(), color().getRed(), color().getGreen(), color().getBlue(), rotation()); }
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

    @Override
    public void accept(ApplyEditToolBarVisitor aetbv, EditToolBar etb) { aetbv.applyPolygone(this, etb);}

    
}
