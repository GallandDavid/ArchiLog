package xshape.model.observer;

import xshape.model.Command.Command;

public interface Iobservable {
    void registerOberver(Iobserver obs);
    void unRegisterObserver(Iobserver obs);

    void notifyObservers(Command command);
}
