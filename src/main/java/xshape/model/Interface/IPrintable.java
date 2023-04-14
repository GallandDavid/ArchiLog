package xshape.model.Interface;

import xshape.model.shape.tools.WhiteBoard;

public interface IPrintable {
    void whiteBoard(WhiteBoard rect);
    WhiteBoard whiteBoard();
    void render();
    void draw();
    void clear();
    void createDrawer();
}
