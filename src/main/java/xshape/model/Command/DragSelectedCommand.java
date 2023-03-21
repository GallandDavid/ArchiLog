package xshape.model.Command;

import java.awt.geom.Point2D;

import xshape.controleur.XShape;
import xshape.model.Rectangle;

public class DragSelectedCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;

    public DragSelectedCommand(XShape app, Object editor, double x, double y) {
        super(app, editor);
        _mouse_x = x;
        _mouse_y = y;
    }

    @Override
    public boolean execute() {
        ((Rectangle)_editor).visiblePosition(new Point2D.Double(_mouse_x,_mouse_y));
        _app.draw();
        return false;
    }

    @Override
    public void print() {
        System.out.println("DragSelectedCommand");
    }

}
