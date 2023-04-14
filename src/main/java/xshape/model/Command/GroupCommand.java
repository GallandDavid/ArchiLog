package xshape.model.Command;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.controleur.XShape;
import xshape.model.shape.Group;
import xshape.model.shape.Shape;

public class GroupCommand extends Command{

    public GroupCommand(XShape app, ArrayList<Object> editor) {
        super(app, editor);
    }

    @Override
    public void backup() {
        _app.addShape((Shape)_backup.get(0));
        for(Object s : _editor)
            _app.removeShape(((Shape) s).getId());
    }

    @Override
    public boolean execute() {
        ArrayList<Shape> array_backup = new ArrayList<>();
        for (Object s : _editor) {
            Shape shape = (Shape) s;
            array_backup.add(shape);
            _app.removeShape(shape.getId());
        }
        Group grp = _app.factory().createGroup(array_backup);
        grp.selected(true);
        _backup.add((Object) grp);
        _app.addShape(grp);
        return true;
    }

    @Override
    public void undo() {
        _app.removeShape(((Shape)_backup.get(0)).getId());
        for(Object s : _editor)
            _app.addShape((Shape) s);
    }
    
}
