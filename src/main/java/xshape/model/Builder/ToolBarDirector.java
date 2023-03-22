package xshape.model.Builder;

import xshape.model.toolbar.ToolBar;

public interface ToolBarDirector {
    void createToolBar();
    Object getToolBar();
    ToolBar toolBar();
}