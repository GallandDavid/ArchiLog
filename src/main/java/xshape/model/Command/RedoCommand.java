package xshape.model.Command;

import xshape.controleur.XShape;

public class RedoCommand extends Command{

    public RedoCommand(XShape app ) {
        super(app, null);
    }

    @Override
    public boolean execute() {
        _app.redo();
        return false;
    }

    @Override
    public void print() {
        System.out.println("RedoCommand");
    }
    
}
