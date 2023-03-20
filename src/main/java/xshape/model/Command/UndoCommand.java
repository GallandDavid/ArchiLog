package xshape.model.Command;

import xshape.controleur.XShape;

public class UndoCommand extends Command{

    public UndoCommand(XShape app, Object editor) {
        super(app, editor);
    }

    @Override
    public boolean execute() {
        _app.undo();
        return false;
    }
    
}
