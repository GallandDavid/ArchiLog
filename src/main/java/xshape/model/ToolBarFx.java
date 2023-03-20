package xshape.model;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
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
    bt.setOnMouseDragged(new EventHandler <MouseEvent>(){
      public void handle(MouseEvent event){
        notifyObservers("rect selected drag", event.getX(), event.getY());
        event.consume();
      }
    });
    bt.setOnMousePressed(new EventHandler <MouseEvent>(){
      public void handle(MouseEvent event){
        notifyObservers("rect selected", event.getX(), event.getY());
        bt.setCursor(Cursor.MOVE);
        event.consume();
      }
    });
 
    bt.setOnMouseReleased(new EventHandler <MouseEvent>(){
      public void handle(MouseEvent event){
        notifyObservers("rect select place", event.getX(), event.getY());
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
  }
}
