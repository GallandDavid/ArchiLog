package xshape.model.Command;

import java.util.ArrayList;

import xshape.controleur.XShape;
import xshape.model.shape.Shape;

public class RectangleSelectedCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;

    public RectangleSelectedCommand(XShape app, ArrayList<Object> editor, double x, double y) {
        super(app, editor);
        _mouse_x = x;
        _mouse_y = y;
    }

    @Override
    public boolean execute() {
        Shape s = (Shape) _app.factory().createRectangle(_mouse_x, _mouse_y, true);
        s.selected(true);
        _app.addSelectedShape(s);
        return false;
    }

    @Override
    public String print() { return "RectangleSelectedCommand" + super.print(); }

    @Override
    public void backup() {
    }

    @Override
    public void undo() {
    }
}
