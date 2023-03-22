package xshape.model.Command;

import xshape.controleur.XShape;
import xshape.model.Shape;
import xshape.model.Builder.ToolBarDirector;

public class ShapeTranslateCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;

    public ShapeTranslateCommand(XShape app, Object editor, double x, double y ) {
        super(app, editor);
        _mouse_x = x;
        _mouse_y = y;
    }

    @Override
    public boolean execute() {
        Shape shape =  (Shape) _editor;
        if(_mouse_y < ((ToolBarDirector)_app).toolBar().getHeight()){
            shape.visiblePosition(shape.position());
            return false;
        }
        shape.position(shape.visiblePosition());
        return  true;
    }

    @Override
    public void print() {
        System.out.println("ShapeTranslateCommand");
    }
}
