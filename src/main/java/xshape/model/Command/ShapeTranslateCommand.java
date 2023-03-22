package xshape.model.Command;

import java.awt.geom.Point2D;

import xshape.controleur.XShape;
import xshape.model.Shape;
import xshape.model.Builder.ToolBarDirector;

public class ShapeTranslateCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;

    public ShapeTranslateCommand(XShape app, Object editor, double x, double y ) {
        super(app, editor);
        _mouse_x = x;
        _mouse_y = y;
    }

    @Override
    public boolean execute() {
        System.out.println("----------------\nexecute :" + print());
        System.out.println((Shape)_editor);
        ((Shape) _editor)._selected = false;
        Point2D tmp = (Point2D) ((Shape) _editor).visiblePosition();
        ((Shape) _editor).visiblePosition((Point2D) ((Shape) _editor).position());
        saveBackup();
        if(_mouse_y < ((ToolBarDirector)_app).toolBar().getHeight()) return false;
        ((Shape) _editor).position((Point2D)tmp.clone());
        ((Shape) _editor).visiblePosition((Point2D)tmp.clone());
        System.out.println((Shape)_editor);
        System.out.println((Shape)_backup);
        System.out.println("execute\n-----------------");
        return  true;
    }

    @Override
    public String print() { return "ShapeTranslateCommand" + super.print(); }


    @Override
    public void backup(){
        System.out.println("----------------\nbackup :" + print());
        System.out.println((Shape)_editor);
        _app.removeShape(((Shape)_backup).getId());
        _app.addShape((Shape)_editor);
        System.out.println((Shape)_backup);
        System.out.println("backup\n-----------------");
    }

    @Override
    public void undo(){
        System.out.println("----------------\nundo :" + print());
        System.out.println((Shape)_editor);
        _app.removeShape(((Shape)_editor).getId());
        _app.addShape((Shape)_backup);
        System.out.println((Shape)_backup);
        System.out.println("undo\n-----------------");
    }


}
