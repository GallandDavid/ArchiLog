package xshape.model.Command;

import xshape.controleur.XShape;

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
    
}
