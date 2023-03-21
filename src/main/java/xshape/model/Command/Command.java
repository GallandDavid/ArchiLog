package xshape.model.Command;

import xshape.controleur.XShape;

public abstract class Command implements ICommand {
    public XShape _app;
    protected Object _editor;
    protected Object _backup;

    public Command(XShape app, Object editor){
        _app = app;
        _editor = editor;

    }

    public void saveBackup(){
        _backup = _editor;
    }

    @Override
    public void undo(){
    }

    
}
