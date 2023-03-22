package xshape.observer;

import xshape.model.Command.Command;

public interface Iobserver {
    void update(Command command);
    void update(String code);
    void update(String code, int X, int Y);
    void update(String code, double x, double y);
    void update(String code, double x, double y, String ref);

}