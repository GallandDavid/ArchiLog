package xshape.observer;

public interface Iobservable {
    void registerOberver(Iobserver obs);

    void unRegisterObserver(Iobserver obs);

    void notifyObservers(String code);
    void notifyObservers(String code, int X, int Y);
}
