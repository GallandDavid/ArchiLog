package xshape.model.Command;

import xshape.controleur.XShape;

public class UndoCommand extends Command{

    public UndoCommand(XShape app) {
        super(app, null);
    }

    @Override
    public boolean execute() {
        _app.undo();
        return false;
    }

    @Override
    public void print() {
        System.out.println("UndoCommand");
    }
    
}
