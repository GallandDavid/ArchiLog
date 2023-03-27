package xshape.model.Builder.popupmenu;

import java.awt.geom.Point2D;

import xshape.model.Command.Command;
import xshape.model.observer.Iobservable;
import xshape.model.observer.Iobserver;

public abstract class PopUpMenu implements PopUpMenuBuilder, Iobservable{
    private Point2D _pos;
    private Point2D _size = null;
    private Object _obj = null;
    protected Iobserver _app;
    private int _selected;
    private boolean _grouped;
    
    public PopUpMenu(Iobserver app, Point2D pos, int selected, boolean grouped){
        _grouped = grouped;
        _pos = pos;
        _selected = selected;
        registerOberver(app);
    }

    @Override
    public void init(Point2D pos, int selected, boolean grouped){
        _pos = pos;
        _selected = selected;
        _grouped = grouped;
    }

    @Override
    public void setProduct(Object obj){
      _obj = obj;
    }

    @Override
    public void makeProduct() {
        createPopUp();
        if(selected() >= 1){
            createEditMenu();
            if(grouped()) createUnGroupMenu();
        }
        if(selected() > 1)
            if(!grouped()) createGroupMenu();
    }

    @Override public int selected() { return _selected; }
    @Override public Object getProduct(){ return _obj; }
    @Override public void registerOberver(Iobserver app) { _app = app; }
    @Override public void unRegisterObserver(Iobserver obs) { _app = null; }
    @Override public void notifyObservers(Command command) { _app.update(command); }
    @Override public double getWidth() { return _size.getX(); }
    @Override public double getHeight() { return _size.getX(); }
    @Override public double getPosX() { return _pos.getX(); }
    @Override public double getPosY() { return _pos.getY(); }
    @Override public boolean grouped() { return _grouped; }

}
