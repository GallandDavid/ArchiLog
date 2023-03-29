package xshape.model.observer;

import xshape.model.Command.Command;
import xshape.model.controlInput.InputControl;

public interface IInputObserver {
    void update(Command command);
    void update(InputControl mouse);

}