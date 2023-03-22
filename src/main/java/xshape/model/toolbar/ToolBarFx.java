package xshape.model.toolbar;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import xshape.controleur.XShape;
import xshape.model.Command.DragSelectedCommand;
import xshape.model.Command.RectPlaceCommand;
import xshape.model.Command.RectangleSelectedCommand;
import xshape.model.Command.RedoCommand;
import xshape.model.Command.UndoCommand;
import xshape.observer.Iobserver;
public class ToolBarFx extends ToolBar {

  public ToolBarFx(Iobserver obs) {
    super(obs);
  }

  @Override
  public void createToolBar() {
    setProduct(new javafx.scene.control.ToolBar());
  }

  @Override
  public void createRectButton() {
    javafx.scene.control.ToolBar tb = (javafx.scene.control.ToolBar) getProduct();
    javafx.scene.control.Button bt = new javafx.scene.control.Button(getRectButton().title());
    bt.setOnMousePressed(new EventHandler <MouseEvent>(){
      public void handle(MouseEvent event){
        notifyObservers(new RectangleSelectedCommand((XShape) _app, (Object) bt, event.getX(), event.getY()));
        bt.setCursor(Cursor.MOVE);
        event.consume();
      }
    });
    bt.setOnMouseDragged(new EventHandler <MouseEvent>(){
      public void handle(MouseEvent event){
        notifyObservers(new DragSelectedCommand((XShape) _app, ((XShape)_app)._selected_item, event.getX(), event.getY()));
        event.consume();
      }
    });
    bt.setOnMouseReleased(new EventHandler <MouseEvent>(){
      public void handle(MouseEvent event){
        notifyObservers(new RectPlaceCommand((XShape) _app, event.getX(), event.getY()));
        bt.setCursor(Cursor.HAND);
        event.consume();
      }
    }); 
    bt.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        bt.setCursor(Cursor.HAND);
      }
    });
    tb.getItems().add(bt);
    setProduct(tb);
  }

  @Override
  public void makeProduct() {
    createToolBar();
    createRectButton();
    createRedoButton();
    createUndoButton();
  }

  @Override
  public void createRedoButton() {
    javafx.scene.control.ToolBar tb = (javafx.scene.control.ToolBar) getProduct();
    javafx.scene.control.Button bt = new javafx.scene.control.Button(getRedoButton().title());
    bt.setOnMouseClicked(new EventHandler <MouseEvent>(){
      public void handle(MouseEvent event){
        notifyObservers(new RedoCommand((XShape)_app, bt));
        bt.setCursor(Cursor.HAND);
        event.consume();
      }
    });
    tb.getItems().add(bt);
    setProduct(tb);
  }

  @Override
  public void createUndoButton() {
    javafx.scene.control.ToolBar tb = (javafx.scene.control.ToolBar) getProduct();
    javafx.scene.control.Button bt = new javafx.scene.control.Button(getUndoButton().title());
    bt.setOnMouseClicked(new EventHandler <MouseEvent>(){
      public void handle(MouseEvent event){
        notifyObservers(new UndoCommand((XShape) _app, bt));
        bt.setCursor(Cursor.HAND);
        event.consume();
      }
    });
    tb.getItems().add(bt);
    setProduct(tb);
  }
}
