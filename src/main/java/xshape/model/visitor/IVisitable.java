package xshape.model.visitor;

public interface IVisitable {
    void accept(IInputVisitor visitor);
    
}
