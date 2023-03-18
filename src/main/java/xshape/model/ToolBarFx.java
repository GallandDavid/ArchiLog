package xshape.model;

import xshape.observer.Iobserver;/* 
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;*/

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
    /* 
    EventHandler<MouseEvent> leftClickHandler = event -> {
      if (MouseButton.PRIMARY.equals(event.getButton())) {
          notifyObservers("new rect/follow mouse/place at right click");
      }
  };*/
    tb.getItems().add(bt);
    setProduct(tb);
  }

  @Override
  public void makeProduct() {
    createToolBar();
    createRectButton();
  }


  @Override
  public void notifyObservers(String code) {
    
  }



  @Override
  public void notifyObservers(String code, int X, int Y) {
    for (Iobserver obs : _obs) {
      obs.update(code,X, Y);
    }
  }
}
