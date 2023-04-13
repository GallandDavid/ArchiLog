package xshape.model.shape.tools.menus;

import java.awt.geom.Point2D;

import xshape.model.shape.Rectangle;

public abstract class Menu extends Rectangle{
    private String _title;


    public Menu(String title, Point2D pos, Point2D size, boolean selected){
        super(pos, size, selected);
        _title = title;
    }
    

    /**
     * @return String return the _title
     */
    public String title() { return _title; }

    /**
     * @param _title the _title to set
     */
    public void title(String title) { _title = title; }

}
