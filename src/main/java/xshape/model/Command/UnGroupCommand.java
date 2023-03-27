package xshape.model.Command;

import xshape.controleur.XShape;
import xshape.model.visitor.IInputVisitor;

public class UnGroupCommand extends Command{

    public UnGroupCommand(XShape app) {
        super(app);
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
