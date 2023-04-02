package xshape.model.Command;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import xshape.controleur.XShape;
import xshape.model.shape.Shape;

public class ShapeTranslateCommand extends Command{

    public ShapeTranslateCommand(XShape app, ArrayList<Object> editor) {
        super(app, editor);
    }

    @Override
    public boolean execute() {
        for (Object shape : _editor) {
            Point2D tmp = (Point2D) ((Shape) shape).visiblePosition();
            ((Shape) shape).visiblePosition((Point2D) ((Shape) shape).position());
            saveBackup(null);
            ((Shape) shape).position((Point2D)tmp.clone());
            ((Shape) shape).visiblePosition((Point2D)tmp.clone());
        }
        return  true;
    }

    @Override public String print() { return "ShapeTranslateCommand" + super.print(); }
    @Override public void backup(){ undo(); }

    @Override
    public void undo(){
        for (int i = 0; i < _editor.size(); i ++) {
            Shape tmp = (Shape) instanceShape((Shape)_editor.get(i), null);
            ((Shape)_editor.get(i)).duplicate((Shape)_backup.get(i));
            ((Shape)_backup.get(i)).duplicate(tmp);
        }
    }
}
