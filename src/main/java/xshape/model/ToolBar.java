package xshape.model;

import java.util.ArrayList;

import xshape.model.Builder.ToolBarBuilder;
import xshape.observer.Iobservable;
import xshape.observer.Iobserver;

public abstract class ToolBar implements ToolBarBuilder, Iobservable{
    private Button _rectbutton = new RectButton();
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
}
