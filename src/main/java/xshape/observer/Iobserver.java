package xshape.observer;

import xshape.model.Command.Command;

public interface Iobserver {
    void update(Command command);

}