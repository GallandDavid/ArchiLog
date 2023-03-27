package xshape.model.observer;

import java.awt.geom.Point2D;

import xshape.model.Command.Command;

public interface Iobserver {
    void update(Command command);

}