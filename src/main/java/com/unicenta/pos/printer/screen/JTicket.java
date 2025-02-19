//    SOLiPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2018 uniCenta & previous Openbravo POS works
//    https://unicenta.com
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

import com.unicenta.pos.printer.ticket.BasicTicket;
import java.awt.*;
import java.util.Map;

class JTicket extends javax.swing.JPanel {
    
    private static final int H_GAP = 8;
    private static final int V_GAP = 8;
    private static final int COLUMNS = 42;
    private static final int LINEWIDTH = COLUMNS * 7;    
    
    private final BasicTicket basict;
    private final Map desktophints;
   
    /** Creates new form JTicket */
    public JTicket(BasicTicket t) {
        
        basict = t;
        desktophints = (Map) Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");
        initComponents();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        paintBorder(g);
        
        Graphics2D g2d = (Graphics2D) g;

        if (desktophints != null) {
            g2d.addRenderingHints(desktophints);
        }
        
        Insets i = getInsets();
        g2d.setPaint(new GradientPaint(
                getWidth() - i.left - i.right - 100, 
                getHeight() - i.top - i.bottom - 100, 
                getBackground(), 
                getWidth() - i.left - i.right, 
                getHeight() - i.top - i.bottom, 
                new Color(0xf0f0f0), true));
        g2d.fillRect(i.left, i.top, 
                getWidth() - i.left - i.right, 
                getHeight() - i.top - i.bottom);
        
        g.setColor(getForeground());
        basict.draw(g2d, i.left + H_GAP, i.top + V_GAP, LINEWIDTH);   
    }  
    
      
    @Override
    public Dimension getPreferredSize() {
        Insets ins = getInsets();
        return new Dimension((int) (
                LINEWIDTH + 2 * H_GAP) + ins.left + ins.right
                  , (int) (basict.getHeight() + 2 * V_GAP) + ins.top + ins.bottom);
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
