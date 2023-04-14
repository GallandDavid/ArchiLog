package xshape.model.Interface;

import xshape.model.abstractFactory.ShapeFactory;

public interface IRunnable {
    //Handler to start the GUI
    void run();
    ShapeFactory factory();
}
