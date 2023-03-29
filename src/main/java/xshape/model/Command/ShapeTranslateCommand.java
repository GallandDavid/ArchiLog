package xshape.model.Command;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.controleur.XShape;
import xshape.model.Builder.menu.toolbar.ToolBarDirector;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class ShapeTranslateCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;

    public ShapeTranslateCommand(XShape app, ArrayList<Object> editor, double x, double y ) {
        super(app, editor);
        _mouse_x = x;
        _mouse_y = y;
    }

    @Override
    public boolean execute() {
        System.out.println("----------------\nexecute :" + print());
        for (Object shape : _editor) {
            ((Shape) shape).setMovable(false);
            Point2D tmp = (Point2D) ((Shape) shape).visiblePosition();
            ((Shape) shape).visiblePosition((Point2D) ((Shape) shape).position());
            saveBackup(null);
            System.out.println("editor = backup : " +((Shape) shape).equals(_backup));
            if(_mouse_y < ((ToolBarDirector)_app).toolBar().getHeight()) return false;
            ((Shape) shape).position((Point2D)tmp.clone());
            ((Shape) shape).visiblePosition((Point2D)tmp.clone());
        }
        System.out.println("execute\n-----------------");
        return  true;
    }


    @Override
    public String print() { return "ShapeTranslateCommand" + super.print(); }


    @Override
    public void backup(){
        undo();
    }

    @Override
    public void undo(){
        for (int i = 0; i < _editor.size(); i ++) {
            Shape tmp = (Shape) instanceShape((Shape)_editor.get(i), null);
            ((Shape)_editor.get(i)).duplicate((Shape)_backup.get(i));
            ((Shape)_editor.get(i)).duplicate(tmp);
        }
    }

    @Override
    public void accept(IInputVisitor visitor) {
        visitor.visit(this);
    }


}
