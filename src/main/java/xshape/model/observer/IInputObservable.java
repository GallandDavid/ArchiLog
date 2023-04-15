package xshape.model.observer;

import xshape.model.controlInput.InputControl;

public interface IInputObservable {
    void registerOberver(IInputObserver obs);
    void unRegisterObserver(IInputObserver obs);
    void notifyObservers(InputControl mouse);
    void notifyObservers(double width, double height);
}
