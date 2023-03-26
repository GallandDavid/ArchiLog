package xshape.model.button;

import java.awt.geom.Point2D;

public abstract class Button { 
    private Point2D _pos;
    private Point2D _size;
    private String _title;
    
    public Button(){
        _title = "Button";
    }

    public Button(String title){
        _title = title;
    }


    public Button(String title, Point2D pos, Point2D size){
        _title = title;
        _pos = pos;
        _size = size;
    }

    public String title(){
        return _title;
    }

    public Point2D position(){
        return _pos;
    }

    public Point2D size(){
        return _size;
    }
}
