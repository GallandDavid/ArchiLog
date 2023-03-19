package xshape.observer;

public interface Iobserver {
    void update(String code);
    void update(String code, int X, int Y);
    void update(String code, double x, double y);
    void update(String code, double x, double y, String ref);

}