//    SOLiPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2025 SOLiPOS & previous Openbravo POS works
//    https://solipos.ch
//
//    This file is part of SOLiPOS
//
//    SOLiPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   SOLiPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with SOLiPOS.  If not, see <http://www.gnu.org/licenses/>.

package com.unicenta.pos.printer.escpos;

import com.unicenta.pos.forms.AppLocal;
import com.unicenta.pos.printer.DeviceDisplay;
import com.unicenta.pos.printer.DeviceDisplayBase;
import com.unicenta.pos.printer.DeviceDisplayImpl;
/**
 *
 * @author adrianromero
 */
public abstract class DeviceDisplaySerial implements DeviceDisplay, DeviceDisplayImpl {
    
    private String m_sName;    

    /**
     *
     */
    protected PrinterWritter display;

    /**
     *
     */
    protected DeviceDisplayBase m_displaylines;
    
    /**
     *
     */
    public DeviceDisplaySerial() {
        m_displaylines = new DeviceDisplayBase(this);
    }
    
    /**
     *
     * @param display
     */
    protected void init(PrinterWritter display) {                
        m_sName = AppLocal.getIntString("printer.serial");
        this.display = display;      
        initVisor();        
    }
   
    /**
     *
     * @return
     */
    @Override
    public String getDisplayName() {
        return m_sName;
    }    

    /**
     *
     * @return
     */
    @Override
    public String getDisplayDescription() {
        return null;
    }        

    /**
     *
     * @return
     */
    @Override
    public javax.swing.JComponent getDisplayComponent() {
        return null;
    }
    
    /**
     *
     * @param animation
     * @param sLine1
     * @param sLine2
     */
    @Override
    public void writeVisor(int animation, String sLine1, String sLine2) {
        m_displaylines.writeVisor(animation, sLine1, sLine2);
    }

    /**
     *
     * @param sLine1
     * @param sLine2
     */
    @Override
    public void writeVisor(String sLine1, String sLine2) {        
        m_displaylines.writeVisor(sLine1, sLine2);
    }
     
    /**
     *
     */
    @Override
    public void clearVisor() {
        m_displaylines.clearVisor();
    }
    
    /**
     *
     */
    public abstract void initVisor();
}
