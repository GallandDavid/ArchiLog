package xshape.model.Builder;

import xshape.model.Button;

public interface ToolBarBuilder {

    void makeProduct();

    Button getRectButton();

    void setProduct(Object obj);

    void createToolBar();

    Object getProduct();

    void createRectButton();
}