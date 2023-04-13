package xshape.model.Interface;

import xshape.model.shape.Shape;

public interface IManipulable {
	String getId();
	boolean equals(Object obj);
	void duplicate(Shape shape);
	boolean grouped();
}