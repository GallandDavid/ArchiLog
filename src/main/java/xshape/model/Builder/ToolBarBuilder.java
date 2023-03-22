package xshape.model.Builder;

import xshape.model.button.Button;

public interface ToolBarBuilder {

    void makeProduct();
    void setProduct(Object obj);
    Button getRectButton();
    Button getRedoButton();
    Button getUndoButton();
    Object getProduct();
    void createToolBar();
    void createRectButton();
    void createRedoButton();
    void createUndoButton();
}
