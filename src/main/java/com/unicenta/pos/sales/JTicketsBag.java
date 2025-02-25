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

import com.unicenta.pos.forms.AppView;
import com.unicenta.pos.forms.DataLogicSales;
import com.unicenta.pos.sales.restaurant.JTicketsBagRestaurantMap;
import com.unicenta.pos.sales.restaurant.JTicketsBagRestaurant;
import com.unicenta.pos.sales.shared.JTicketsBagShared;
import com.unicenta.pos.sales.simple.JTicketsBagSimple;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author JG uniCenta
 */
public abstract class JTicketsBag extends JPanel {
    
    /**
     *
     */
    protected AppView m_App;     

    /**
     *
     */
    protected DataLogicSales m_dlSales;

    /**
     *
     */
    protected TicketsEditor m_panelticket;    
    
    /** Creates new form JTicketsBag
     * @param oApp
     * @param panelticket */
    public JTicketsBag(AppView oApp, TicketsEditor panelticket) {        
        m_App = oApp;     
        m_panelticket = panelticket;        
        m_dlSales = (DataLogicSales) m_App.getBean("com.unicenta.pos.forms.DataLogicSales");
    }
    
    public abstract void activate();
    public abstract boolean deactivate();
    public abstract void deleteTicket();
    protected abstract JComponent getBagComponent();
    protected abstract JComponent getNullComponent();
    
    /**
     *
     * @param sName
     * @param app
     * @param panelticket
     * @return
     */
    public static JTicketsBag createTicketsBag(String sName, AppView app, TicketsEditor panelticket) {
        switch (sName) {
            case "standard":
                // return new JTicketsBagMulti(oApp, user, panelticket);
                return new JTicketsBagShared(app, panelticket);
            case "restaurant":
                return new JTicketsBagRestaurantMap(app, panelticket);
            default:
                // "simple"
                return new JTicketsBagSimple(app, panelticket);
        }
    }   
}