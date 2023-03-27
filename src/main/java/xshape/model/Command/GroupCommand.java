package xshape.model.Command;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.controleur.XShape;
import xshape.model.shape.Group;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class GroupCommand extends Command{

    public GroupCommand(XShape app) {
        super(app);
    }

    @Override
    public void backup() {
        _app.addShape((Shape)_backup.get(0));
        for(Object s : _editor)
            _app.removeShape(((Shape) s).getId());
    }

    @Override
    public boolean execute() {
        saveBackup();
        ArrayList<Shape> array = new ArrayList<>();
        for (Object s : _backup) {
            Shape shape = (Shape) s;
            array.add(shape);
            _app.removeShape(shape.getId());
        }
        Group grp = new Group(new Point2D.Double(0,0), new Point2D.Double(0,0), false, _app, array);
        grp.selected(true);
        grp.toString();
        _backup.add((Object) grp);
        return true;
    }

    @Override
    public void undo() {
        _app.removeShape(((Shape)_backup.get(0)).getId());
        for(Object s : _editor)
            _app.addShape((Shape) s);
    }

    @Override
    public void accept(IInputVisitor visitor) {
        visitor.visit(this);
    }
    
}
