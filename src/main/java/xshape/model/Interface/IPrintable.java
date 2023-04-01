package xshape.model.Interface;

import xshape.model.shape.Rectangle;

public interface IPrintable {
    void whiteBoard(Rectangle rect);
    Rectangle whiteBoard();
    void render();
    void draw();
}
