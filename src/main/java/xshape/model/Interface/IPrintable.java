package xshape.model.Interface;

import xshape.model.shape.Rectangle;
import xshape.model.shape.WhiteBoard;

public interface IPrintable {
    void whiteBoard(WhiteBoard rect);
    WhiteBoard whiteBoard();
    void render();
    void draw();
    void clear();
    void createDrawer();
}
