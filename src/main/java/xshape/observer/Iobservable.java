package xshape.observer;

import xshape.model.Command.Command;

public interface Iobservable {
    void registerOberver(Iobserver obs);
    void unRegisterObserver(Iobserver obs);

    void notifyObservers(Command command);
    void notifyObservers(String code);
    void notifyObservers(String code, int X, int Y);
    void notifyObservers(String code, double X, double Y);
    void notifyObservers(String code, double X, double Y, String ref);
}
