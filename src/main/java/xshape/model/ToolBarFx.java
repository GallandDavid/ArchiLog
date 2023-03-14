package xshape.model;

import xshape.model.Button;

import javafx.scene.Group;

public class ToolBarFx extends ToolBar {

  public ToolBarFx() {
  }

  @Override
  public void createToolBar() {
    setProduct(new javafx.scene.control.ToolBar());
  }

  @Override
  public void createRectButton() {
    javafx.scene.control.ToolBar tb = (javafx.scene.control.ToolBar) getProduct();
    tb.getItems().add(new javafx.scene.control.Button(getRectButton().title()));
    setProduct(tb);
  }

  @Override
  public void makeProduct() {
    createToolBar();
    createRectButton();
  }

}
