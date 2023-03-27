package xshape.model.Command;

import xshape.controleur.XShape;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class MouseShiftLeftClickClickedCommand extends MouseCommand{

    public MouseShiftLeftClickClickedCommand(XShape app, double mouse_x, double mouse_y) {
        super(app, mouse_x, mouse_y);
    }

    @Override
    public void backup() {}

    @Override
    public boolean execute() {
        for (Object s : _editor) {
            ((Shape) s).selected(true);
            ((Shape) s).setMovable(false);
        }
        return false;
    }

    @Override
    public void undo() {}

    @Override
    public void accept(IInputVisitor visitor) {
        visitor.visit(this);
    }
    
}
