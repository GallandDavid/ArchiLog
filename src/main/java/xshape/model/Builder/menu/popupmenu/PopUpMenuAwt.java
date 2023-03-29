package xshape.model.Builder.menu.popupmenu;

import java.awt.Component;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.awt.geom.Point2D;

import xshape.model.observer.IInputObserver;

public class PopUpMenuAwt extends PopUpMenu{
    public Component _cp;

    public PopUpMenuAwt(IInputObserver app, Point2D pos, int selected, boolean grouped, Component cp) {
        super(app, pos, selected, grouped);
        _cp = cp;
    }

    @Override
    public void createPopUp() {
        PopupMenu pm = new PopupMenu();
        pm.setEnabled(true);
        setProduct((Object) pm);
    }

    @Override
    public void createEditMenu() {
        Menu m = new Menu("Edit");
        ((PopupMenu) getProduct()).add(m);
    }

    @Override
    public void createGroupMenu() {
        Menu m = new Menu("Group");
        ((PopupMenu) getProduct()).add(m);
    }

    @Override
    public void createUnGroupMenu() {
        Menu m = new Menu("Un-Group");
        ((PopupMenu) getProduct()).add(m);
    }
    
}
