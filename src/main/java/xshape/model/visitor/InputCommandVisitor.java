package xshape.model.visitor;

import xshape.model.Command.DragSelectedCommand;
import xshape.model.Command.MouseClickedCommand;
import xshape.model.Command.MouseDraggedCommand;
import xshape.model.Command.MouseEnteredCommand;
import xshape.model.Command.MouseExitedCommand;
import xshape.model.Command.MouseMovedCommand;
import xshape.model.Command.MouseLeftClickPressedCommand;
import xshape.model.Command.MouseReleasedCommand;
import xshape.model.Command.MouseRightClickClickedCommand;
import xshape.model.Command.MouseShiftLeftClickClickedCommand;
import xshape.model.Command.RectPlaceCommand;
import xshape.model.Command.RectangleSelectedCommand;
import xshape.model.Command.RedoCommand;
import xshape.model.Command.ShapeDragCommand;
import xshape.model.Command.ShapeSelectCommand;
import xshape.model.Command.ShapeTranslateCommand;
import xshape.model.Command.TrashBinCommand;
import xshape.model.Command.UndoCommand;
import xshape.model.shape.Shape;

public class InputCommandVisitor implements IInputVisitor{

    @Override
    public void visit(MouseClickedCommand mc) {
    }

    @Override
    public void visit(MouseDraggedCommand mc) {
        for (Shape s : mc._app.orderShapes().values()) {
            if(s.isMovable())
                mc.add(s);
        }

    }

    @Override
    public void visit(MouseEnteredCommand mc) {
    }

    @Override
    public void visit(MouseExitedCommand mc) {
    }

    @Override
    public void visit(MouseMovedCommand mc) {
    }

    @Override
    public void visit(MouseLeftClickPressedCommand mc) {
        boolean breake = false;
        boolean inside = false;
        if(mc.mouseY() > mc._app.toolBar().getHeight()){
            if(mc._app.selection())
                for (Shape s : mc._app.orderShapes().values()){
                    if(breake) break;
                    if(s.isInside(new java.awt.geom.Point2D.Double(mc.mouseX(), mc.mouseY()))){
                        inside = true;
                        if(s.selected()){
                            for (Shape shape : mc._app.orderShapes().values())
                                if(shape.selected())
                                    mc.add(shape);
                            breake = true;
                        }else{
                            for (Shape shape : mc._app.orderShapes().values())
                                if(shape.selected())
                                    shape.selected(false);
                            mc.add(s);
                            breake = true;

                        }
                    }
                    if(!inside){
                        for (Shape shape : mc._app.orderShapes().values())
                                if(shape.selected())
                                    shape.selected(false);
                         mc._app.selection(false);
                    }
                }
            else{
                for (Shape s : mc._app.orderShapes().values()) {
                    if(s.selected())
                        s.selected(false);
                }
                for (Shape s : mc._app.orderShapes().values()) {
                    if(s.isInside(new java.awt.geom.Point2D.Double(mc.mouseX(), mc.mouseY()))){
                        mc._app.selection(true);
                        mc.add(s);
                        break;
                    }
                }
            }
        }

    }

    @Override
    public void visit(MouseReleasedCommand mc) {
        System.out.println("released");
        if(mc.mouseY() > mc._app.toolBar().getHeight()){
            for (Shape shape : mc._app.orderShapes().values())
                if(shape.isMovable())
                    mc.add(shape);
            System.out.println("mouse : " + mc.mouseY());
            System.out.println("tb : " + mc._app.toolBar().getHeight());
        }
        else{
            String str = "";
            str += "mouse : (" + mc.mouseX() + ", " + mc.mouseY() + ")";
            str += "Pos : (" + mc._app.toolBar().getTrashBinPosX() + ", " + mc._app.toolBar().getTrashBinPosY() + ")   |   ";
            str += "Size : (" + mc._app.toolBar().getTrashBinSizeX() + ", " + mc._app.toolBar().getTrashBinSizeY() + ")   |   ";
            System.out.println(str);
            if(mc.mouseX() > mc._app.toolBar().getTrashBinPosX() && mc.mouseX() - mc._app.toolBar().getTrashBinSizeX() / 2 < mc._app.toolBar().getTrashBinPosX() + mc._app.toolBar().getTrashBinSizeX() && mc.mouseY() / 2 > mc._app.toolBar().getTrashBinPosY() - mc._app.toolBar().getTrashBinSizeY() && mc.mouseY() / 2 < mc._app.toolBar().getTrashBinPosY() + mc._app.toolBar().getTrashBinSizeY() / 2){
                TrashBinCommand tb = new TrashBinCommand(mc._app);
                mc._app.update(tb);
            }else{
                for (Shape s : mc._app.orderShapes().values())
                    if(s.isMovable() || s.selected()){
                        s.visiblePosition(s.position());
                        s.setMovable(false);
                    }
            }
        }
    }

    @Override
    public void visit(TrashBinCommand mc) {
        if(mc._app.selection())
            for (Shape s : mc._app.getShapes()){
                System.out.println(s);
                if(s.isMovable() || s.selected())
                    mc.add(s);  
            }
    }

    @Override public void visit(MouseRightClickClickedCommand mc) { }
    @Override public void visit(DragSelectedCommand mc) {}
    @Override public void visit(RectangleSelectedCommand mc) {
        for (Shape shape : mc._app.orderShapes().values())
            if(shape.selected())
                shape.selected(false);
        mc._app.selection(false);
    }

    @Override public void visit(RectPlaceCommand mc) { }

    @Override public void visit(RedoCommand mc) { }

    @Override public void visit(ShapeDragCommand mc) { }

    @Override public void visit(ShapeSelectCommand mc) { }

    @Override public void visit(ShapeTranslateCommand mc) { }

    @Override public void visit(UndoCommand mc) { }

    @Override
    public void visit(MouseShiftLeftClickClickedCommand mc) {
        for (Shape s : mc._app.orderShapes().values()){
            if(s.isInside(new java.awt.geom.Point2D.Double(mc.mouseX(), mc.mouseY()))){
                if(s.selected()){
                    s.selected(false);
                    boolean selected = false;
                    for (Shape shape : mc._app.getShapes()){
                        if(shape.selected())
                            selected = true;
                    }
                    if(!selected)
                        mc._app.selection(false);
                }
                else{
                    mc.add(s);
                    mc._app.selection(true);
                }
            }
        }
        
    }
}
