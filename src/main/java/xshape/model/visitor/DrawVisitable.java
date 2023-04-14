package xshape.model.visitor;

public interface DrawVisitable {
    void accept(DrawVisitor dv);
}
