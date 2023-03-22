package xshape.model.Command;

import xshape.controleur.XShape;
import xshape.model.shape.Shape;

public class ShapeSelectCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;

    public ShapeSelectCommand(XShape app, Object editor, double x, double y ) {
        super(app, editor);
        _mouse_x = x;
        _mouse_y = y;
    }

    @Override
    public boolean execute() {
        Shape shape =  (Shape) _editor;
        shape._selected = true;
        shape.setPrevMouse(_mouse_x, _mouse_y);
        return false;
    }

    @Override
    public String print() { return "ShapeSelectCommand" + super.print(); }



    @Override
    public void saveBackup()  {
    }

    @Override
    public void backup() {
    }

    @Override
    public void undo() {
    }
    
}
