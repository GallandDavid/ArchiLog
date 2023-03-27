package xshape.model.Builder.popupmenu;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;

import java.awt.geom.Point2D;

import xshape.controleur.XShape;
import xshape.model.Command.GroupCommand;
import xshape.model.observer.Iobserver;

public class PopUpMenuFx extends PopUpMenu{

    public PopUpMenuFx(Iobserver app, Point2D pos, int selected, boolean grouped) {
        super(app, pos, selected, grouped);
    }

    @Override
    public void createPopUp() {
        MenuBar mb =  new MenuBar();
        mb.setLayoutX(getPosX());
        mb.setLayoutY(getPosY());
        setProduct((Object) mb);
    }

    @Override
    public void createEditMenu() {
        Menu m = new Menu("Edit");
        ((MenuBar) getProduct()).getMenus().addAll(m);
    }

    @Override
    public void createGroupMenu() {
        Label label = new Label("Group");
        label.setOnMouseClicked(new EventHandler <MouseEvent>(){
            public void handle(MouseEvent event){
              notifyObservers(new GroupCommand((XShape) _app));
              label.setCursor(Cursor.HAND);
              event.consume();
            }
          });
        Menu m = new Menu("", label);
        
        ((MenuBar) getProduct()).getMenus().addAll(m);
    }

    @Override
    public void createUnGroupMenu() {
        Label label = new Label("Un-Group");
        label.setOnMouseClicked(new EventHandler <MouseEvent>(){
        public void handle(MouseEvent event){
          notifyObservers(new GroupCommand((XShape) _app));
          label.setCursor(Cursor.HAND);
          event.consume();
        }
      });
    Menu m = new Menu("", label);
        ((MenuBar) getProduct()).getMenus().addAll(m);
    }
    
}
