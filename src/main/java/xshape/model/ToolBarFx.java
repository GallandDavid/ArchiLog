package xshape.model;

import javafx.event.EventHandler;
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
    bt.setOnMouseDragged(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
              notifyObservers("rect selected drag", event.getX(), event.getY());
              writelog("Event on Source: mouse dragged");
            }
        });
    bt.setOnMousePressed(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                notifyObservers("rect selected", event.getX(), event.getY());
                writelog("Event on Source: mouse pressed");
                event.consume();
            }
        });
 
    bt.setOnMouseReleased(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
              notifyObservers("rect select place", event.getX(), event.getY());
              writelog("Event on Source: mouse released");
              event.consume();
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

  @Override
  public void notifyObservers(String code, double X, double Y, String ref) {
    for (Iobserver obs : _obs) {
      obs.update(code,X, Y,ref);
    }
  }

  // Helper Method for Logging
  private void writelog(String text)
  {
      System.out.println(text);
  }
}
