package xshape.model.visitor;

import java.awt.geom.Point2D;

import xshape.model.shape.tools.toolbar.editToolBar.EditToolBar;

public interface CreateEditToolBarVisitable {
    EditToolBar accept(CreateEditToolBarVisitor s, Point2D pos, Point2D size);
}
