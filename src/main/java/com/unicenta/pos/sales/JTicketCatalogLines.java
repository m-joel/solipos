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

package com.unicenta.pos.sales;

import com.unicenta.basic.BasicException;
import com.unicenta.pos.catalog.CatalogSelector;
import com.unicenta.pos.catalog.JCatalog;
import com.unicenta.pos.forms.AppView;
import com.unicenta.pos.forms.DataLogicSales;
import com.unicenta.pos.forms.DataLogicSystem;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author JG uniCenta
 */
public class JTicketCatalogLines extends javax.swing.JPanel {
    
    private JRefundLines m_reflines;
    private CatalogSelector m_catalog;
    
    /** Creates new form JTicketCatalogLines
     * @param app
     * @param jTicketEdit
     * @param pricevisible
     * @param taxesincluded
     * @param width
     * @param height */
    public JTicketCatalogLines(AppView app, JPanelTicketEdits jTicketEdit, boolean pricevisible, boolean taxesincluded, int width, int height) {
        
        DataLogicSystem dlSystem = null;
        DataLogicSales dlSales = null;
        dlSystem = (DataLogicSystem) app.getBean("com.unicenta.pos.forms.DataLogicSystem");
        dlSales = (DataLogicSales) app.getBean("com.unicenta.pos.forms.DataLogicSales");
        
        initComponents();
        
        m_reflines = new JRefundLines(dlSystem, jTicketEdit);        
        add(m_reflines, "reflines");
        
        m_catalog = new JCatalog(dlSales, pricevisible, taxesincluded, width, height);
        m_catalog.getComponent().setPreferredSize(new Dimension(0, 245));
        // m_catalog.addActionListener(new CatalogListener());        
        add(m_catalog.getComponent(), "catalog");
    }
    
    /**
     *
     */
    public void showCatalog() {
        showView("catalog");
    }
    
    /**
     *
     * @throws BasicException
     */
    public void loadCatalog() throws BasicException {
        m_catalog.loadCatalog();
    }
    
    /**
     *
     * @param l
     */
    public void addActionListener(ActionListener l) {
        m_catalog.addActionListener(l);
    }

    /**
     *
     * @param l
     */
    public void removeActionListener(ActionListener l) {
        m_catalog.addActionListener(l);
    }
    
    /**
     *
     * @param aRefundLines
     */
    public void showRefundLines(List aRefundLines) {
        // anado las lineas de refund
        m_reflines.setLines(aRefundLines);
        showView("reflines");
    }   
    
    private void showView(String sView) {
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, sView);       
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setLayout(new java.awt.CardLayout());
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
