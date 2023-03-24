package xshape.model.toolbar;

import xshape.model.Builder.ToolBarBuilder;
import xshape.model.Command.Command;
import xshape.model.button.Button;
import xshape.model.button.RectButton;
import xshape.model.button.RedoButton;
import xshape.model.button.UndoButton;
import xshape.observer.Iobservable;
import xshape.observer.Iobserver;

public abstract class ToolBar implements ToolBarBuilder, Iobservable{
    private static int vw = 100;
    private static int vh = 10;
    private double height;
    private double width;
    protected Button _rectbutton = new RectButton();
    protected Button _undobutton = new UndoButton();
    protected Button _redobutton = new RedoButton();
    private Object _obj = null;
    protected Iobserver _app;

    public ToolBar(Iobserver app){
        registerOberver(app);
    }

    @Override
    public Button getRectButton(){
        return _rectbutton;
    }

    @Override
    public Button getRedoButton(){
      return _redobutton;
    }

    @Override
    public Button getUndoButton(){
      return _undobutton;
    }

    @Override
    public void setProduct(Object obj){
      _obj = obj;
    }

    @Override
    public Object getProduct(){
      return _obj;
    }

    @Override
    public void registerOberver(Iobserver app) {
      _app = app;
    }

    @Override
    public void unRegisterObserver(Iobserver obs) {
      _app = null;
    }


    public double getWidth() {
      return width;
    }

    public void setWidth(double width) {
      this.width = width;
    }

    public double getHeight() {
      return height;
    }

    public static int getVh() {
      return vh;
    }

    public static int getVw() {
      return vw;
    }

    public void setHeight(double height) {
      this.height = height;
    }

  @Override
  public void notifyObservers(Command command) {
    _app.update(command);
  }

}
