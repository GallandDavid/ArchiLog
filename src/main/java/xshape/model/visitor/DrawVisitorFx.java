package xshape.model.visitor;

import java.awt.geom.Point2D;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import xshape.model.shape.Group;
import xshape.model.shape.Polygone;
import xshape.model.shape.Rectangle;
import xshape.model.shape.Shape;
import xshape.model.shape.tools.EditItem;
import xshape.model.shape.tools.Menu;
import xshape.model.shape.tools.PopUpMenu;
import xshape.model.shape.tools.WhiteBoard;
import xshape.model.shape.tools.toolbar.ShapeToolBar;
import xshape.model.shape.tools.toolbar.SystemToolBar;
import xshape.model.shape.tools.toolbar.editToolBar.EditToolBar;



public class DrawVisitorFx implements DrawVisitor{
    Canvas _cvs = null;

    public DrawVisitorFx(){}

    public void canvas(Canvas cvs){
        _cvs = cvs;
    }

    public void drawGroup(Group grp){
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.save();
        gc.translate(grp.position().getX(), grp.position().getY());
        gc.rotate(grp.rotation());

        gc.translate(-grp.position().getX(), -grp.position().getY());
        if(grp.selected()){
            
            gc.setFill(Color.color(0,0,1.0,0.7));
            gc.fillRect(grp.position().getX() - grp.size().getX()/2,grp.position().getY() - grp.size().getY()/2,
            grp.size().getX(), grp.size().getY());
        }
        for (Shape shape : grp.group()) shape.accept(this);
        gc.restore();
    }

    @Override
    public void drawPolygone(Polygone polygone) {
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.save();
        gc.translate(polygone.position().getX(), polygone.position().getY());
        gc.rotate(polygone.rotation());
        gc.translate(-polygone.position().getX(), -polygone.position().getY());
        if(polygone.selected()) {
            gc.setFill(Color.color(0,0,1.0,0.7));
        }else gc.setFill(Color.color(polygone.color().getRed()/255, polygone.color().getGreen()/255, polygone.color().getBlue()/255));
        gc.fillPolygon(polygone.pointsXDouble(),
                        polygone.pointsYDouble(),
                    polygone.side());
        gc.restore();
    }

    @Override
    public void drawRectangle(Rectangle rect) {
        Point2D p = rect.visiblePosition();
		Point2D	s = rect.visibleSize();
        GraphicsContext gc = _cvs.getGraphicsContext2D();

        gc.save();
        gc.translate(p.getX(), p.getY());
        gc.rotate(rect.rotation());
        if(rect.selected()) {
            gc.setFill(Color.color(0,0,1.0,0.7));
        }else gc.setFill(Color.color(rect.color().getRed()/255, rect.color().getGreen()/255, rect.color().getBlue()/255));
        gc.fillRect(0- s.getX()/2, 0- s.getY()/2,
                    s.getX(), s.getY());
        gc.restore();
    }

    @Override
    public void drawWhiteBoard(WhiteBoard wtb) {
        Point2D p = wtb.position();
		Point2D	s = wtb.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
    }

    @Override
    public void drawShapeToolBar(ShapeToolBar stb) {
        Point2D p = stb.position();
		Point2D	s = stb.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        
        for (int i = 0; i < stb.addons().size(); i ++) {
            stb.addons().get(i).accept(this);
        }
        stb.rect().accept(this);
        stb.poly().accept(this);
    }

    @Override
    public void drawSystemToolBar(SystemToolBar stb) {
        Point2D p = stb.position();
		Point2D	s = stb.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        stb.files().accept(this);
        stb.edit().accept(this);
        stb.trashbin().accept(this);
        if(stb.filesSelected()){
            stb.load().accept(this);
            stb.save().accept(this);
        }
        if(stb.editSelected()){
            stb.undo().accept(this);
            stb.redo().accept(this);
        }
    }

    @Override
    public void drawMenu(Menu menu) {
        Point2D p = menu.position();
		Point2D	s = menu.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        gc.setStroke(Color.BLACK);
        gc.strokeRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 13));
        gc.fillText(menu.title(), (p.getX()- s.getX()/2) + 3, p.getY() + 6);
    }

    @Override
    public void drawPopUpMenu(PopUpMenu pum) {
        Point2D p = pum.position();
		Point2D	s = pum.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        pum.edit().accept(this);
        if(pum.nbSelected() > 1) pum.group().accept(this);
        if(pum.grouped()) pum.ungroup().accept(this);
    }

    @Override
    public void drawEditItem(EditItem editItem) {
        Point2D p = editItem.position();
		Point2D	s = editItem.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        gc.setStroke(Color.GRAY);
        gc.strokeRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        
        for (int i = 0; i < editItem.nbItems(); i ++) {
            Rectangle items_area = editItem.itemsArea()[i];
            Rectangle input_area = editItem.inputsArea()[i];
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 13));
            gc.fillText(editItem.titles()[i], (items_area.position().getX()- items_area.size().getX()/2) + 3, items_area.position().getY() + 6);
            gc.setFill(Color.WHITE);
            gc.fillRect(input_area.position().getX()- input_area.size().getX()/2,
                        input_area.position().getY()- input_area.size().getY()/2,
                        input_area.size().getX(),
                        input_area.size().getY());
            gc.setStroke(Color.GRAY);
            gc.strokeRect(input_area.position().getX()- input_area.size().getX()/2,
                        input_area.position().getY()- input_area.size().getY()/2,
                        input_area.size().getX(),
                        input_area.size().getY());
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 10));
            gc.fillText(editItem.inputs()[i], (input_area.position().getX()- input_area.size().getX()/2) + 3, input_area.position().getY() + 6);

        }
        
    
    }

    @Override
    public void drawEditToolBar(EditToolBar editToolBar) {
        Point2D p = editToolBar.position();
		Point2D	s = editToolBar.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        gc.setStroke(Color.BLACK);
        gc.strokeRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        
        editToolBar.apply().accept(this);
        editToolBar.ok().accept(this);   
        editToolBar.cancel().accept(this);
        for (EditItem ei : editToolBar.items()) {
            ei.accept(this);
        }
    }

    
}
