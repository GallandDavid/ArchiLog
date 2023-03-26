package xshape.model.Command;

import xshape.controleur.XShape;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class TrashBinCommand extends Command{

    public TrashBinCommand(XShape app) {
        super(app);
    }

    @Override
    public void backup() {
        for(Object shape : _backup)
            _app.removeShape(((Shape) shape).getId());
    }

    @Override
    public boolean execute() {
        saveBackup();
        for(Object shape : _editor)
            _app.removeShape(((Shape) shape).getId());
        return false;
    }

    @Override
    public void undo() {
        for(Object shape : _backup)
            _app.addShape((Shape) shape);
    }

    @Override public void accept(IInputVisitor visitor) { visitor.visit(this); }
    
}
