package xshape.model.controlInput;

public class Button {
    private boolean _press_event = false;
    private boolean _release_event = false;
    private boolean _now = false;

    public Button(){} 

    /**
     * @return boolean return the _press_event
     */
    public boolean pressEvent() { return _press_event; }
    /**
     * @param press_event the _press_event to set
     */
    public void pressEvent(boolean press_event) { _press_event = press_event; }
    /**
     * @return boolean return the _release_event
     */
    public boolean releaseEvent() { return _release_event; }
    /**
     * @param release_event the _release_event to set
     */
    public void releaseEvent(boolean release_event) { _release_event = release_event;  }
    /**
     * @return boolean return the _now
     */
    public boolean now() { return _now; }
    /**
     * @param now the _now to set
     */
    public void now(boolean now) { _now = now; }

}
