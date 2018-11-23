package com.project.line;

import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class MapSymbolPanelAdjustListener implements AdjustmentListener {

	public void adjustmentValueChanged(AdjustmentEvent evt) {
		
		//MapSymbolPanel.getPanel().setDrawProperties(false);
		
	    Adjustable source = evt.getAdjustable();
	    if (evt.getValueIsAdjusting()) {
	      return;
	    }
	    int orient = source.getOrientation();
	    if (orient == Adjustable.HORIZONTAL) {
	      //TopoUIStorage.setHORIZONTAL_MOVEMENT(evt.getValue());
	    } else {
	    	//TopoUIStorage.setVERTICAL_MOVEMENT(evt.getValue());
	    }
	  
	    //System.out.println("Value Is :::: "+value);
	  }

}
