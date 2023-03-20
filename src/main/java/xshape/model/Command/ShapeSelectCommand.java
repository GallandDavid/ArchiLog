package xshape.model.Command;

import xshape.controleur.XShape;
import xshape.model.Shape;

public class ShapeSelectCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;
    private String _ref;

    public ShapeSelectCommand(XShape app, Object editor, double x, double y, String ref) {
        super(app, editor);
        _mouse_x = x;
        _mouse_y = y;
        _ref = ref;
    }

    @Override
    public boolean execute() {
        Shape shape =  _app.getShape(_ref);
        shape.setPrevMouse(_mouse_x, _mouse_y);
        return false;
    }
    
}
