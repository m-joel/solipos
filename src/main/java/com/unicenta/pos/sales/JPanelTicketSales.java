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
import com.unicenta.pos.ticket.ProductInfoExt;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author JG uniCenta
 */
public class JPanelTicketSales extends JPanelTicket {

    private CatalogSelector m_cat;
   
    /** Creates a new instance of JPanelTicketSales */
    public JPanelTicketSales() {        
    }
    
    /**
     *
     * @param app
     */
    @Override
    public void init(AppView app) {
        super.init(app);
        m_ticketlines.addListSelectionListener(new CatalogSelectionListener());
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getTitle() {
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    protected Component getSouthComponent() {
        m_cat = new JCatalog(dlSales,
                "true".equals(m_jbtnconfig.getProperty("pricevisible")),
                "true".equals(m_jbtnconfig.getProperty("taxesincluded")),
                Integer.parseInt(m_jbtnconfig.getProperty("img-width", "64")),                
                Integer.parseInt(m_jbtnconfig.getProperty("img-height", "54")));
        //   Integer.parseInt(m_jbtnconfig.getProperty("img-width", "32")),
               //   Integer.parseInt(m_jbtnconfig.getProperty("img-height", "32")));
        m_cat.addActionListener(new CatalogListener());
        m_cat.getComponent().setPreferredSize(new Dimension(
                0,
                Integer.parseInt(m_jbtnconfig.getProperty("cat-height", "245"))));
        return m_cat.getComponent();
    }

    /**
     *
     */
    @Override
    protected void resetSouthComponent() {
        m_cat.showCatalogPanel(null);
    }
    
    /**
     *
     * @return
     */
    @Override
    protected JTicketsBag getJTicketsBag() {
        return JTicketsBag.createTicketsBag(m_App.getProperties().getProperty("machine.ticketsbag"), m_App, this);
    }
    
    /**
     *
     * @throws BasicException
     */
    @Override
    public void activate() throws BasicException {      
        super.activate();
        m_cat.loadCatalog();
    }      
    
    public void reLoadCatalog() {
        try {
            m_cat.loadCatalog();
        } catch (BasicException ex) {
        }

    }

    private class CatalogListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonTransition((ProductInfoExt) e.getSource());
        }  
    }
    
    private class CatalogSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {      
            
            if (!e.getValueIsAdjusting()) {
                int i = m_ticketlines.getSelectedIndex();
                
                if (i >= 0) {
                    // Look for the first non auxiliar product.
                    while (i >= 0 && m_oTicket.getLine(i).isProductCom()) {
                        i--;
                    }

                    // Show the accurate catalog panel...
                    if (i >= 0) {
                        m_cat.showCatalogPanel(m_oTicket.getLine(i).getProductID());
                    } else {
                        m_cat.showCatalogPanel(null);
                    }
                }
            }
        }  
    }
       
}