package xshape.model;
import java.awt.*; import javax.swing.*; import java.awt.event.*; 
import java.awt.geom.Point2D;

public class ToolBarAwt extends ToolBar{

    public ToolBarAwt(double posX, double posY, double height, double width, Button rect_button) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), rect_button);
    }

    public JMenuBar draw() {
        JMenuBar menuBar = new JMenuBar();
        JMenuItem rectangleItem = new JMenuItem(super.rectButton().title());
        rectangleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
        });
        
        menuBar.add(rectangleItem);
        return menuBar;
    }
    
}
