package xshape.model.Builder.toolbar;

import xshape.model.button.Button;

public interface ToolBarBuilder {

    void makeProduct();
    void setProduct(Object obj);
    Button getRectButton();
    Button getRedoButton();
    Button getUndoButton();
    Button getTrashBinButton();
    Object getProduct();
    void createToolBar();
    void createRectButton();
    void createRedoButton();
    void createUndoButton();
    void createTrashBinButton();
    void createSeparation();
}
