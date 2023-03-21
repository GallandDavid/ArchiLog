package xshape.model.Command;

import xshape.controleur.XShape;
import xshape.model.Shape;

public class ShapeDragCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;

    public ShapeDragCommand(XShape app, Object editor, double x, double y) {
        super(app, editor);
        _mouse_x = x;
        _mouse_y = y;
    }

    @Override
    public boolean execute() {
        Shape shape =  (Shape) _editor;
        shape.visibleTranslate(shape.getMouseVec(_mouse_x,_mouse_y));
        shape.setPrevMouse(_mouse_x,_mouse_y);
        return false;
    }

    @Override
    public void print() {
        System.out.println("ShapeDragCommand");
    }
}
