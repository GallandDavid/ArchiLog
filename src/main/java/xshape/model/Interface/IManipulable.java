package xshape.model.Interface;

import xshape.model.shape.Shape;

public interface IManipulable {
	void remove();
	boolean equals(Object obj);
	void duplicate(Shape shape);
	Object adapted();
}