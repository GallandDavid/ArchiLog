package xshape.model;

import java.util.ArrayList;

import xshape.model.Builder.ToolBarBuilder;
import xshape.model.button.Button;
import xshape.model.button.RectButton;
import xshape.observer.Iobservable;
import xshape.observer.Iobserver;

public abstract class ToolBar implements ToolBarBuilder, Iobservable{
    private static int vw = 100;
    private static int vh = 10;
    private double height;
    private double width;
    protected Button _rectbutton = new RectButton();
    private Object _obj = null;
    protected ArrayList<Iobserver> _obs = new ArrayList<>();

    public ToolBar(Iobserver obs){
        registerOberver(obs);
    }

    @Override
    public Button getRectButton(){
        return _rectbutton;
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
        public void registerOberver(Iobserver obs) {
        _obs.add(obs);
    }

    @Override
    public void unRegisterObserver(Iobserver obs) {
        if(_obs.contains(obs))
            _obs.remove(obs);
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
  public void notifyObservers(String code) {
    for (Iobserver obs : _obs) {
        obs.update(code);
      }
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

}
