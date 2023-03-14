package xshape.model;

import xshape.model.Builder.ToolBarBuilder;

public abstract class ToolBar implements ToolBarBuilder{
    private Button _rectbutton = new RectButton();
    private Object _obj = null;

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


}
