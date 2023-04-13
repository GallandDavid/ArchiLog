package xshape.model.visitor;

import javafx.scene.Group;
import xshape.model.shape.Rectangle;

public class ShapeDrawFxVisitor {
    Group _grp = null;
	÷
    public void draw(Rectangle rect) {
        if(!_grp.getChildren().contains(_adapted)) _grp.getChildren().add(_adapted);
		Point2D p = visiblePosition();
		Point2D	s = visibleSize();
		_adapted.setX(p.getX()- s.getX()/2);
		_adapted.setY(p.getY()- s.getY()/2);
		_adapted.setWidth(s.getX());
		_adapted.setHeight(s.getY());
		_adapted.setFill(Color.BLUE);
		
		if(selected()) {
			Light.Distant light = new Light.Distant();
			light.setAzimuth(0.0);
			light.setElevation(90.0);
	
			Lighting lighting = new Lighting();
			lighting.setLight(light);
			lighting.setSurfaceScale(1.0);
	
			_adapted.setEffect(lighting);
		}
		else{
			_adapted.setEffect(null);
		}
        _adapted.toFront();
		}
    }

    public void draw(WhiteBoard wb) {
        
    }

    public void draw(ShapeToolBar stb) {
        
    }

    public void draw(SystemToolBar stb) {
        
    }

    public void draw(PopUpMenu pum) {
        
    }

    public void draw(Menu mn) {
        
    }

    public void draw(Group grp) {
    }

}
