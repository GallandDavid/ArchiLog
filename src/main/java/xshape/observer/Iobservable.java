package xshape.observer;

public interface Iobservable {
    void registerOberver(Iobserver obs);

    void unRegisterObserver(Iobserver obs);

    void notifyObservers(String code);
}
