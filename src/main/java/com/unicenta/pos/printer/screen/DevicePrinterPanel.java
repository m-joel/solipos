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

package com.unicenta.pos.printer.screen;

import com.unicenta.pos.forms.AppLocal;
import com.unicenta.pos.printer.DevicePrinter;
import com.unicenta.pos.printer.ticket.BasicTicket;
import com.unicenta.pos.printer.ticket.BasicTicketForScreen;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 *
 * @author JG uniCenta
 */
public class DevicePrinterPanel extends javax.swing.JPanel implements DevicePrinter {
    
    private final String m_sName;
   
    private final JTicketContainer m_jTicketContainer;    
    private BasicTicket m_ticketcurrent;
    
    /** Creates new form JPrinterScreen2 */
    public DevicePrinterPanel() {
        initComponents();
        
        m_sName = AppLocal.getIntString("printer.screen");
        
        m_ticketcurrent = null;
       
        m_jTicketContainer = new JTicketContainer();
        m_jScrollView.setViewportView(m_jTicketContainer);
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getPrinterName() {
        return m_sName;
    }
    
    /**
     *
     */
    @Override
    public void printLogo(){   
    }

    /**
     *
     * @return
     */
    @Override
    public String getPrinterDescription() {
        return null;
    }       

    /**
     *
     * @return
     */
    @Override
    public JComponent getPrinterComponent() {
        return this;
    }

    /**
     *
     */
    @Override
    public void reset() {
        m_ticketcurrent = null;
        m_jTicketContainer.removeAllTickets();
        m_jTicketContainer.repaint();
    }
    
    @Override
    public void beginReceipt() {
        m_ticketcurrent = new BasicTicketForScreen();

    }

    /**
     *
     * @param image
     */
    @Override
    public void printImage(BufferedImage image) {
        m_ticketcurrent.printImage(image);
    }

    /**
     *
     * @param type
     * @param position
     * @param code
     */
    @Override
    public void printBarCode(String type, String position, String code) {
        m_ticketcurrent.printBarCode(type, position, code);
    }

    /**
     *
     * @param iTextSize
     */
    @Override
    public void beginLine(int iTextSize) {
        m_ticketcurrent.beginLine(iTextSize);
    }

    /**
     *
     * @param iStyle
     * @param sText
     */
    @Override
    public void printText(int iStyle, String sText) {
        m_ticketcurrent.printText(iStyle, sText);
    }

    /**
     *
     */
    @Override
    public void endLine() {
        m_ticketcurrent.endLine();
    } 

    /**
     *
     */
    @Override
    public void endReceipt() {
        m_jTicketContainer.addTicket(new JTicket(m_ticketcurrent));
        m_ticketcurrent = null;
    }
    
    /**
     *
     */
    @Override
    public void openDrawer() {
        Toolkit.getDefaultToolkit().beep();
    }   
       
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_jScrollView = new javax.swing.JScrollPane();

        setLayout(new java.awt.BorderLayout());

        m_jScrollView.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        add(m_jScrollView, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane m_jScrollView;
    // End of variables declaration//GEN-END:variables
    
}
