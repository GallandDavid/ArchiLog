package xshape.model.Command;

import xshape.controleur.XShape;
import xshape.model.visitor.IInputVisitor;

public class RedoCommand extends Command{

    public RedoCommand(XShape app, Object editor) {
        super(app, editor);
    }

    @Override
    public boolean execute() {
        _app.redo();
        return false;
    }

    @Override
    public String print() { return "RedoCommand" + super.print(); }



    @Override
    public void saveBackup(){
    }

    @Override
    public void backup() {
    }

    @Override
    public void undo() {
    }

    @Override
    public void accept(IInputVisitor visitor) {
        visitor.visit(this);
    }
    
}
