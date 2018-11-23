package com.project.line;

import java.awt.Image;

import javax.swing.JLabel;

public class DrawComponent extends JLabel {
	
	
	protected Image image = null;   
	protected String imgLocation = "";  
	
	public void setImage(Image img) {
        this.image = img;
    }
    
    /**
     * Returns the value of image
     * @return image
     */
    public Image getImage() {
        return this.image;
    }
    
    /**
     * Get the value of type
     * 
     * @return the value of type
     */
    
    
    public void setBackgroundImage(String imgLocation){
        this.imgLocation = imgLocation;
    }
    
    /**
     * Returns the value of imgLocation
     * @return imgLocation
     */
    public String getBackgroundImage(){
        return this.imgLocation;
    }

}
