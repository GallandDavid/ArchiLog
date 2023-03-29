package xshape.model.Builder.menu.popupmenu;

import java.awt.geom.Point2D;

public interface PopUpMenuDirector {
    void createPopUpMenu();
    Object getPopUpMenu();
    PopUpMenu popUpMenu();
    boolean isInPopUpMenu(Point2D pos);
}
