package xshape.model.Builder.popupmenu;

import java.awt.geom.Point2D;

public interface PopUpMenuBuilder {
    void makeProduct();
    void setProduct(Object obj);
    Object getProduct();
    void createPopUp();
    double getWidth();
    double getHeight();
    double getPosX();
    double getPosY();
    int selected();
    void createEditMenu();
    void createGroupMenu();
    void createUnGroupMenu();
    boolean grouped();
    void init(Point2D pos, int selected, boolean grouped);
}
