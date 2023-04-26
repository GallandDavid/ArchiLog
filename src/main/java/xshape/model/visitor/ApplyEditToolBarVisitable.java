package xshape.model.visitor;

import xshape.model.shape.tools.toolbar.editToolBar.EditToolBar;

public interface ApplyEditToolBarVisitable {
    void accept(ApplyEditToolBarVisitor aetbv, EditToolBar etb);
}
