package xshape.model.Command;

import java.util.ArrayList;

import xshape.model.shape.Shape;

public interface ICommand {
    void saveBackup(ArrayList<Shape> shapes);
    void backup();
    boolean execute();
    void undo();
    String print();
    void add(Object obj);
}
