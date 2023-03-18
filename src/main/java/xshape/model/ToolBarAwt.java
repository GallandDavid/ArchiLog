package xshape.model;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import xshape.observer.Iobserver;

public class ToolBarAwt extends ToolBar {

    public ToolBarAwt(Iobserver obs) {
        super(obs);
    }

    @Override
    public void makeProduct() {
        createToolBar();
        createRectButton();
    }

    @Override
    public void createToolBar() {
        setProduct(new JMenuBar());
    }

    @Override
    public void createRectButton() {
        JMenuBar mb = (JMenuBar) getProduct();
        mb.add(new JMenuItem(getRectButton().title()));
        setProduct(mb);
    }

    @Override
    public void notifyObservers(String code) {
    }

}
