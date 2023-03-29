package xshape.model.controlInput;

public class Button {
    private boolean _press_event = false;
    private boolean _release_event = false;

    public Button(){} 

    /**
     * @return boolean return the _press_event
     */
    public boolean pressEvent() { return _press_event; }
    /**
     * @param _press_event the _press_event to set
     */
    public void pressEvent(boolean press_event) { _press_event = press_event; }
    /**
     * @return boolean return the _release_event
     */
    public boolean releaseEvent() { return _release_event; }
    /**
     * @param _release_event the _release_event to set
     */
    public void releaseEvent(boolean release_event) { _release_event = release_event;  }

}
