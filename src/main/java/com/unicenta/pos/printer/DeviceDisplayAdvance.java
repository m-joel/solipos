/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicenta.pos.printer;

import com.unicenta.pos.sales.JTicketLines;
import java.awt.image.BufferedImage;

/**
 *
 * @author tek
 */
public interface DeviceDisplayAdvance {
    
    // has support for product image
    public static final int PRODUCT_IMAGE = 1;
    
    // has support for displaying ticket lines
    public static final int TICKETLINES = 2;
    
    // has support for displaying AD Image
    public static final int AD_IMAGE = 4;
    
    // Used to indicate what the advance display can support
    public boolean hasFeature(int feature);
    
    // Advance support for list of ticket lines 
    public boolean setTicketLines(JTicketLines ticketlinesPanel);
    
    
}
