package edu.chl.blastinthepast.tests;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by jonas on 2015-05-30.
 */
public class MockPCL implements PropertyChangeListener{

    public String eventName = "No event";
    public Object oldValue;
    public Object newValue;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        eventName = evt.getPropertyName();
        oldValue=evt.getOldValue();
        newValue=evt.getNewValue();
    }
}
