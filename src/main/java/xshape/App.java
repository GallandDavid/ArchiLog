package xshape;

import xshape.controleur.AwtApp;
import xshape.controleur.FxApp;
import xshape.controleur.XShape;

public class App {

    public static void main(String[] args) {
        XShape appAwt = new AwtApp();
        appAwt.run();
        XShape appFx = new FxApp();
        appFx.run();

    }

}
