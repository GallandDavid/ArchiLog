package xshape.model.toolbar;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import xshape.controleur.XShape;
import xshape.model.Command.DragSelectedCommand;
import xshape.model.Command.RectPlaceCommand;
import xshape.model.Command.RectangleSelectedCommand;
import xshape.model.Command.RedoCommand;
import xshape.model.Command.TrashBinCommand;
import xshape.model.Command.UndoCommand;
import xshape.model.observer.Iobserver;

public class ToolBarAwt extends ToolBar {
    private JMenuItem _trashbin = new JMenuItem();

    public ToolBarAwt(Iobserver obs) {
        super(obs);
    }

    @Override public void makeProduct() {
        createToolBar();
        createRectButton();
        createUndoButton();
        createRedoButton();
        createTrashBinButton();
    }

    @Override public void createToolBar() { setProduct(new JMenuBar()); }

    @Override public void createRectButton() {
        JMenuBar mb = (JMenuBar) getProduct();
        JMenuItem br = new JMenuItem(getRectButton().title());
        br.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                notifyObservers(new DragSelectedCommand((XShape) _app, ((XShape)_app)._selected_item, e.getX(), e.getY()));
                e.consume();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                e.consume();
            }
            
        });
        br.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                e.consume();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                br.setCursor(new Cursor(Cursor.HAND_CURSOR));
                e.consume();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                e.consume();
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                notifyObservers(new RectangleSelectedCommand((XShape ) _app, br, e.getX(), e.getY()));
                br.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                e.consume();
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                notifyObservers(new RectPlaceCommand((XShape) _app, e.getX(), e.getY()));
                br.setCursor(new Cursor(Cursor.HAND_CURSOR));
                e.consume();
            }
        });
        mb.add(br);
        setProduct(mb);
    }


    @Override
    public void createRedoButton() {
        JMenuBar mb = (JMenuBar) getProduct();
        JMenuItem br = new JMenuItem(getRedoButton().title());
        br.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) { 
                notifyObservers(new RedoCommand((XShape)_app, br));
                br.setCursor(new Cursor(Cursor.HAND_CURSOR));
                e.consume(); 
            }
            @Override public void mouseEntered(java.awt.event.MouseEvent e) { e.consume(); }
            @Override public void mouseExited(java.awt.event.MouseEvent e) { e.consume(); }
            @Override public void mousePressed(java.awt.event.MouseEvent e) { e.consume(); }
            @Override public void mouseReleased(java.awt.event.MouseEvent e) { e.consume(); }
        });
        mb.add(br);
        setProduct(mb);
    }

    @Override
    public void createUndoButton() {
        JMenuBar mb = (JMenuBar) getProduct();
        JMenuItem br = new JMenuItem(getUndoButton().title());
        br.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) { 
                notifyObservers(new UndoCommand((XShape)_app, br));
                br.setCursor(new Cursor(Cursor.HAND_CURSOR));
                e.consume(); 
            }
            @Override public void mouseEntered(java.awt.event.MouseEvent e) { e.consume(); }
            @Override public void mouseExited(java.awt.event.MouseEvent e) { e.consume(); }
            @Override public void mousePressed(java.awt.event.MouseEvent e) { e.consume(); }
            @Override public void mouseReleased(java.awt.event.MouseEvent e) { e.consume(); }
        });
        mb.add(br);
        setProduct(mb);
    }

    @Override
    public void createTrashBinButton() {
        JMenuBar mb = (JMenuBar) getProduct();
        JMenuItem br = new JMenuItem(getTrashBinButton().title());
        br.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) { e.consume(); }
            @Override public void mouseEntered(java.awt.event.MouseEvent e) { e.consume(); }
            @Override public void mouseExited(java.awt.event.MouseEvent e) { e.consume(); }
            @Override public void mousePressed(java.awt.event.MouseEvent e) { e.consume(); }
            @Override public void mouseReleased(java.awt.event.MouseEvent e) { 
                notifyObservers(new TrashBinCommand((XShape) _app));
                br.setCursor(new Cursor(Cursor.HAND_CURSOR));
                e.consume();
             }
        });
        _trashbin = br;
        mb.add(br);
        setProduct(mb);
    }

    @Override public void createSeparation() {}
    @Override public double getTrashBinPosX() { return _trashbin.getLocation().getX(); }
    @Override public double getTrashBinPosY() { return _trashbin.getLocation().getY() + 40; }
    @Override public double getTrashBinSizeX() { return _trashbin.getWidth();}
    @Override public double getTrashBinSizeY() { return _trashbin.getHeight();}
}
