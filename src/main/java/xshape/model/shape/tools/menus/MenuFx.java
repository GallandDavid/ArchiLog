package xshape.model.shape.tools.menus;

import java.awt.geom.Point2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuFx extends Menu{
    Text _text;
	Rectangle _adapted;
    javafx.scene.Group _grp;

    public MenuFx(String title, Point2D pos, Point2D size, boolean selected, javafx.scene.Group grp) {
        super(title, pos, size, selected);
        _adapted = new Rectangle();
        _text = new Text();
        _grp = grp;
        _grp.getChildren().addAll(_adapted,_text);
    }

    @Override
    public void draw() {
        if(!_grp.getChildren().contains(_adapted)) _grp.getChildren().add(_adapted);
		Point2D p = visiblePosition();
		Point2D	s = visibleSize();
        _text.setX((p.getX()- s.getX()/2) + 5);
		_text.setY(s.getY() - 5);
		_adapted.setWidth(s.getX() - 10);
		_adapted.setHeight(s.getY() - 10);
		_text.setText(title());
        _text.setFont(Font.font(13));
        _text.setFill(Color.BLACK);
		_text.toBack();
        _adapted.setX(p.getX()- s.getX()/2);
		_adapted.setY(p.getY()- s.getY()/2);
		_adapted.setWidth(s.getX());
		_adapted.setHeight(s.getY());
		_adapted.setFill(Color.GRAY);
        _adapted.setStroke(Color.BLACK);
		_adapted.toBack();
		if(selected()) _adapted.setOpacity(0.5); else _adapted.setOpacity(1.0);
    }

    @Override
    public void remove() {
		_grp.getChildren().removeAll(_adapted, _text);
		System.gc();
	}

	@Override public Object adapted() { return _adapted; }
    
}
