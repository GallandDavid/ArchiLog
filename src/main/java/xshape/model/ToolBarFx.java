package xshape.model;

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
    bt.setOnMousePressed(e -> {
        notifyObservers("rect selected", e.getX(), e.getY());
      }
    );
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

  @Override
  public void notifyObservers(String code, double X, double Y) {
    for (Iobserver obs : _obs) {
      obs.update(code,X, Y);
    }
  }
}
