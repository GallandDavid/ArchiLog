package xshape.model.observer;

import xshape.model.Command.Command;
import xshape.model.controlInput.InputControl;

public interface IInputObservable {
    void registerOberver(IInputObserver obs);
    void unRegisterObserver(IInputObserver obs);
    void notifyObservers(Command command);
    void notifyObservers(InputControl mouse);
}
