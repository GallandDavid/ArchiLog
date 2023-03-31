package xshape.model.shape.tools.popup;

import java.awt.geom.Point2D;

import xshape.model.shape.Rectangle;
import xshape.model.shape.tools.menus.Menu;

public abstract class PopUpMenu extends Rectangle{
    public static Point2D _size = new Point2D.Double(40,30);
    private final Menu _edit;
    private final Menu _group;
    private final Menu _ungroup;
    private boolean _grouped;
    private int _selected;

    public PopUpMenu(Point2D pos, int selected, boolean grouped, Menu edit, Menu group, Menu ungroup) {
        super(pos, _size, false);
        _selected = selected;
        _grouped = grouped;
        _edit = edit;
        _group = group;
        _ungroup = ungroup;
    }

    public Menu edit(){ return _edit; }
    public Menu group(){ return _group; }
    public Menu ungroup(){ return _ungroup; }
    

    /**
     * @return boolean return the _grouped
     */
    public boolean grouped() { return _grouped; }

    /**
     * @return int return the _selected
     */
    public int nbSelected() { return _selected; }

}
