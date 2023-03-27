package xshape.model.Command;

import xshape.controleur.XShape;
import xshape.model.visitor.IInputVisitor;

public class MouseRightClickClickedCommand extends MouseCommand{

    public MouseRightClickClickedCommand(XShape app, double mouse_x, double mouse_y) {
        super(app, mouse_x, mouse_y);
    }

    @Override
    public void backup() {
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public void undo() {
        
    }

    @Override
    public void accept(IInputVisitor visitor) {
        visitor.visit(this);
    }

}
