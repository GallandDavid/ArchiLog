package xshape.model;

import xshape.model.Builder.ToolBarBuilder;
import xshape.observer.Iobservable;
import xshape.observer.Iobserver;

public abstract class ToolBar implements ToolBarBuilder, Iobservable{
    private Button _rectbutton = new RectButton();
    private Object _obj = null;
    protected Iobserver _obs = null;

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
        _obs = obs;
    }

    @Override
    public void unRegisterObserver(Iobserver obs) {
    }
}
